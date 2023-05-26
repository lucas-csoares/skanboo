package com.ti.Skanboo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.ti.Skanboo.models.Oferta;
import com.ti.Skanboo.models.Postagem;
import com.ti.Skanboo.models.Troca;
import com.ti.Skanboo.repositories.OfertaRepository;
import com.ti.Skanboo.repositories.TrocaRepository;

public class TrocaService {
    
    @Autowired
    private TrocaRepository trocaRepository;

    @Autowired
    private OfertaRepository ofertaRepository;


    //*Método encontrarPorId

    //*Método listarTroca


    //* Método criar troca
    public Troca criar(Troca obj) {
        
        // Verificar se a oferta associada à troca existe
        Long ofertaId = obj.getOferta().getId();
        Oferta oferta = ofertaRepository.findById(ofertaId)
        .orElseThrow(() -> new RuntimeException("Oferta não encontrada!"));

        // Verifica se a oferta associada existe e tem status "aceitar"
        // if (obj.getOferta() == null || !"aceitar".equals(obj.getOferta().getStatus())) {
        //     throw new IllegalArgumentException("Não é possível criar a troca. A oferta associada não existe ou não está com status aceitar.");
        // }





        //Verificar status da oferta
        String statusOferta = obj.getOferta().getStatus();
        if (statusOferta.equalsIgnoreCase("aceita")) {
            obj.setStatus("Em andamento");
        }

        return trocaRepository.save(obj);

    }
}
