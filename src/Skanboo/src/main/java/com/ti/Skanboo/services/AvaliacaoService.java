package com.ti.Skanboo.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ti.Skanboo.exceptions.EntityNotFoundException;
import com.ti.Skanboo.exceptions.RatingCreationException;
import com.ti.Skanboo.exceptions.RatingNotFound;
import com.ti.Skanboo.models.Avaliacao;
import com.ti.Skanboo.models.Troca;
import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.models.enums.TrocaEnum;
import com.ti.Skanboo.repositories.AvaliacaoRepository;
import com.ti.Skanboo.repositories.UsuarioRepository;
import com.ti.Skanboo.security.UserSpringSecurity;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class AvaliacaoService {
    
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    

    @Autowired
    private TrocaService trocaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    
    //# Encontrar avaliacão por id
    public Avaliacao encontrarPorId(Long id) {

        Avaliacao avaliacao = this.avaliacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliacao nao encontrada!"));

        trocaService.encontrarPorId(avaliacao.getTroca().getId());

        return avaliacao;
    }
    
    
    
    //# Listar avaliações 
    public List<Avaliacao> listarAvaliacoesUsuarioAtivo() {
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        Long usuarioId = userSpringSecurity.getId();
        
        List<Avaliacao> todasAvaliacoes = avaliacaoRepository.findAll();
        
        List<Avaliacao> avaliacoesDoUsuarioLogado = todasAvaliacoes.stream()
            .filter(avaliacao -> avaliacao.getUsuario().getId().equals(usuarioId))
            .collect(Collectors.toList());

        if (avaliacoesDoUsuarioLogado.isEmpty()) {
                throw new RatingNotFound("Usuário não possui avaliações");
        }
    
        return avaliacoesDoUsuarioLogado;
    }
    



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
            throw new RatingCreationException("Não é possível avaliar: A troca não foi finalizada");
        }


        //Atualiza a nota final do usuário que trocou com o usuário ativo
        atualizarNotaFinal(obj);

        return this.avaliacaoRepository.save(obj);
        
    }

    
    //Deletar avaliação por id
    public void deletarPorId(Long id) {

        encontrarPorId(id);
        
        try {
            this.avaliacaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir avaliacao pois ela possui entidades relacionadas!");
        }
    }

    
    public void atualizarNotaFinal(Avaliacao avaliacao) {
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        Usuario usuario;
        if (trocaPertenceAoUsuarioPostagemOrigem(userSpringSecurity, avaliacao)){
            usuario = avaliacao.getTroca().getOferta().getPostagemOfertada().getUsuario();
            calcMedia(usuario);
        } else if(trocaPertenceAoUsuarioPostagemOfertada(userSpringSecurity, avaliacao)) {
            usuario = avaliacao.getTroca().getOferta().getPostagemOrigem().getUsuario();
            calcMedia(usuario);
        }
    }
    
    public void calcMedia(Usuario usuario) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByUsuarioAndMes(usuario, LocalDate.now().getMonthValue());
        int somaNotas = avaliacoes.stream().mapToInt(Avaliacao::getNota).sum();
        int quantidadeAvaliacoes = avaliacoes.size();
        double mediaNotas = quantidadeAvaliacoes > 0 ? (double) somaNotas / quantidadeAvaliacoes : 0;
        usuario.setNotaFinal(mediaNotas);
        usuarioRepository.save(usuario);
    }
    
    
    private Boolean trocaPertenceAoUsuarioPostagemOrigem(UserSpringSecurity userSpringSecurity, Avaliacao avaliacao) {
        return avaliacao.getTroca().getOferta().getPostagemOrigem().getUsuario().getId().equals(userSpringSecurity.getId());
    }

    private Boolean trocaPertenceAoUsuarioPostagemOfertada(UserSpringSecurity userSpringSecurity, Avaliacao avaliacao) {
        return avaliacao.getTroca().getOferta().getPostagemOfertada().getUsuario().getId().equals(userSpringSecurity.getId());
    }
    
}
    
        
    
    
    
    


    
    
    
    



    
    
    
    

    
 




