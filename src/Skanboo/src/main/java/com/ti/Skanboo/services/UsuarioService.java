package com.ti.Skanboo.services;

import java.util.List;
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
import com.ti.Skanboo.exceptions.EntityNotFoundException;
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

        return usuario.orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado!"));
    }

    public Usuario listarInformacoesUsuarioAtivo() {

        UserSpringSecurity userSpringSecurity = authenticated();

        Optional<Usuario> usuario = this.usuarioRepository.findById(userSpringSecurity.getId());

        return usuario.orElseThrow(() -> new RuntimeException("Falha ao carregar informacoes do usuario!"));
    }

    public List<Usuario> listarUsuariosCadastrados() {

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !userSpringSecurity.hasRole(UsuarioEnum.ADMIN))
            throw new AuthorizationException("Acesso negado!");

        List<Usuario> usuario = this.usuarioRepository.findAll();

        return usuario;
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
    public Usuario atualizarPorId(Usuario obj) {

        Usuario usuario = encontrarPorId(obj.getId());

        usuario.setNome(obj.getNome());
        usuario.setCpf(obj.getCpf());
        usuario.setEmail(obj.getEmail());
        usuario.setSenha(obj.getSenha());
        usuario.setSenha(this.bCryptPasswordEncoder.encode(obj.getSenha()));
        usuario.setTelefone(obj.getTelefone());

        if (obj.getFoto() != null)
            usuario.setFoto(obj.getFoto());

        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizarUsuarioAtivo(Usuario obj) {

        UserSpringSecurity userSpringSecurity = authenticated();

        Usuario usuario = encontrarPorId(userSpringSecurity.getId());

        usuario.setNome(obj.getNome());
        usuario.setCpf(obj.getCpf());
        usuario.setEmail(obj.getEmail());
        usuario.setSenha(obj.getSenha());
        usuario.setSenha(this.bCryptPasswordEncoder.encode(obj.getSenha()));
        usuario.setDataNascimento(obj.getDataNascimento());
        usuario.setFoto(obj.getFoto());
        usuario.setTelefone(obj.getTelefone());
        usuario.setFoto(obj.getFoto());

        return this.usuarioRepository.save(usuario);
    }

    public void deletarPorId(Long id) {

        encontrarPorId(id);

        try {
            this.usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir usuario pois ele possui entidades relacionadas!");
        }
    }

    public void deletarUsuarioAtivo() {

        UserSpringSecurity userSpringSecurity = authenticated();

        try {
            this.usuarioRepository.deleteById(userSpringSecurity.getId());
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
