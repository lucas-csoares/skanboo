package com.ti.Skanboo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ti.Skanboo.models.Postagem;
import com.ti.Skanboo.repositories.PostagemRepository;
//import com.ti.Skanboo.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    // @Autowired
    // private UsuarioRepository usuarioRepository;

    public List<Postagem> listarPostagens() {
        //! revisar se necessario, apos testes

        return postagemRepository.findAll();
    }

    public Postagem encontrarPorId(Long id) {
        //todo: adicionar metodo de verificacao de userSpringSecurity <- autenticacao de usuario

        Optional<Postagem> postagem = this.postagemRepository.findById(id);

        return postagem.orElseThrow(() -> new RuntimeException("Postagem nao encontrada!"));
    }

    @Transactional
    public Postagem criar(Postagem obj) {
        
        obj.setId(null);

        return postagemRepository.save(obj);
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
}
