package com.ti.Skanboo.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ti.Skanboo.models.enums.OfertaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
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

    public Oferta(Postagem postagemOrigem, Postagem postagemOfertada) {

        this.postagemOrigem = postagemOrigem;
        this.postagemOfertada = postagemOfertada;
        this.status = OfertaEnum.EM_ANDAMENTO;
        this.hora = LocalTime.now();
        this.data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_postagem_origem", referencedColumnName = "id", nullable = false, updatable = false)
    private Postagem postagemOrigem;

    @ManyToOne
    @JoinColumn(name = "id_postagem_ofertada", referencedColumnName = "id", nullable = false, updatable = false)
    private Postagem postagemOfertada;

    @Enumerated(EnumType.STRING)
    private OfertaEnum status;

    @Column(name = "hora", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    @Column(name = "data", nullable = false)
    private String data;
}
