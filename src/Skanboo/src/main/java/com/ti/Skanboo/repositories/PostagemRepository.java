package com.ti.Skanboo.repositories;

import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ti.Skanboo.models.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

        List<Postagem> findByUsuario_Id(Long id);

        @Query("SELECT COUNT(p) FROM Postagem p " +
                        "WHERE p.data >= :dataInicial " +
                        "AND p.data <= :dataFinal")
        Long quantidadePostagensCriadasMesAtual(@Param("dataInicial") LocalDate dataInicial,
                        @Param("dataFinal") LocalDate dataFinal);

        @Query("SELECT EXTRACT(MONTH FROM p.data) AS mes, COUNT(*) AS quantidade " +
                        "FROM Postagem p " +
                        "WHERE EXTRACT(YEAR FROM p.data) = :ano " +
                        "GROUP BY EXTRACT(MONTH FROM p.data)")
        List<Object[]> quantidadePostagensCriadasPorMes(@Param("ano") int ano);

        @Query("SELECT categoriaProdutoDesejado, COUNT(*) AS quantidade " +
                        "FROM Postagem p " +
                        "GROUP BY categoriaProdutoDesejado")
        List<Object[]> categoriasMaisDesejadas();

        @Query("SELECT categoriaProduto, COUNT(*) AS quantidade " +
                        "FROM Postagem p " +
                        "GROUP BY categoriaProduto")
        List<Object[]> categoriasMaisOfertadas();
}