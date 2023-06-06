package com.ti.Skanboo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = EnderecoUsuario.NOME_TABELA)
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoUsuario {

        public static final String NOME_TABELA = "endereco_usuario";

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true)
        private Long id;

        @OneToOne
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private Usuario usuario;

        @Column(name = "rua", length = 45, nullable = false)
        @NotBlank
        private String rua;

        @Column(name = "bairro", length = 45, nullable = false)
        @NotBlank
        private String bairro;

        @Column(name = "uf", length = 45, nullable = false)
        @NotBlank
        private String uf;

        @Column(name = "cidade", length = 45, nullable = false)
        @NotBlank
        private String cidade;

        @Column(name = "numero", length = 45, nullable = false)
        @NotNull
        private Integer numero;

        @Column(name = "complemento", length = 45)
        private String complemento;

        @Column(name = "cep", length = 45, nullable = false)
        @NotBlank
        private String cep;
}
