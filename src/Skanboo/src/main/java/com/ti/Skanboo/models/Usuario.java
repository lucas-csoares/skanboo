package com.ti.Skanboo.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;
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
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String nome;

    @Column(name = "cpf", length = 11, nullable = false, unique = true, updatable = false)
    @NotNull
    @NotEmpty
    private String cpf;

    @Column(name = "email", length = 45, nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String email;

    @Column(name = "senha", length = 20, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20)
    private String senha;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // para evitar acesso ciclico as entidades
    private EnderecoUsuario endereco = new EnderecoUsuario();

    @Column(name = "tipo_usuario", nullable = false)
    @NotNull
    @NotEmpty
    // todo: atributo oculto para usuario, devemos verificar como vamos tratar isso
    private String tipoUsuario;

    @Column(name = "data_nascimento", length = 8, nullable = false)
    private LocalDate dataNascimento; // formato yyyy-mm-dd

    @Column(name = "foto", length = 255)
    private String foto;

    @Column(name = "telefone", length = 11, nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String telefone;
}
