package com.ti.Skanboo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Parceiro.NOME_TABELA)
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Parceiro {

    public static final String NOME_TABELA = "parceiro";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    private String nome;

    @Column(name = "foto", length = 100000, nullable = true, updatable = true)
    @Lob 
    private String foto;

    @Column(name = "contrato", length = 100000, nullable = true, updatable = true)
    @Lob 
    private String contrato;

    @Column(name = "cnpj", length = 18, nullable = false, unique = true, updatable = false)
    @NotBlank
    private String cnpj;

    @Column(name = "plano", length = 10, nullable = false)
    @NotBlank
    private String plano;

}
