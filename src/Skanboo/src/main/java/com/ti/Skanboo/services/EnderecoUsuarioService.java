package com.ti.Skanboo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.Skanboo.models.EnderecoUsuario;
import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.repositories.EnderecoUsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class EnderecoUsuarioService {

    @Autowired
    private EnderecoUsuarioRepository enderecoUsuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public EnderecoUsuario encontrarPorId(Long id) {

        Optional<EnderecoUsuario> endereco = this.enderecoUsuarioRepository.findById(id);

        return endereco.orElseThrow(() -> new RuntimeException("Endereco nao encontrado!"));
    }

    @Transactional
    public EnderecoUsuario criar(EnderecoUsuario obj) {

        Usuario usuario = this.usuarioService.encontrarPorId(obj.getUsuario().getId());

        obj.setUsuario(usuario);

        return this.enderecoUsuarioRepository.save(obj);
    }
 
    @Transactional
    public EnderecoUsuario atualizar(EnderecoUsuario obj) {

        EnderecoUsuario novoEnderecoUsuario = encontrarPorId(obj.getId());

        novoEnderecoUsuario.setBairro(obj.getBairro());
        novoEnderecoUsuario.setCep(obj.getCep());
        novoEnderecoUsuario.setCidade(obj.getCidade());
        novoEnderecoUsuario.setComplemento(obj.getComplemento());
        novoEnderecoUsuario.setNumero(obj.getNumero());
        novoEnderecoUsuario.setRua(obj.getRua());
        novoEnderecoUsuario.setUf(obj.getUf());

        return this.enderecoUsuarioRepository.save(novoEnderecoUsuario);
    }

    public void deletar(Long id) {

        encontrarPorId(id); // retorna se o endereco existe ou nao

        try {
            this.enderecoUsuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir o endereco!");
        }
    }
}
