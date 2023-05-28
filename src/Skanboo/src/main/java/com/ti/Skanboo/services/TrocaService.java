package com.ti.Skanboo.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ti.Skanboo.exceptions.EntityNotFoundException;
import com.ti.Skanboo.exceptions.ExchangeCreationException;
import com.ti.Skanboo.exceptions.ExchangeNotFound;
import com.ti.Skanboo.exceptions.ExchangeUpdateException;
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
    
                   
    public List<Troca> listarTrocasUsuarioAtivo() {
        
        
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        Long usuarioId = userSpringSecurity.getId();
        List<Troca> todasTrocas = trocaRepository.findAll();

        List<Troca> trocasDoUsuarioLogado = todasTrocas.stream()
        .filter(troca -> troca.getOferta().getPostagemOrigem().getUsuario().getId().equals(usuarioId)
            || troca.getOferta().getPostagemOfertada().getUsuario().getId().equals(usuarioId))
        .collect(Collectors.toList());

        if (trocasDoUsuarioLogado.isEmpty()) {
            throw new ExchangeNotFound("Usuário não possui trocas!");
        }

        return trocasDoUsuarioLogado;
    
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
            throw new ExchangeCreationException("Não é possível criar troca: oferta não aceita");
        }
        
        return this.trocaRepository.save(troca);
    
    }


    public Troca atualizarPorId(Long id_troca) {
        
        
        Troca novaTroca = encontrarPorId(id_troca); 

        if (novaTroca.getStatus().equals(TrocaEnum.FINALIZADA)) 
            throw new ExchangeUpdateException("A Troca já foi finalizada, seu status nao pode ser atualizado!");


        
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






    
    
    


