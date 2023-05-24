package com.ti.Skanboo.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.Skanboo.exceptions.AuthorizationException;
import com.ti.Skanboo.models.Parceiro;
import com.ti.Skanboo.models.enums.UsuarioEnum;
import com.ti.Skanboo.repositories.ParceiroRepository;
import com.ti.Skanboo.security.UserSpringSecurity;

import jakarta.transaction.Transactional;

@Service
public class ParceiroService {

    @Autowired
    private ParceiroRepository parceiroRepository;

    public Parceiro encontrarPorId(Long id) {

        Parceiro parceiro = this.parceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parceiro nao encontrado!"));

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !userSpringSecurity.hasRole(UsuarioEnum.ADMIN))
            throw new AuthorizationException("Acesso negado!");

        return parceiro;
    }

    public List<Parceiro> listarParceirosCadastrados() {

        List<Parceiro> parceiro = this.parceiroRepository.findAll();

        return parceiro;
    }

    @Transactional
    public Parceiro criar(Parceiro obj) {

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();

        if (Objects.isNull(userSpringSecurity) || !userSpringSecurity.hasRole(UsuarioEnum.ADMIN))
            throw new AuthorizationException("Acesso negado!");

        obj.setId(null);

        return this.parceiroRepository.save(obj);
    }

    @Transactional
    public Parceiro atualizarPorId(Parceiro obj) {

        Parceiro novoParceiro = encontrarPorId(obj.getId());

        novoParceiro.setNome(obj.getNome());
        novoParceiro.setCnpj(obj.getCnpj());
        novoParceiro.setPlano(obj.getPlano());
        novoParceiro.setFoto(obj.getFoto());
        novoParceiro.setContrato(obj.getContrato());

        return this.parceiroRepository.save(novoParceiro);
    }

    public void deletarPorId(Long id) {

        encontrarPorId(id);

        try {
            this.parceiroRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir parceiro pois ele possui entidades relacionadas!");
        }
    }
}
