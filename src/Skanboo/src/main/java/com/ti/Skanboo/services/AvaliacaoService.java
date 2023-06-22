package com.ti.Skanboo.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ti.Skanboo.exceptions.EntityNotFoundException;
import com.ti.Skanboo.exceptions.RatingCreationException;
import com.ti.Skanboo.exceptions.RatingNotFound;
import com.ti.Skanboo.models.Avaliacao;
import com.ti.Skanboo.models.Troca;
import com.ti.Skanboo.models.Usuario;
import com.ti.Skanboo.models.enums.TrocaEnum;
import com.ti.Skanboo.repositories.AvaliacaoRepository;
import com.ti.Skanboo.repositories.TrocaRepository;
import com.ti.Skanboo.repositories.UsuarioRepository;
import com.ti.Skanboo.security.UserSpringSecurity;
import java.util.stream.Stream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class AvaliacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TrocaRepository trocaRepository;

    @Autowired
    private TrocaService trocaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao encontrarPorId(Long id) {

        Avaliacao avaliacao = this.avaliacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliacao nao encontrada!"));

        trocaService.encontrarPorId(avaliacao.getTroca().getId());

        return avaliacao;
    }

    public List<Avaliacao> listarAvaliacoesUsuarioAtivo() {

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        Long usuarioId = userSpringSecurity.getId();
        List<Avaliacao> todasAvaliacoes = avaliacaoRepository.findAll();

        List<Avaliacao> avaliacoesDoUsuarioLogado = todasAvaliacoes.stream()
                .filter(avaliacao -> avaliacao.getUsuario().getId().equals(usuarioId))
                .collect(Collectors.toList());

        if (avaliacoesDoUsuarioLogado.isEmpty()) {
            throw new RatingNotFound("Usuário não possui avaliações");
        }

        return avaliacoesDoUsuarioLogado;
    }

    public Avaliacao criar(Long id_troca, Avaliacao obj) {

        Troca troca = this.trocaService.encontrarPorId(id_troca);
        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        Usuario usuario = this.usuarioService.encontrarPorId(userSpringSecurity.getId());
        int nota = obj.getNota();
        TrocaEnum statusTroca = troca.getStatus();

        avaliacaoRepository.existsByUsuarioAndTroca(usuario, troca);

        
       if (statusTroca == TrocaEnum.FINALIZADA) {
            if (nota >= 0 && nota <= 5) {
                boolean usuarioJaAvaliouTroca = avaliacaoRepository.existsByUsuarioAndTroca(usuario, troca);
                if (!usuarioJaAvaliouTroca) {
                    obj.setId(null);
                    obj.setNota(nota);
                    obj.setUsuario(usuario);
                    obj.setTroca(troca);
                    obj.setData(LocalDate.now());
                    obj.setHora(LocalTime.now());
                } else {
                   throw new RatingCreationException("A troca já foi avaliada");
                }
            } else {
                throw new IllegalArgumentException("A nota deve estar entre 0 e 5.");
            }
        } else {
            throw new RatingCreationException("Não é possível avaliar: A troca não foi finalizada");
        }
        this.avaliacaoRepository.save(obj);
        atualizarNotaFinal(obj);
        return obj;
    }
           
    public void deletarPorId(Long id) {

        encontrarPorId(id);

        try {
            this.avaliacaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao e possivel excluir avaliacao pois ela possui entidades relacionadas!");
        }
    }

    public void atualizarNotaFinal(Avaliacao avaliacao) {

        UserSpringSecurity userSpringSecurity = UsuarioService.authenticated();
        Usuario usuario =  null;

        if (trocaPertenceAoUsuarioPostagemOrigem(userSpringSecurity, avaliacao)) {
            usuario = avaliacao.getTroca().getOferta().getPostagemOfertada().getUsuario();
        } else if (trocaPertenceAoUsuarioPostagemOfertada(userSpringSecurity, avaliacao)) {
            usuario = avaliacao.getTroca().getOferta().getPostagemOrigem().getUsuario();
        } else {
            throw new RatingCreationException("Usuário avaliado não existe");
        }

        calcMedia(usuario);
        
    }
       

    public void calcMedia(Usuario usuario) {
        List<Troca> trocasRealizadas = trocaRepository.findByOferta_PostagemOrigem_UsuarioOrOferta_PostagemOfertada_Usuario(usuario);
        List<Long> usuariosQueRealizaramTroca = trocasRealizadas.stream()
            .flatMap(troca -> Stream.of(
                troca.getOferta().getPostagemOrigem().getUsuario().getId(),
                troca.getOferta().getPostagemOfertada().getUsuario().getId()))
            .distinct()
            .collect(Collectors.toList());

        List<Avaliacao> avaliacoes = avaliacaoRepository.findByUsuarioIds(usuariosQueRealizaramTroca);

        int mesAtual = LocalDate.now().getMonthValue();

        List<Avaliacao> avaliacoesMesAtual = avaliacoes.stream()
            .filter(avaliacao -> avaliacao.getData().getMonthValue() == mesAtual)
            .collect(Collectors.toList());

        int somaNotas = avaliacoesMesAtual.stream()
            .filter(avaliacao -> !avaliacao.getUsuario().equals(usuario))
            .mapToInt(Avaliacao::getNota)
            .sum();

        int quantidadeAvaliacoes = (int) avaliacoesMesAtual.stream()
            .filter(avaliacao -> !avaliacao.getUsuario().equals(usuario))
            .count();

        double mediaNotas = quantidadeAvaliacoes > 0 ? (double) somaNotas / quantidadeAvaliacoes : 0;

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String mediaFormatada = decimalFormat.format(mediaNotas);
        mediaNotas = Double.parseDouble(mediaFormatada);

        usuario.setNotaFinal(mediaNotas);
        usuarioRepository.save(usuario);
    }

    


    private Boolean trocaPertenceAoUsuarioPostagemOrigem(UserSpringSecurity userSpringSecurity, Avaliacao avaliacao) {
        return avaliacao.getTroca().getOferta().getPostagemOrigem().getUsuario().getId()
                .equals(userSpringSecurity.getId());
    }

    private Boolean trocaPertenceAoUsuarioPostagemOfertada(UserSpringSecurity userSpringSecurity, Avaliacao avaliacao) {
        return avaliacao.getTroca().getOferta().getPostagemOfertada().getUsuario().getId()
                .equals(userSpringSecurity.getId());
    }


}
