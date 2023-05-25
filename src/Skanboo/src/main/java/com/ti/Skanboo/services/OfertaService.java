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

        if (Objects.isNull(userSpringSecurity) ||
                !userSpringSecurity.hasRole(UsuarioEnum.ADMIN)
                        && !OfertaPertenceAoUsuario(userSpringSecurity, oferta))
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

        Boolean postagemOrigemPertenceAoUsuario = this.postagemService.postagemPertenceAoUsuario(userSpringSecurity,
                obj.getPostagemOrigem());
        Boolean postagemOfertadaPertenceAoUsuario = this.postagemService.postagemPertenceAoUsuario(userSpringSecurity,
                obj.getPostagemOfertada());

        if (!postagemOrigemPertenceAoUsuario && postagemOfertadaPertenceAoUsuario)
            obj.setId(null);
        else
            throw new RuntimeException("Nao e possivel fazer essa oferta!");

        return this.ofertaRepository.save(obj);
    }

    @Transactional
    public Oferta atualizarPorId(Oferta obj) {

        Oferta novaOferta = encontrarPorId(obj.getId());

        novaOferta.setStatus(obj.getStatus());

        return this.ofertaRepository.save(novaOferta);
    }

    private Boolean OfertaPertenceAoUsuario(UserSpringSecurity userSpringSecurity, Oferta oferta) {

        Postagem postagemOrigem = oferta.getPostagemOrigem();
        Postagem postagemOfertada = oferta.getPostagemOfertada();

        return postagemOrigem.getUsuario().getId().equals(userSpringSecurity.getId())
                || postagemOfertada.getUsuario().getId().equals(userSpringSecurity.getId());
    }

}
