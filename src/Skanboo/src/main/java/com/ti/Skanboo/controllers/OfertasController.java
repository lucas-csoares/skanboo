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

    @GetMapping("/{id}")
    public ResponseEntity<Oferta> encontrarPorId(@PathVariable Long id) {

        Oferta obj = this.ofertaService.encontrarPorId(id);

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/listar-feitas/me")
    public ResponseEntity<List<List<Oferta>>> listarOfertasFeitasUsuarioAtivo() {
        List<List<Oferta>> obj = this.ofertaService.listarOfertasFeitasUsuarioAtivo();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/listar-recebidas/me")
    public ResponseEntity<List<List<Oferta>>> listarOfertasRecebidasUsuarioAtivo() {
        List<List<Oferta>> obj = this.ofertaService.listarOfertasRecebidasUsuarioAtivo();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/listar-ofertas-postagem/{id}")
    public ResponseEntity<List<Oferta>> listarOfertasPostagem(@PathVariable Long id) {
        List<Oferta> obj = this.ofertaService.listarOfertasPostagem(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/{id-postagem-origem}/{id-postagem-ofertada}")
    public ResponseEntity<Void> criar(@PathVariable("id-postagem-origem") Long id_postagem_origem, @PathVariable("id-postagem-ofertada") Long id_postagem_ofertada) {

        Postagem postagemOrigem = this.postagemService.encontrarPorId(id_postagem_origem);
        Postagem postagemOfertada = this.postagemService.encontrarPorId(id_postagem_ofertada);
        Oferta obj = new Oferta(postagemOrigem, postagemOfertada);

        this.ofertaService.criar(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarPorId(@Valid @RequestBody Oferta obj, @PathVariable Long id) {

        obj.setId(id);
        obj = this.ofertaService.atualizarPorId(obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        this.ofertaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
