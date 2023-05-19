package com.ti.Skanboo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ti.Skanboo.models.Parceiro;

@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Long> /* <Entidade, tipo da variavel Id> */ {

    @Transactional(readOnly = true)
    Optional<Parceiro> findById(Long id);

}
