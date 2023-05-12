package com.ti.Skanboo.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Column(name = "senha", length = 200, nullable = false)
    @NotBlank
    @Size(min = 6, max = 200)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // para evitar acesso ciclico as entidades
    private EnderecoUsuario endereco = new EnderecoUsuario();

    @Column(name = "data_nascimento", length = 8, nullable = false)
    private LocalDate dataNascimento; // formato yyyy-mm-dd

    @Column(name = "foto", length = 255)
    @Lob
    private byte[] foto;

    @Column(name = "telefone", length = 15, nullable = false, unique = true)
    @NotBlank
    private String telefone;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // para evitar acesso ciclico as entidades
    private List<Postagem> postagem = new ArrayList<Postagem>();

    /*-----Codigos para autenticadao de usuario-----*/

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CollectionTable(name = "perfis_usuario")
    @Column(name = "perfil", nullable = false)
    private Set<Integer> perfis = new HashSet<>();

    public Set<UsuarioEnum> getPerfil() {
        return this.perfis.stream().map(x -> UsuarioEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void adicionaPerfil(UsuarioEnum usuarioEnum) {
        this.perfis.add(usuarioEnum.getCode());
    }
}
