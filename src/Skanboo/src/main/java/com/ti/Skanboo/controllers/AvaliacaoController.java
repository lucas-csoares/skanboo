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

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> encontrarPorId(@PathVariable Long id) {
        Avaliacao obj = this.avaliacaoService.encontrarPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Avaliacao>> listarAvaliacoesUsuarioAtivo() {
        List<Avaliacao> avaliacoes = this.avaliacaoService.listarAvaliacoesUsuarioAtivo();
        return ResponseEntity.ok().body(avaliacoes);
    }

    @PostMapping("/{id_troca}")
    public ResponseEntity<Void> criar(@PathVariable Long id_troca, @Valid @RequestBody Avaliacao obj) {

        this.avaliacaoService.criar(id_troca, obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        this.avaliacaoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}