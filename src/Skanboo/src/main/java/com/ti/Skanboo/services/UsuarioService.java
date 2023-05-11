package com.ti.Skanboo.services;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ti.Skanboo.exceptions.AuthorizationException;
import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.models.enums.UsuarioEnum;
import com.ti.Skanboo.repositories.UsuarioRepository;
import com.ti.Skanboo.security.UserSpringSecurity;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Usuario encontrarPorId(Long id) {
        // Confere se o usuario logado esta autenticado para achar o ID indicado
        UserSpringSecurity userSpringSecurity = authenticated();
        
        if (!Objects.nonNull(userSpringSecurity)
                || !userSpringSecurity.hasRole(UsuarioEnum.ADMIN) && !id.equals(userSpringSecurity.getId()))
            throw new AuthorizationException("Acesso negado");

        Optional<Usuario> usuario = this.usuarioRepository.findById(id);

        return usuario.orElseThrow(() -> new RuntimeException("Usuario nao encontrado!"));
    }

    @Transactional
    public Usuario criar(Usuario obj) {

        obj.setId(null);
        obj.setSenha(this.bCryptPasswordEncoder.encode(obj.getSenha()));

        /* Usuario padrao e salvo como USER - num 2 */
        obj.setPerfis(Stream.of(UsuarioEnum.USER.getCode()).collect(Collectors.toSet()));
        obj.setEndereco(null);

        return this.usuarioRepository.save(obj);
    }

    @Transactional
    public Usuario atualizar(Usuario obj) {

        Usuario novoUsuario = encontrarPorId(obj.getId());

        novoUsuario.setSenha(obj.getSenha());
        novoUsuario.setSenha(this.bCryptPasswordEncoder.encode(obj.getSenha()));

        return this.usuarioRepository.save(novoUsuario);
    }

    public void deletar(Long id) {

        encontrarPorId(id);

        try {
            this.usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir usuario pois ele possui entidades relacionadas!");
        }
    }

    public static UserSpringSecurity authenticated() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
