package com.ti.Skanboo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.Skanboo.models.Troca;

@Repository
public interface TrocaRepository extends JpaRepository<Troca, Long> {
    
    List<Troca> findByOfertaId(Long ofertaId);
}





