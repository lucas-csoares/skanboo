package com.ti.Skanboo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ti.Skanboo.models.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> /* <Entidade, tipo da variavel Id> */ {


}