package com.ti.Skanboo.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ti.Skanboo.models.Troca;

@Repository
public interface TrocaRepository extends JpaRepository<Troca, Long> {

        List<Troca> findByOfertaId(Long ofertaId);

        @Query("SELECT COUNT(t) FROM Troca t " +
                        "WHERE t.data >= :dataInicial " +
                        "AND t.data <= :dataFinal")
        Long quantidadeTrocasCriadasMesAtual(@Param("dataInicial") LocalDate dataInicial,
                        @Param("dataFinal") LocalDate dataFinal);

        @Query("SELECT EXTRACT(MONTH FROM t.data) AS mes, COUNT(*) AS quantidade " +
                        "FROM Troca t " +
                        "WHERE EXTRACT(YEAR FROM t.data) = :ano " +
                        "GROUP BY EXTRACT(MONTH FROM t.data)")
        List<Object[]> quantidadeTrocasCriadasPorMes(@Param("ano") int ano);

        @Query("SELECT COUNT(t) FROM Troca t " +
                        "WHERE t.data >= :dataInicial " +
                        "AND t.data <= :dataFinal " +
                        "AND t.status = 'FINALIZADA'")
        Long quantidadeTrocasFinalizadasMesAtual(@Param("dataInicial") LocalDate dataInicial,
                        @Param("dataFinal") LocalDate dataFinal);

        @Query("SELECT EXTRACT(MONTH FROM t.data) AS mes, COUNT(*) AS quantidade " +
                        "FROM Troca t " +
                        "WHERE EXTRACT(YEAR FROM t.data) = :ano " +
                        "AND t.status = 'FINALIZADA' " +
                        "GROUP BY EXTRACT(MONTH FROM t.data)")
        List<Object[]> quantidadeTrocasFinalizadasPorMes(@Param("ano") int ano);
}
