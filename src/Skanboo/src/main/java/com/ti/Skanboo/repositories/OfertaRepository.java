package com.ti.Skanboo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ti.Skanboo.models.Oferta;
import com.ti.Skanboo.models.Postagem;
import com.ti.Skanboo.models.enums.OfertaEnum;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    List<Oferta> findByPostagemOfertada_Id(Long id);

    List<Oferta> findBypostagemOrigem_Id(Long id);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END " +
            "FROM Oferta o " +
            "WHERE o.postagemOrigem = :postagemOrigem " +
            "AND o.postagemOfertada = :postagemOfertada " +
            "AND o.status = :status")
    boolean existsByPostagemOrigemAndPostagemOfertadaAndStatus(
            @Param("postagemOrigem") Postagem postagemOrigem,
            @Param("postagemOfertada") Postagem postagemOfertada,
            @Param("status") OfertaEnum status);
    // Oferta findByPostagemOrigemAndPostagemOfertada(Postagem postagemOrigem,
    // Postagem postagemOfertada);
}
