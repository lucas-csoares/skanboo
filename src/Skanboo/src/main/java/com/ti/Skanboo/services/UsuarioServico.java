package com.ti.Skanboo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ti.Skanboo.models.EnderecoUsuario;
import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.repositories.EnderecoUsuarioRepositorio;
import com.ti.Skanboo.repositories.UsuarioRepositorio;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private EnderecoUsuarioRepositorio enderecoUsuarioRepositorio;

    public Usuario findById(Long id) {

        Optional<Usuario> usuario = this.usuarioRepositorio.findById(id);

        return usuario.orElseThrow(() -> new RuntimeException("Usuario nao encontrado."));
    }

    @Transactional
    public Usuario createUsuario(Usuario obj) {

        obj.setId(null); // para seguranca, caso o usuario tente mandar um query com id predefinido
        EnderecoUsuario enderecoUsuario = obj.getEndereco();
        enderecoUsuario = enderecoUsuarioRepositorio.save(enderecoUsuario); // todo: verificar implementacao
        obj.setEndereco(enderecoUsuario);
        obj = this.usuarioRepositorio.save(obj);

        return obj;
    }

    @Transactional
    public Usuario updateUsuario (Usuario obj) {

        Usuario novoUsuario = findById(obj.getId());
        
        novoUsuario.setNome(obj.getNome());
        novoUsuario.setEmail(obj.getEmail());
        novoUsuario.setSenha(obj.getSenha());
        novoUsuario.setEndereco(obj.getEndereco());
        novoUsuario.setDataNascimento(obj.getDataNascimento());
        novoUsuario.setFoto(obj.getFoto());
        novoUsuario.setTelefone(obj.getTelefone());

        return this.usuarioRepositorio.save(novoUsuario);
    }

    public void deleteUsuario (Long id) {

        findById(id); //retorna se o usuario existe ou nao

        try {
            this.usuarioRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir usuario pois ele possui entidades relacionadas");
        }
    }
}
