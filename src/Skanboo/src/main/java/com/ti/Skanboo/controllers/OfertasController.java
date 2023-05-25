package com.ti.Skanboo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ti.Skanboo.models.Oferta;
import com.ti.Skanboo.models.Postagem;
import com.ti.Skanboo.services.OfertaService;
import com.ti.Skanboo.services.PostagemService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/oferta")
public class OfertasController {

    @Autowired
    private OfertaService ofertaService;

    @Autowired
    private PostagemService postagemService;

    @PostMapping("/{id_postagem_origem}/{id_postagem_ofertada}")
    public ResponseEntity<Void> criar(@PathVariable Long id_postagem_origem, @PathVariable Long id_postagem_ofertada) {

        Postagem postagemOrigem = this.postagemService.encontrarPorId(id_postagem_origem);
        Postagem postagemOfertada = this.postagemService.encontrarPorId(id_postagem_ofertada);
        Oferta obj = new Oferta(postagemOrigem, postagemOfertada);

        this.ofertaService.criar(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarRecusada(@Valid @RequestBody Oferta obj, @PathVariable Long id) {

        obj.setId(id);
        obj = this.ofertaService.atualizarPorId(obj);

        return ResponseEntity.noContent().build();
    }

}
