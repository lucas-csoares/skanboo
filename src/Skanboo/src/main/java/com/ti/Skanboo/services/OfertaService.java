package com.ti.Skanboo.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.Skanboo.exceptions.AuthorizationException;
import com.ti.Skanboo.models.Oferta;
import com.ti.Skanboo.models.Postagem;
import com.ti.Skanboo.models.enums.OfertaEnum;
import com.ti.Skanboo.repositories.OfertaRepository;
import com.ti.Skanboo.security.UserSpringSecurity;

import jakarta.transaction.Transactional;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private PostagemService postagemService;

    public Oferta encontrarPorId(Long id) {

        Oferta oferta = this.ofertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta nao encontrada!"));

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !PostagemOrigemPertenceAoUsuario(userSpringSecurity, oferta)
                && !PostagemOfertadaPertenceAoUsuario(userSpringSecurity, oferta))
            throw new AuthorizationException("Acesso negado!");

        return oferta;
    }

    public List<List<Oferta>> listarOfertasFeitasUsuarioAtivo() {

        List<Postagem> postagens = this.postagemService.listarPostagensUsuarioAtivo();
        List<List<Oferta>> ofertasFeitas = new ArrayList<List<Oferta>>();

        for (Postagem postagem : postagens) {
            List<Oferta> ofertas = this.ofertaRepository.findByPostagemOfertada_Id(postagem.getId());

            if (!ofertas.isEmpty())
                ofertasFeitas.add(ofertas);
        }

        if (ofertasFeitas.isEmpty())
            throw new RuntimeException("Usuario nao fez nenhuma oferta!");

        return ofertasFeitas;
    }

    public List<List<Oferta>> listarOfertasRecebidasUsuarioAtivo() {

        List<Postagem> postagens = this.postagemService.listarPostagensUsuarioAtivo();
        List<List<Oferta>> ofertasRecebidas = new ArrayList<List<Oferta>>();

        for (Postagem postagem : postagens) {
            List<Oferta> ofertas = this.ofertaRepository.findBypostagemOrigem_Id(postagem.getId());

            if (!ofertas.isEmpty())
            ofertasRecebidas.add(ofertas);
        }

        if (ofertasRecebidas.isEmpty())
            throw new RuntimeException("Usuario nao recebeu nenhuma oferta!");

        return ofertasRecebidas;
    }

    @Transactional
    public Oferta criar(Oferta obj) {

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

        if (Objects.isNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");

        this.postagemService.encontrarPorId(obj.getPostagemOrigem().getId());
        this.postagemService.encontrarPorId(obj.getPostagemOfertada().getId());

        Boolean postagemOrigemPertenceAoUsuario = this.PostagemOrigemPertenceAoUsuario(userSpringSecurity, obj);
        Boolean postagemOfertadaPertenceAoUsuario = this.PostagemOfertadaPertenceAoUsuario(userSpringSecurity, obj);

        if (!postagemOrigemPertenceAoUsuario && postagemOfertadaPertenceAoUsuario) {
            obj.setId(null);
            obj.setData(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now()));
            obj.setHora(LocalTime.now());
        } else
            throw new RuntimeException("Nao e possivel fazer essa oferta!");

        return this.ofertaRepository.save(obj);
    }

    @Transactional
    public Oferta atualizarPorId(Oferta obj) {

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        Oferta novaOferta = encontrarPorId(obj.getId());

        if (novaOferta.getStatus().equals(OfertaEnum.RECUSADA) || novaOferta.getStatus().equals(OfertaEnum.ACEITA))
            throw new RuntimeException("A oferta ja foi recusada ou aceita, seu status nao pode ser atualizado!");

        Boolean postagemOrigemPertenceAoUsuario = this.PostagemOrigemPertenceAoUsuario(userSpringSecurity, novaOferta);

        if (obj.getStatus().equals(OfertaEnum.ACEITA)) {
            if (postagemOrigemPertenceAoUsuario)
                novaOferta.setStatus(OfertaEnum.ACEITA);
            else
                throw new RuntimeException("O usuario nao pode atualizar essa oferta!");
        } else if (obj.getStatus().equals(OfertaEnum.RECUSADA))
            novaOferta.setStatus(OfertaEnum.RECUSADA);

        return this.ofertaRepository.save(novaOferta);
    }

    public void deletarPorId(Long id) {

        Oferta oferta = encontrarPorId(id);

        // todo: verificacao se existem uma troca com a oferta referenciada
        if (!oferta.getStatus().equals(OfertaEnum.RECUSADA))
            throw new RuntimeException("A oferta precisa ser recusada antes de ser deletada!");

        try {
            this.ofertaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir a oferta pois ela possui entidades relacionadas!");

        }
    }

    private Boolean PostagemOrigemPertenceAoUsuario(UserSpringSecurity userSpringSecurity, Oferta oferta) {
        return oferta.getPostagemOrigem().getUsuario().getId().equals(userSpringSecurity.getId());
    }

    private Boolean PostagemOfertadaPertenceAoUsuario(UserSpringSecurity userSpringSecurity, Oferta oferta) {
        return oferta.getPostagemOfertada().getUsuario().getId().equals(userSpringSecurity.getId());
    }

}
