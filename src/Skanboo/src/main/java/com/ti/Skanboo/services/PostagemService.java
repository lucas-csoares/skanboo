package com.ti.Skanboo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ti.Skanboo.models.Postagem;
import com.ti.Skanboo.models.Usuario;

import java.util.Objects;
import com.ti.Skanboo.repositories.PostagemRepository;
import com.ti.Skanboo.repositories.UsuarioRepository;
//import com.ti.Skanboo.repositories.UsuarioRepository;
import com.ti.Skanboo.models.enums.UsuarioEnum;
import com.ti.Skanboo.security.UserSpringSecurity;
import com.ti.Skanboo.exceptions.AuthorizationException;
import jakarta.transaction.Transactional;

import java.util.List;
//import java.util.Optional;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    // @Autowired
    // private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Postagem> listarPostagens() {
        //! revisar se necessario, apos testes
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        if (Objects.isNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");

        List<Postagem> postagem = this.postagemRepository.findByUsuario_Id(userSpringSecurity.getId());
        return postagem;
    }



    public Postagem encontrarPorId(Long id) {
        //todo: adicionar metodo de verificacao de userSpringSecurity <- autenticacao de usuario

        Postagem postagem = this.postagemRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Postagem nao encontrada!"));

         // Verifica se usuario esta logado e se o endereco que ele busca pertence a ele
         UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

         if (Objects.isNull(userSpringSecurity) || !userSpringSecurity.hasRole(UsuarioEnum.ADMIN)
                 && !PostagemPertenceAoUsuario(userSpringSecurity, postagem))
             throw new AuthorizationException("Acesso negado!");

        return postagem;
    }

    @Transactional
    public Postagem criar(Postagem obj) {
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        if (Objects.isNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");

        Usuario usuario = this.usuarioService.encontrarPorId(userSpringSecurity.getId());
        
        obj.setId(null);

        return this.postagemRepository.save(obj);
    }

    @Transactional
    public Postagem atualizar(Postagem obj) {
        
        Postagem novaPostagem = encontrarPorId(obj.getId());

        return this.postagemRepository.save(novaPostagem);        
    }

    public void deletar(Long id) {

        encontrarPorId(id);

        try {
            this.postagemRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir essa postagem, existem negociacoes em andamento!");
            
            //? revisar mensagem, existem realmente negociacoes em andamento ou e um texto generico?
            //? se for generico, sugiro mudar para algo mais generico (ver UsuarioService)
        }
    }
    
    private Boolean PostagemPertenceAoUsuario(UserSpringSecurity userSpringSecurity, Postagem postagem) {
        return postagem.getUsuario().getId().equals(userSpringSecurity.getId());
    }



}
