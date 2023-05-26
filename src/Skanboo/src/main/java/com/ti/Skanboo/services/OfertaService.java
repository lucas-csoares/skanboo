package com.ti.Skanboo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.Skanboo.exceptions.AuthorizationException;
import com.ti.Skanboo.models.Oferta;
import com.ti.Skanboo.models.Postagem;
import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.models.enums.OfertaEnum;
import com.ti.Skanboo.models.enums.UsuarioEnum;
import com.ti.Skanboo.repositories.OfertaRepository;
import com.ti.Skanboo.security.UserSpringSecurity;

import jakarta.transaction.Transactional;

@Service
public class OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private PostagemService postagemService;

    @Autowired
    private UsuarioService usuarioService;

    public Oferta encontrarPorId(Long id) {

        Oferta oferta = this.ofertaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oferta nao encontrada!"));

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !PostagemOrigemPertenceAoUsuario(userSpringSecurity, oferta)
                && !PostagemOfertadaPertenceAoUsuario(userSpringSecurity, oferta))
            throw new AuthorizationException("Acesso negado!");

        return oferta;
    }

    // public List<Oferta> listarOfertasUsuarioAtivo() {

    // UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

    // if (Objects.isNull(userSpringSecurity))
    // throw new AuthorizationException("Acesso negado!");

    // List<Postagem> postagens =
    // this.postagemService.listarPostagensUsuarioAtivo();
    // List<Oferta> ofertas = new ArrayList<>();

    // for (Postagem postagem : postagens) {
    // ofertas = this.ofertaRepository.findByPostagem_Id(postagem.getId());
    // }

    // return ofertas;
    // }

    @Transactional
    public Oferta criar(Oferta obj) {

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

        if (Objects.isNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");

        this.postagemService.encontrarPorId(obj.getPostagemOrigem().getId());
        this.postagemService.encontrarPorId(obj.getPostagemOfertada().getId());

        Boolean postagemOrigemPertenceAoUsuario = this.PostagemOrigemPertenceAoUsuario(userSpringSecurity, obj);
        Boolean postagemOfertadaPertenceAoUsuario = this.PostagemOfertadaPertenceAoUsuario(userSpringSecurity, obj);

        if (!postagemOrigemPertenceAoUsuario && postagemOfertadaPertenceAoUsuario)
            obj.setId(null);
        else
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

    private Boolean PostagemOrigemPertenceAoUsuario(UserSpringSecurity userSpringSecurity, Oferta oferta) {
        return oferta.getPostagemOrigem().getUsuario().getId().equals(userSpringSecurity.getId());
    }

    private Boolean PostagemOfertadaPertenceAoUsuario(UserSpringSecurity userSpringSecurity, Oferta oferta) {
        return oferta.getPostagemOfertada().getUsuario().getId().equals(userSpringSecurity.getId());
    }

}
