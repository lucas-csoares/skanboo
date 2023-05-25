package com.ti.Skanboo.models;

import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.Getter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Table(name = Oferta.NOME_TABELA)
@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Oferta {

    public static final String NOME_TABELA = "oferta";

    @Id
    @GerenateValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @OneToOne
    private Postagem id_postagem_pai;

    private Postagem id_postagem_ofertada;

    private String status;
    
}
