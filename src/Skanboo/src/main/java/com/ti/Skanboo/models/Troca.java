package com.ti.Skanboo.models;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ti.Skanboo.models.enums.TrocaEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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

    public Troca(Oferta oferta) {

        this.oferta = oferta;
        this.status = TrocaEnum.EM_ANDAMENTO;
        this.confirma_usuario01 = false;
        this.confirma_usuario02 = false;
        this.data = LocalDate.now();
        this.hora = LocalTime.now();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_oferta", referencedColumnName = "id", nullable = false)
    private Oferta oferta;
    
    @Column(name = "confirmacao_usuario01")
    private Boolean confirma_usuario01;
    
    @Column(name = "confirmacao_usuario02")
    private Boolean confirma_usuario02;
    
    @Enumerated(EnumType.STRING)
    private TrocaEnum status;    

    @Column(name = "hora", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    @Column(name = "data", nullable = false)
    private LocalDate data;
}

    
    
    
  
    
    
    
