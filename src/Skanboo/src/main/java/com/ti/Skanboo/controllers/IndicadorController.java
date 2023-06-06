package com.ti.Skanboo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ti.Skanboo.services.IndicadorService;

@RestController
@Validated
@RequestMapping("/indicador")
public class IndicadorController {

    @Autowired
    private IndicadorService indicadorService;

    // --------------------------------------------------------------------------------------------
    // Indicadores de usuario
    // --------------------------------------------------------------------------------------------
    @GetMapping("/usuarios-criados-mes-atual")
    public ResponseEntity<Long> quantidadeUsuariosCriadosMesAtual() {

        Long resp = this.indicadorService.quantidadeUsuariosCriadosMesAtual();
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/usuarios-criados-mes")
    public ResponseEntity<List<Object[]>> quantidadeUsuariosCriadosPorMes() {

        List<Object[]> resp = this.indicadorService.quantidadeUsuariosCriadosPorMes();
        return ResponseEntity.ok().body(resp);
    }

    // --------------------------------------------------------------------------------------------
    // Indicadores de postagens
    // --------------------------------------------------------------------------------------------
    @GetMapping("/postagens-criadas-mes-atual")
    public ResponseEntity<Long> quantidadePostagensCriadasMesAtual() {

        Long resp = this.indicadorService.quantidadePostagensCriadasMesAtual();
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/postagens-criadas-mes")
    public ResponseEntity<List<Object[]>> quantidadePostagensCriadasPorMes() {

        List<Object[]> resp = this.indicadorService.quantidadePostagensCriadasPorMes();
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/categorias-mais-desejadas")
    public ResponseEntity<List<Object[]>> categoriasMaisDesejadas() {

        List<Object[]> resp = this.indicadorService.categoriasMaisDesejadas();
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/categorias-mais-ofertadas")
    public ResponseEntity<List<Object[]>> categoriasMaisOfertadas() {

        List<Object[]> resp = this.indicadorService.categoriasMaisOfertadas();
        return ResponseEntity.ok().body(resp);
    }

    // --------------------------------------------------------------------------------------------
    // Indicadores de trocas
    // --------------------------------------------------------------------------------------------
    @GetMapping("/trocas-criados-mes-atual")
    public ResponseEntity<Long> quantidadeTrocasCriadasMesAtual() {

        Long resp = this.indicadorService.quantidadeTrocasCriadasMesAtual();
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/trocas-criados-mes")
    public ResponseEntity<List<Object[]>> quantidadeTrocasCriadasPorMes() {

        List<Object[]> resp = this.indicadorService.quantidadeTrocasCriadasPorMes();
        return ResponseEntity.ok().body(resp);
    }

    // --------------------------------------------------------------------------------------------
    // Indicadores compostos
    // --------------------------------------------------------------------------------------------
    @GetMapping("/taxa-criacao-trocas")
    public ResponseEntity<Float> taxaCriacaoTrocas() {

        Float resp = this.indicadorService.taxaCriacaoTrocas();
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/taxa-conclusao-trocas")
    public ResponseEntity<Float> taxaConclusaoTrocas() {

        Float resp = this.indicadorService.taxaConclusaoTrocas();
        return ResponseEntity.ok().body(resp);
    }

}
