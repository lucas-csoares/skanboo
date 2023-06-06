package com.ti.Skanboo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ti.Skanboo.models.EnderecoUsuario;

@Repository
public interface EnderecoUsuarioRepository extends JpaRepository<EnderecoUsuario, Long> {
    // O underline indica que voce quer procurar uma lista de tasks relacionadas a
    // um usuario, mas o id desse usuario esta DENTRO do modelo User
    List<EnderecoUsuario> findByUsuario_Id(Long id);
}
