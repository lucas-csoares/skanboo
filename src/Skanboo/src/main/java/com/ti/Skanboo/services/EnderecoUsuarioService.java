package com.ti.Skanboo.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.Skanboo.exceptions.AuthorizationException;
import com.ti.Skanboo.models.EnderecoUsuario;
import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.models.enums.UsuarioEnum;
import com.ti.Skanboo.repositories.EnderecoUsuarioRepository;
import com.ti.Skanboo.security.UserSpringSecurity;

import jakarta.transaction.Transactional;

@Service
public class EnderecoUsuarioService {

    @Autowired
    private EnderecoUsuarioRepository enderecoUsuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public EnderecoUsuario encontrarPorId(Long id) {

        EnderecoUsuario endereco = this.enderecoUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereco nao encontrado!"));

        // Verifica se usuario esta logado e se o endereco que ele busca pertence a ele
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !userSpringSecurity.hasRole(UsuarioEnum.ADMIN)
                && !EnderecoPertenceAoUsuario(userSpringSecurity, endereco))
            throw new AuthorizationException("Acesso negado!");

        return endereco;
    }

    public List<EnderecoUsuario> listarEnderecoUsuarioAtivo() {
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        if (Objects.isNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");

        List<EnderecoUsuario> endereco = this.enderecoUsuarioRepository.findByUsuario_Id(userSpringSecurity.getId());

        if (endereco.isEmpty())
            throw new RuntimeException("Usuario nao possui indereco cadastrado!");

        return endereco;
    }

    @Transactional
    public EnderecoUsuario criar(EnderecoUsuario obj) {
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        if (Objects.isNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");

        Usuario usuario = this.usuarioService.encontrarPorId(userSpringSecurity.getId());

        obj.setUsuario(usuario);

        if (usuario.getEndereco() == null)
            usuario.setEndereco(obj);
        else
            throw new RuntimeException("Usuario ja possui endereco cadastrado!");

        return this.enderecoUsuarioRepository.save(obj);
    }

    @Transactional
    public EnderecoUsuario atualizarPorId(EnderecoUsuario obj) {

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

    public EnderecoUsuario atualizarEnderecoUsuarioAtivo(EnderecoUsuario obj) {

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        Usuario usuario = usuarioService.encontrarPorId(userSpringSecurity.getId());
        EnderecoUsuario novoEnderecoUsuario = encontrarPorId(usuario.getEndereco().getId());

        novoEnderecoUsuario.setBairro(obj.getBairro());
        novoEnderecoUsuario.setCep(obj.getCep());
        novoEnderecoUsuario.setCidade(obj.getCidade());
        novoEnderecoUsuario.setComplemento(obj.getComplemento());
        novoEnderecoUsuario.setNumero(obj.getNumero());
        novoEnderecoUsuario.setRua(obj.getRua());
        novoEnderecoUsuario.setUf(obj.getUf());

        return this.enderecoUsuarioRepository.save(novoEnderecoUsuario);
    }

    public void deletarPorId(Long id) {

        encontrarPorId(id);
        Usuario usuario = encontrarPorId(id).getUsuario();
        usuario.setEndereco(null);

        try {
            this.enderecoUsuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir o endereco!");
        }
    }

    public void deletarEnderecoUsuarioAtivo() {

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        Usuario usuario = usuarioService.encontrarPorId(userSpringSecurity.getId());
        Long id = encontrarPorId(usuario.getEndereco().getId()).getId();

        encontrarPorId(id);
        usuario.setEndereco(null);

        try {
            this.enderecoUsuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir o endereco!");
        }
    }

    private Boolean EnderecoPertenceAoUsuario(UserSpringSecurity userSpringSecurity, EnderecoUsuario enderecoUsuario) {
        return enderecoUsuario.getUsuario().getId().equals(userSpringSecurity.getId());
    }
}
