package com.ti.Skanboo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.Skanboo.models.Parceiro;

import com.ti.Skanboo.repositories.ParceiroRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class ParceiroService {

    @Autowired
    private ParceiroRepository parceiroRepository;

    public Parceiro encontrarPorId(Long id) throws Exception {

        Parceiro parceiro = this.parceiroRepository.findById(id).orElse(null);

        if(parceiro == null){
            throw new Exception("Parceiro nao encontrado");
        }

        return parceiro;
    }

    public List<Parceiro> listarParceirosCadastrados() {

        List<Parceiro> parceiro = this.parceiroRepository.findAll();

        return parceiro;
    }


    @Transactional
    public Parceiro criar(Parceiro obj) {
        obj.setId(null);

        return this.parceiroRepository.save(obj);
    }

    @Transactional
    public Parceiro atualizarPorId(Parceiro obj) throws Exception {

        Parceiro parceiro = encontrarPorId(obj.getId());

        parceiro.setNome(obj.getNome());
        parceiro.setCnpj(obj.getCnpj());
        parceiro.setPlano(obj.getPlano());

        return this.parceiroRepository.save(parceiro);
    }

    public void deletarPorId(Long id) throws Exception {

        encontrarPorId(id);

        try {
            this.parceiroRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir parceiro pois ele possui entidades relacionadas!");
        }
    }

    public @Valid Parceiro atualizarParceiroAtivo(@Valid Parceiro obj) {
        return null;
    }


}
