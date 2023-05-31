package com.ti.Skanboo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ti.Skanboo.models.Avaliacao;

@Repository
public class AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
}
