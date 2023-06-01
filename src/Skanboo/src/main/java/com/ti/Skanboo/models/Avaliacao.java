package com.ti.Skanboo.models;

import java.time.LocalTime;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {
    
    public static final String NOME_TABELA = "avaliacao";


    @Id //id Avaliacao
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;


    @ManyToOne //id Troca
    @JoinColumn(name = "id_troca", referencedColumnName = "id", nullable = false, updatable = false)
    private Troca troca;

    @ManyToOne //id Usuario
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false, updatable = false)
    private Usuario usuario;

    @Min(value = 0)
    @Max(value = 5)
    private int nota;

    @Column(name = "hora", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    @Column(name = "data", nullable = false)
    private LocalDate data;


}
