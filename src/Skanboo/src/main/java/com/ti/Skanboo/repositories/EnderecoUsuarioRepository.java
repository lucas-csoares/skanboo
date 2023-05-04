package com.ti.Skanboo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ti.Skanboo.models.EnderecoUsuario;

@Repository
public interface EnderecoUsuarioRepository extends JpaRepository<EnderecoUsuario, Long> {
    
}
