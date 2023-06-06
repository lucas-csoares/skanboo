package com.ti.Skanboo.controllers;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> encontrarPorId(@PathVariable Long id) {
 
        Usuario obj = this.usuarioService.encontrarPorId(id);

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/me")
    public ResponseEntity<Usuario> listarInformacoesUsuarioAtivo() {

        Usuario obj = this.usuarioService.listarInformacoesUsuarioAtivo();

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> listarUsuariosCadastrados() {

        List<Usuario> obj = this.usuarioService.listarUsuariosCadastrados();
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> criar(@Valid @RequestBody Usuario obj) {

        this.usuarioService.criar(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarPorId(@Valid @RequestBody Usuario obj, @PathVariable Long id) {

        obj.setId(id);
        obj = this.usuarioService.atualizarPorId(obj);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/me")
    public ResponseEntity<Void> atualizarUsuarioAtivo(@Valid @RequestBody Usuario obj) {

        obj = this.usuarioService.atualizarUsuarioAtivo(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {

        this.usuarioService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deletarUsuarioAtivo() {

        this.usuarioService.deletarUsuarioAtivo();

        return ResponseEntity.noContent().build();
    }
}
