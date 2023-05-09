package com.ti.Skanboo.controllers;

import java.net.URI;
//import java.util.List;
//import java.util.Optional;

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

import com.ti.Skanboo.models.Postagem;
import com.ti.Skanboo.services.PostagemService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/postagem")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> encontrarPorId(@PathVariable Long id) {

        Postagem obj = this.postagemService.encontrarPorId(id);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> criar(@Valid @RequestBody Postagem obj) {

        this.postagemService.criar(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@Valid @RequestBody Postagem obj, @PathVariable Long id) {

        obj.setId(null);

        this.postagemService.atualizar(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        this.postagemService.deletar(id);

        return ResponseEntity.noContent().build();
    }

}