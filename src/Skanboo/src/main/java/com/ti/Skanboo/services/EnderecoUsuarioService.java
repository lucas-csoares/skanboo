package com.ti.Skanboo.services;

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

    @Transactional
    public EnderecoUsuario criar(EnderecoUsuario obj) {
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        if (Objects.isNull(userSpringSecurity))
            throw new AuthorizationException("Acesso negado!");

        Usuario usuario = this.usuarioService.encontrarPorId(userSpringSecurity.getId());

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

    private Boolean EnderecoPertenceAoUsuario(UserSpringSecurity userSpringSecurity, EnderecoUsuario enderecoUsuario) {
        return enderecoUsuario.getUsuario().getId().equals(userSpringSecurity.getId());
    }
}
