package com.ti.Skanboo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ti.Skanboo.models.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    // List<Oferta> findByPostagem_Id(Long id);
    
}
