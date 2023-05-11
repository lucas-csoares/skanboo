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

import com.ti.Skanboo.models.EnderecoUsuario;
import com.ti.Skanboo.services.EnderecoUsuarioService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/endereco")
public class EnderecoUsuarioController {

    @Autowired
    private EnderecoUsuarioService enderecoUsuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoUsuario> encontrarPorId(@PathVariable Long id) {

        EnderecoUsuario obj = this.enderecoUsuarioService.encontrarPorId(id);

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<EnderecoUsuario>> listarEnderecoUsuario() {
        List<EnderecoUsuario> obj = this.enderecoUsuarioService.listarEnderecoUsuario();
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<EnderecoUsuario> criar(@Valid @RequestBody EnderecoUsuario obj) {

        this.enderecoUsuarioService.criar(obj);

        // Converte o contexto do user para adicionar o path a ele, e transformar em URI
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@Valid @RequestBody EnderecoUsuario obj, @PathVariable Long id) {

        obj.setId(id);
        obj = this.enderecoUsuarioService.atualizar(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        this.enderecoUsuarioService.deletar(id);

        return ResponseEntity.noContent().build();
    }

}
