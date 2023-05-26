package com.ti.Skanboo.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.ti.Skanboo.exceptions.AuthorizationException;
import com.ti.Skanboo.models.Oferta;
import com.ti.Skanboo.models.Postagem;
import com.ti.Skanboo.models.Troca;
import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.models.enums.OfertaEnum;
import com.ti.Skanboo.models.enums.TrocaEnum;
import com.ti.Skanboo.repositories.OfertaRepository;
import com.ti.Skanboo.repositories.TrocaRepository;
import com.ti.Skanboo.security.UserSpringSecurity;

public class TrocaService {
    
    @Autowired
    private TrocaRepository trocaRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

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
    
    // public void algumMetodo(Troca troca) {
    //     // Obtém a oferta da troca
    //     Oferta oferta = troca.getOferta();

    //     // Obtém o ID das postagens da oferta
    //     Long idPostagem1 = oferta.getIdPostagem1();
    //     Long idPostagem2 = oferta.getIdPostagem2();

    //     // Obtém o ID do usuário referente à postagem 1 e 2 (supondo que você tenha um método para isso)
    //     Long idUsuarioPostagem1 = getIdUsuarioDaPostagem(idPostagem1);
    //     Long idUsuarioPostagem2 = getIdUsuarioDaPostagem(idPostagem2);

    //     // Chama o método em OfertaService com base nas informações obtidas
    //     ofertaService.metodoDaOfertaService(idUsuarioPostagem1, idUsuarioPostagem2);
    // }

    // private Long getIdUsuarioDaPostagem(Long idPostagem) {
    //     // Lógica para obter o ID do usuário com base no ID da postagem
    //     // ...

    //     return idUsuario;
    // }



    //*Método listarTroca


    //* Método criar troca
    public Troca criar(Troca obj) {

        //*Verificando se oferta existe
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
        
    }



} 