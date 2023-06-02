package com.ti.Skanboo.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ti.Skanboo.models.enums.UsuarioEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Usuario.NOME_TABELA)
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    public static final String NOME_TABELA = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    private String nome;

    @Column(name = "cpf", length = 14, nullable = false, unique = true, updatable = false)
    @NotBlank
    private String cpf;

    @Column(name = "email", length = 45, nullable = false, unique = true)
    @NotBlank
    private String email;

    @Column(name = "senha", length = 200, nullable = false, updatable = false)
    @NotBlank
    @Size(min = 6, max = 200)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //READ_WRITE para editar usuário
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private EnderecoUsuario endereco;

    @Column(name = "data_nascimento", length = 8, nullable = false)
    private LocalDate dataNascimento; // formato yyyy-mm-dd

    @Column(name = "foto", length = 100000, nullable = true, updatable = true)
    @Lob
    private String foto;

    @Column(name = "telefone", length = 15, nullable = false, unique = true)
    @NotBlank
    private String telefone;

    @Column(name = "nota_final", nullable = false, updatable = true)
    private Double notaFinal;

    {
        notaFinal = 0.0;
    }


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Postagem> postagem = new ArrayList<Postagem>();

    //Avaliacao
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CollectionTable(name = "perfis_usuario")
    @Column(name = "perfil", nullable = false)
    private Set<Integer> perfis = new HashSet<>();

    @Column(name = "hora", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    public Set<UsuarioEnum> getPerfil() {
        return this.perfis.stream().map(x -> UsuarioEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void adicionaPerfil(UsuarioEnum usuarioEnum) {
        this.perfis.add(usuarioEnum.getCode());
    }
}
