package com.ti.Skanboo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.Skanboo.exceptions.AuthorizationException;
import com.ti.Skanboo.models.enums.UsuarioEnum;
import com.ti.Skanboo.repositories.PostagemRepository;
import com.ti.Skanboo.repositories.TrocaRepository;
import com.ti.Skanboo.repositories.UsuarioRepository;
import com.ti.Skanboo.security.UserSpringSecurity;

@Service
public class IndicadorService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private TrocaRepository trocaRepository;

    private void validarUsuarioAdmin() {

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

        if (!Objects.nonNull(userSpringSecurity) || !userSpringSecurity.hasRole(UsuarioEnum.ADMIN))
            throw new AuthorizationException("Acesso negado");
    }

    // --------------------------------------------------------------------------------------------
    // Indicadores de usuario
    // --------------------------------------------------------------------------------------------
    public Long quantidadeUsuariosCriadosMesAtual() {

        validarUsuarioAdmin();

        LocalDate dataInicial = LocalDate.now().withDayOfMonth(1);
        LocalDate dataFinal = LocalDate.now();

        return this.usuarioRepository.quantidadeUsuariosCriadosMesAtual(dataInicial, dataFinal);
    }

    public List<Object[]> quantidadeUsuariosCriadosPorMes() {

        validarUsuarioAdmin();
        int ano = LocalDate.now().getYear();

        return this.usuarioRepository.quantidadeUsuariosCriadosPorMes(ano);
    }

    // --------------------------------------------------------------------------------------------
    // Indicadores de postagens
    // --------------------------------------------------------------------------------------------
    public Long quantidadePostagensCriadasMesAtual() {

        validarUsuarioAdmin();

        LocalDate dataInicial = LocalDate.now().withDayOfMonth(1);
        LocalDate dataFinal = LocalDate.now();

        return this.postagemRepository.quantidadePostagensCriadasMesAtual(dataInicial, dataFinal);
    }

    public List<Object[]> quantidadePostagensCriadasPorMes() {

        validarUsuarioAdmin();
        int ano = LocalDate.now().getYear();

        return this.postagemRepository.quantidadePostagensCriadasPorMes(ano);
    }

    public List<Object[]> categoriasMaisDesejadas() {
        validarUsuarioAdmin();
        return this.postagemRepository.categoriasMaisDesejadas();
    }

    public List<Object[]> categoriasMaisOfertadas() {
        validarUsuarioAdmin();
        return this.postagemRepository.categoriasMaisOfertadas();
    }

    // --------------------------------------------------------------------------------------------
    // Indicadores de trocas
    // --------------------------------------------------------------------------------------------
    public Long quantidadeTrocasCriadasMesAtual() {

        validarUsuarioAdmin();

        LocalDate dataInicial = LocalDate.now().withDayOfMonth(1);
        LocalDate dataFinal = LocalDate.now();

        return this.trocaRepository.quantidadeTrocasCriadasMesAtual(dataInicial, dataFinal);
    }

    public List<Object[]> quantidadeTrocasCriadasPorMes() {

        validarUsuarioAdmin();
        int ano = LocalDate.now().getYear();

        return this.trocaRepository.quantidadeTrocasCriadasPorMes(ano);
    }

    public Long quantidadeTrocasFinalizadasMesAtual() {

        validarUsuarioAdmin();

        LocalDate dataInicial = LocalDate.now().withDayOfMonth(1);
        LocalDate dataFinal = LocalDate.now();

        return this.trocaRepository.quantidadeTrocasFinalizadasMesAtual(dataInicial, dataFinal);
    }

    public List<Object[]> quantidadeTrocasFinalizadasPorMes() {

        validarUsuarioAdmin();
        int ano = LocalDate.now().getYear();

        return this.trocaRepository.quantidadeTrocasFinalizadasPorMes(ano);
    }

    // --------------------------------------------------------------------------------------------
    // Indicadores compostos
    // --------------------------------------------------------------------------------------------
    public Float taxaCriacaoTrocas() {
        validarUsuarioAdmin();
        return (float) quantidadeTrocasCriadasMesAtual() / quantidadePostagensCriadasMesAtual();
    }

    public Float taxaConclusaoTrocas() {
        validarUsuarioAdmin();
        return (float) quantidadeTrocasFinalizadasMesAtual() / quantidadeTrocasCriadasMesAtual();
    }
}
