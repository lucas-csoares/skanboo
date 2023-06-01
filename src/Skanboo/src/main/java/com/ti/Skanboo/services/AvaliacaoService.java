package com.ti.Skanboo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.Skanboo.models.Avaliacao;
import com.ti.Skanboo.models.Troca;
import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.models.enums.TrocaEnum;
import com.ti.Skanboo.repositories.AvaliacaoRepository;
import com.ti.Skanboo.repositories.TrocaRepository;
import com.ti.Skanboo.security.UserSpringSecurity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class AvaliacaoService {
    
    @Autowired
    private AvaliacaoRepository avaliacao;

    @Autowired
    private TrocaRepository trocaRepository;

    @Autowired
    private TrocaService trocaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    // Encontrar avaliacão por id





    //*avaliacao.getTroca().getOferta().getPostagemOrigem().getUsuario().getId() || avaliacao.getTroca().getOferta().getPostagemOfertada().getUsuario().getId()

    // Listar avaliações 


    // Criar avaliação
    public Avaliacao criar(Long id_troca, Avaliacao obj) {


        //Verificando a existência da Troca
        Troca troca = this.trocaService.encontrarPorId(id_troca);
      
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        
        Usuario usuario = this.usuarioService.encontrarPorId(userSpringSecurity.getId());
        
        // Nota do aluno
        int nota = obj.getNota();
        
        //Verificando status da troca
        TrocaEnum statusTroca = troca.getStatus();
       
                                                        
        if(statusTroca == TrocaEnum.FINALIZADA) {
            if (nota >= 0 && nota <= 5) {
                obj.setId(null); // usada para garantir que a entidade Avaliacao criada não tenha um ID pré-existente antes de ser persistida no banco de dados.
                obj.setNota(nota);
                obj.setUsuario(usuario);
                obj.setTroca(troca);
                obj.setData(LocalDate.now());
                obj.setHora(LocalTime.now());
            } else {
                throw new IllegalArgumentException("A nota deve estar entre 0 e 5.");
            }
        } else {
            throw new RuntimeException("Não é possível avaliar: A troca não foi finalizada");
        }

        return this.avaliacaoRepository.save(obj);

    }
        



    //Atualizar avaliação por Id

    
    //Deletar avaliação por id



    private Boolean trocaPertenceAoUsuario(UserSpringSecurity userSpringSecurity, Avaliacao avaliacao) {
        return avaliacao.getTroca().getOferta().getPostagemOrigem().getUsuario().getId().equals(userSpringSecurity.getId()) 
            ||
            avaliacao.getTroca().getOferta().getPostagemOfertada().getUsuario().getId().equals(userSpringSecurity.getId());
    }
}
