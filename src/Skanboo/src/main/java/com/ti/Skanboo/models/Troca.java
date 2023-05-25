package com.ti.Skanboo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = Troca.NOME_TABELA)
@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Troca {
    
    public static final String NOME_TABELA = "troca";

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_oferta", referencedColumnName = "id", nullable = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Oferta id_oferta;
    
    
    @Column(name = "confirmacao_usuario01")
    private Boolean confirma_usuario01;
    
    @Column(name = "confirmacao_usuario01")
    private Boolean confirma_usuario02;
    
    
    @Column(name = "status_troca")
    private String status;

    // @Column(name = "descricao", length = 255, nullable = false)
    // @Size(min = 1, max = 255)
    // private String descricao;
    
    
    
    // @ManyToOne
    // @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // private Usuario usuario;

    
}

    
    
    
  
    
    
    
