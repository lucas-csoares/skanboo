package com.ti.Skanboo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ti.Skanboo.models.Postagem;
import com.ti.Skanboo.models.Usuario;

import java.util.Objects;
import com.ti.Skanboo.repositories.PostagemRepository;
import com.ti.Skanboo.models.enums.UsuarioEnum;
import com.ti.Skanboo.security.UserSpringSecurity;
import com.ti.Skanboo.exceptions.AuthorizationException;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Postagem encontrarPorId(Long id) {

        Postagem postagem = this.postagemRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Postagem nao encontrada!"));

         // Verifica se usuario esta logado e se o endereco que ele busca pertence a ele
         UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

         if (Objects.isNull(userSpringSecurity) || !userSpringSecurity.hasRole(UsuarioEnum.ADMIN)
                 && !PostagemPertenceAoUsuario(userSpringSecurity, postagem))
             throw new AuthorizationException("Acesso negado!");

        return postagem;
    }

    public List<Postagem> listarPostagens() {

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        if (Objects.isNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");

        List<Postagem> postagem = this.postagemRepository.findByUsuario_Id(userSpringSecurity.getId());
        return postagem;
    }

    @Transactional
    public Postagem criar(Postagem obj) {
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        if (Objects.isNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");

        Usuario usuario = this.usuarioService.encontrarPorId(userSpringSecurity.getId());
        
        obj.setUsuario(usuario);

        return this.postagemRepository.save(obj);
    }

    @Transactional
    public Postagem atualizar(Postagem obj) {
        
        Postagem novaPostagem = encontrarPorId(obj.getId());

        novaPostagem.setTitulo(obj.getTitulo());
        novaPostagem.setDescricao(obj.getDescricao());

        return this.postagemRepository.save(novaPostagem);        
    }

    public void deletar(Long id) {

        encontrarPorId(id);

        try {
            this.postagemRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir usuario pois ele possui entidades relacionadas!");
            
        }
    }
    
    private Boolean PostagemPertenceAoUsuario(UserSpringSecurity userSpringSecurity, Postagem postagem) {
        return postagem.getUsuario().getId().equals(userSpringSecurity.getId());
    }



}
