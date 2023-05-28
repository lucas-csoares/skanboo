package com.ti.Skanboo.services;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import com.ti.Skanboo.exceptions.AuthorizationException;
import com.ti.Skanboo.exceptions.EntityNotFoundException;
import com.ti.Skanboo.exceptions.OfferUpdateException;
import com.ti.Skanboo.models.Oferta;
import com.ti.Skanboo.models.Postagem;
import com.ti.Skanboo.models.Troca;
import com.ti.Skanboo.models.enums.OfertaEnum;
import com.ti.Skanboo.models.enums.TrocaEnum;
import com.ti.Skanboo.repositories.TrocaRepository;
import com.ti.Skanboo.security.UserSpringSecurity;


@Service
public class TrocaService {
    
    @Autowired
    private TrocaRepository trocaRepository;

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private PostagemService postagemService;


    //*Método encontrarPorId
    public Troca encontrarPorId(Long id) {

        Troca troca = this.trocaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Troca nao encontrada!"));
        
        ofertaService.encontrarPorId(troca.getOferta().getId());
        
        return troca;
    }
    
    // Postagem postagemOrigem = oferta.getPostagemOrigem();
    // Postagem postagemOfertada = oferta.getPostagemOfertada();
    // Usuario usuarioPostagemOrigem = postagemOrigem.getUsuario();
    // Usuario usuarioPostagemOfertada = postagemOfertada.getUsuario();
    
    //*Método listarTroca - Precisa de revisão 
    public List<Troca> listarTrocasUsuarioAtivo() {
        List<List<Oferta>> ofertasFeitas = this.ofertaService.listarOfertasFeitasUsuarioAtivo(); //já verifica o usuário logado
        List<List<Oferta>> ofertasRecebidas = this.ofertaService.listarOfertasRecebidasUsuarioAtivo();
        List<Troca> trocasRealizadas = new ArrayList<>();
               
        for (List<Oferta> oferta : ofertasFeitas) {
            for (Oferta of : oferta) {
                
                List<Troca> trocas = this.trocaRepository.findByOfertaId(of.getId());
                
                if (!trocas.isEmpty()) {
                    trocasRealizadas.addAll(trocas);
                }
            }
        }

        for (List<Oferta> oferta : ofertasRecebidas) {
            for (Oferta of : oferta) {
                List<Troca> trocas = this.trocaRepository.findByOfertaId(of.getId());

                if (!trocas.isEmpty()) {
                    trocasRealizadas.addAll(trocas);
                }
            }
        }
        
        if (trocasRealizadas.isEmpty())
            throw new RuntimeException("Usuario nao realizou nenhuma troca!");
    
        return trocasRealizadas;
            
    }
                
    
    



    //* Método criar troca
    public Troca criar(Long id_oferta) {

        //Verificando se oferta existe
        Oferta obj = this.ofertaService.encontrarPorId(id_oferta);

        //Verificar status da oferta
        OfertaEnum statusOferta = obj.getStatus();
        
        Troca troca;
        
        if (statusOferta == OfertaEnum.ACEITA) {
           troca = new Troca(obj);          
        } else {
            throw new RuntimeException("Não é possível criar troca: oferta não aceita");
        }
        
        return this.trocaRepository.save(troca);
    
    }


    public Troca atualizarPorId(Long id_troca) {
        
        
        Troca novaTroca = encontrarPorId(id_troca); 

        if (novaTroca.getStatus().equals(TrocaEnum.FINALIZADA)) //verifica a troca original
            throw new RuntimeException("A Troca já foi finalizada, seu status nao pode ser atualizado!");

        //UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        //List<Postagem> postagem = new ArrayList<Postagem>();
        
        List<Postagem> postagensUsuarioLogado = this.postagemService.listarPostagensUsuarioAtivo();
        
        for(Postagem postagem : postagensUsuarioLogado) {
            if(postagem.getId() == novaTroca.getOferta().getPostagemOrigem().getId()) {
                novaTroca.setConfirma_usuario01(true);
                break;
            } else if (postagem.getId() == novaTroca.getOferta().getPostagemOfertada().getId()) {
                novaTroca.setConfirma_usuario02(true);
                break;
            }
        }
        
        if(novaTroca.getConfirma_usuario01() && novaTroca.getConfirma_usuario02()) {
            novaTroca.setStatus(TrocaEnum.FINALIZADA);
        } 
        
        return this.trocaRepository.save(novaTroca);
    }
        
        //verifica a troca atualizada               
        //List<Postagem> postagemUsuarioLogado = userSpringSecurity.PostagemRepository.findByUsuario_Id(userSpringSecurity.getId());
        //novaTroca.getOferta().getPostagemOrigem().getUsuario().getId();
        //postagem.addAll();
        //List<Oferta> ofertasRelacionadas = new ArrayList<Oferta>();
        //Postagem postagemOrigem = oferta.getPostagemOrigem();
        //userSpringSecurity.getId()
                    
        
    
    
    
    public void deletarPorId(Long id) {
        
        Troca troca = encontrarPorId(id);

        if(!troca.getStatus().equals(TrocaEnum.FINALIZADA))
            throw new RuntimeException("A troca precisa ser finalizada para ser deletada");

        try {
            this.trocaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir a troca pois ela possui entidades relacionadas!");
        }

    }


} 






    
    
    


