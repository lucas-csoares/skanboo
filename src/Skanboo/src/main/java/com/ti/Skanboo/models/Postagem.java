package com.ti.Skanboo.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = Postagem.NOME_TABELA)
@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Postagem {

    public static final String NOME_TABELA = "postagem";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "titulo", length = 30, nullable = false)
    @NotBlank
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    @Column(name = "descricao", length = 255, nullable = false)
    @Size(min = 1, max = 255)
    private String descricao;

    @Column(name = "categoria_produto", length = 30, nullable = false, updatable = true)
    @Size(min = 1, max = 30)
    private String categoriaProduto;

    @Column(name = "categoria_produto_desejado", length = 30, nullable = false, updatable = true)
    @Size(min = 1, max = 30)
    private String categoriaProdutoDesejado;

    @Column(name = "foto", length = 100000, nullable = true, updatable = true)
    @Lob 
    private String foto;

    @OneToMany(mappedBy = "id_postagem_pai", cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Oferta> ofertas = new ArrayList<Oferta>();

    @Column(name = "hora_postagem", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaPostagem;

    @Column(name = "data_postagem", nullable = false)
    private String dataPostagem;

    public Postagem(String titulo, Usuario usuario, String descricao, String categoriaProduto,
            String categoriaProdutoDesejado) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.descricao = descricao;
        this.categoriaProduto = categoriaProduto;
        this.categoriaProdutoDesejado = categoriaProdutoDesejado;
        this.horaPostagem = LocalTime.now();
        this.dataPostagem = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}