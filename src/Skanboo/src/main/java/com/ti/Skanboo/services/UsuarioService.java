package com.ti.Skanboo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario encontrarPorId(Long id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);

        return usuario.orElseThrow(() -> new RuntimeException("Usuario nao encontrado!"));
    }

    @Transactional
    public Usuario criar(Usuario obj) {

        obj.setId(null);
        obj.setEndereco(null);

        return this.usuarioRepository.save(obj);
    }

    @Transactional
    public Usuario atualizar(Usuario obj) {

        Usuario novoUsuario = encontrarPorId(obj.getId());

        novoUsuario.setNome(obj.getNome());
        novoUsuario.setEmail(obj.getEmail());
        novoUsuario.setSenha(obj.getSenha());
        novoUsuario.setDataNascimento(obj.getDataNascimento());
        novoUsuario.setFoto(obj.getFoto());
        novoUsuario.setTelefone(obj.getTelefone());

        return this.usuarioRepository.save(novoUsuario);
    }

    public void deletar(Long id) {

        encontrarPorId(id); // retorna se o usuario existe ou nao

        try {
            this.usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir usuario pois ele possui entidades relacionadas!");
        }
    }
}
