package com.ti.Skanboo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ti.Skanboo.models.Troca;
import com.ti.Skanboo.services.OfertaService;
import com.ti.Skanboo.services.TrocaService;

@RestController
@Validated
@RequestMapping("/troca")
public class TrocaController {
    
    @Autowired
    private TrocaService trocaService;

    @Autowired 
    private OfertaService ofertaService;

    @GetMapping("/{id}")
    public ResponseEntity<Troca> encontrarPorId(@PathVariable Long id) {
        
        Troca obj = this.trocaService.encontrarPorId(id);
        
        return ResponseEntity.ok().body(obj);
    }


    @GetMapping("/me")
    public ResponseEntity<List<Troca>> listarTrocasUsuarioAtivo() {
        List<Troca> trocas = this.trocaService.listarTrocasUsuarioAtivo();
        return ResponseEntity.ok().body(trocas);
    }

    
}
