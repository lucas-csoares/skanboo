package com.ti.Skanboo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ti.Skanboo.models.Oferta;
import com.ti.Skanboo.models.Troca;
import com.ti.Skanboo.services.OfertaService;
import com.ti.Skanboo.services.TrocaService;

import jakarta.validation.Valid;

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


    @GetMapping("/lista")
    public ResponseEntity<List<Troca>> listarTrocasUsuarioAtivo() {
        List<Troca> trocas = this.trocaService.listarTrocasUsuarioAtivo();
        return ResponseEntity.ok().body(trocas);
    }

    @PostMapping("/{id_oferta}")
    public ResponseEntity<Void> criar(@PathVariable Long id_oferta) {

        this.trocaService.criar(id_oferta);
        

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id_oferta).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}") //id da troca
    public ResponseEntity<Void> atualizarPorId(@PathVariable Long id) {
        
        this.trocaService.atualizarPorId(id);
        return ResponseEntity.noContent().build();

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPorId(@PathVariable Long id) {
        this.trocaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
    
}
