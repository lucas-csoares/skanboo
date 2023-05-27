package com.ti.Skanboo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import com.ti.Skanboo.exceptions.AuthorizationException;
import com.ti.Skanboo.exceptions.OfferUpdateException;
import com.ti.Skanboo.models.Oferta;
import com.ti.Skanboo.models.Troca;
import com.ti.Skanboo.models.enums.OfertaEnum;
import com.ti.Skanboo.models.enums.TrocaEnum;
import com.ti.Skanboo.repositories.TrocaRepository;


@Service
public class TrocaService {
    
    @Autowired
    private TrocaRepository trocaRepository;

    @Autowired
    private OfertaService ofertaService;


    //*Método encontrarPorId
    public Troca encontrarPorId(Long id) {

        Troca troca = this.trocaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Troca nao encontrada!"));
        
        ofertaService.encontrarPorId(troca.getOferta().getId());
        
        return troca;
    }
    
    // Postagem postagemOrigem = oferta.getPostagemOrigem();
    // Postagem postagemOfertada = oferta.getPostagemOfertada();
    // Usuario usuarioPostagemOrigem = postagemOrigem.getUsuario();
    // Usuario usuarioPostagemOfertada = postagemOfertada.getUsuario();
    
    //*Método listarTroca
    public List<Troca> listarTrocasUsuarioAtivo() {
        List<List<Oferta>> ofertas = this.ofertaService.listarOfertasFeitasUsuarioAtivo();
        List<Troca> trocasRealizadas = new ArrayList<>();
    
        for (List<Oferta> ofertaList : ofertas) {
            for (Oferta oferta : ofertaList) {
                if (oferta.getStatus() == OfertaEnum.ACEITA) {
                    List<Troca> trocas = this.trocaRepository.findByOfertaId(oferta.getId());
    
                    if (!trocas.isEmpty()) {
                        trocasRealizadas.addAll(trocas);
                    }
                }
            }
        }

        if (trocasRealizadas.isEmpty())
            throw new RuntimeException("Usuario nao realizou nenhuma troca!");
    
        return trocasRealizadas;
    }
    
    



    //* Método criar troca
    public Troca criar(Troca obj) {

        //Verificando se oferta existe
        ofertaService.encontrarPorId(obj.getOferta().getId());

        //Verificar status da oferta
        OfertaEnum statusOferta = obj.getOferta().getStatus();
        
        if (statusOferta != OfertaEnum.ACEITA) {
            throw new AuthorizationException("Não é possível criar troca: oferta não aceita");
        } else{
            obj.setStatus(TrocaEnum.EM_ANDAMENTO);
            obj.setConfirma_usuario01(false);
            obj.setConfirma_usuario02(false);
        }
        return trocaRepository.save(obj);
    
    }


    public Troca atualizarPorId(Troca obj) {
        
        Troca novaTroca = encontrarPorId(obj.getId()); 


        if (novaTroca.getStatus().equals(TrocaEnum.FINALIZADA)) //verifica a troca original
            throw new OfferUpdateException("A Troca já foi finalizada, seu status nao pode ser atualizado!");
        
        //verifica a troca atualizada
        if(obj.getConfirma_usuario01() && obj.getConfirma_usuario02() && obj.getStatus().equals(TrocaEnum.FINALIZADA)) {
            novaTroca.setConfirma_usuario01(true);
            novaTroca.setConfirma_usuario01(true);
            novaTroca.setStatus(TrocaEnum.FINALIZADA);
        } else if(obj.getConfirma_usuario01() != obj.getConfirma_usuario02()) {
            throw new OfferUpdateException("A troca não pode ser atualizada, os dois usuários tem que confirmar recebimento do produto");
        } else if(obj.getConfirma_usuario01() && obj.getConfirma_usuario02() && obj.getStatus().equals(TrocaEnum.EM_ANDAMENTO)){
            throw new OfferUpdateException("A troca não pode ser atualizada, os dois usuários finalizaram troca");
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






    
    
    


