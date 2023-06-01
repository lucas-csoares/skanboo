package com.ti.Skanboo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ti.Skanboo.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        @Transactional(readOnly = true)
        Usuario findByEmail(String email);

        @Query("SELECT COUNT(u) FROM Usuario u " +
                        "WHERE u.data >= :dataInicial " +
                        "AND u.data <= :dataFinal")
        Long quantidadeUsuariosCriadosMesAtual(@Param("dataInicial") LocalDate dataInicial,
                        @Param("dataFinal") LocalDate dataFinal);

        @Query("SELECT EXTRACT(MONTH FROM u.data) AS mes, COUNT(*) AS quantidade " +
                        "FROM Usuario u " +
                        "WHERE EXTRACT(YEAR FROM u.data) = :ano " +
                        "GROUP BY EXTRACT(MONTH FROM u.data)")
        List<Object[]> quantidadeUsuariosCriadosPorMes(@Param("ano") int ano);

}
