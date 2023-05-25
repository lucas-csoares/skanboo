package com.ti.Skanboo.models;

import jakarta.persistence.Column;

//import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    
    
    @OneToOne(cascade = )
    private Oferta id_oferta;
    
    
    
    private Boolean confirma_usuario01;
    
    private Boolean confirma_usuario02;
    private String status;




    
    
    
    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = true)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // private EnderecoUsuario endereco;
    
    
    
    // @Column(name = "descricao", length = 255, nullable = false)
    // @Size(min = 1, max = 255)
    // private String descricao;
    
    
    
    // @ManyToOne
    // @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // private Usuario usuario;
}
