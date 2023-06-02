package com.ti.Skanboo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.ti.Skanboo.models.Avaliacao;
import com.ti.Skanboo.services.AvaliacaoService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    
    // @Autowired
    // private TrocaService trocaService;

    @Autowired
    private AvaliacaoService avaliacaoService;


    @PostMapping("/{id_troca}")
    public ResponseEntity<Void> criar(@PathVariable Long id_troca, @Valid @RequestBody Avaliacao obj) {

        this.avaliacaoService.criar(id_troca, obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
