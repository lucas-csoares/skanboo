package com.ti.Skanboo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ti.Skanboo.models.Avaliacao;
import com.ti.Skanboo.models.Troca;
import com.ti.Skanboo.models.Usuario;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
    @Query("SELECT a FROM Avaliacao a WHERE a.usuario = :usuario AND MONTH(a.data) = :mes")
    List<Avaliacao> findByUsuarioAndMes(Usuario usuario, int mes);

    boolean existsByUsuarioAndTroca(Usuario usuario, Troca troca);
}
