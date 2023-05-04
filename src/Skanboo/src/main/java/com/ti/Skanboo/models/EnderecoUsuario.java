package com.ti.Skanboo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
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

        @OneToOne(mappedBy = "endereco")
        private Usuario id_usuario;

        @Column(name = "rua", length = 45, nullable = false)
        @NotNull
        @NotEmpty
        private String rua;

        @Column(name = "bairro", length = 45, nullable = false)
        @NotNull
        @NotEmpty
        private String bairro;

        @Column(name = "uf", length = 45, nullable = false)
        @NotNull
        @NotEmpty
        private String uf;

        @Column(name = "cidade", length = 45, nullable = false)
        @NotNull
        @NotEmpty
        private String cidade;

        @Column(name = "numero", length = 45, nullable = false)
        @NotNull
        @NotEmpty
        private Integer numero;

        @Column(name = "complemento", length = 45)
        private String complemento;

        @Column(name = "cep", length = 45, nullable = false)
        @NotNull
        @NotEmpty
        private String cep;

}
