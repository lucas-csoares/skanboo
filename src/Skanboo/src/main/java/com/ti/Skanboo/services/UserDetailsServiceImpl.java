package com.ti.Skanboo.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.repositories.UsuarioRepository;
import com.ti.Skanboo.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Usuario usuario = this.usuarioRepository.findByEmail(email);

        if (Objects.isNull(usuario))
            throw new UsernameNotFoundException("Usuario nao encontrado " + email);
        
        return new UserSpringSecurity(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfil());
    }
    
}
