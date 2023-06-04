<template>
  <div>
    <h1>Graficos</h1>
    <div class="grid">
      <div class="grid-indicador-mensal">
        <h1>Usuários criados no mês</h1>
        <p class="indicador indicador-qtde-usuario"></p>
      </div>

      <div class="grid-indicador-mensal">
        <h1>Postagens criadas no mês</h1>
        <p class="indicador indicador-qtde-postagem"></p>
      </div>

      <div class="grid-indicador-mensal">
        <h1>Trocas criadas no mês</h1>
        <p class="indicador indicador-qtde-troca"></p>
      </div>

      <div class="grid-medio gf-usuarios-mes"><canvas id="usuarios-mes"></canvas></div>

      <div class="grid-pequeno gf-categorias-desejadas">
        <h1>Categorias mais desejadas</h1>
        <canvas id="categorias-desejadas"></canvas>
      </div>

      <div class="grid-medio gf-postagens-mes"><canvas id="postagens-mes"></canvas></div>

      <div class="grid-pequeno gf-categorias-ofertadas">
        <h1>Categorias mais ofertadas</h1>
        <canvas id="categorias-ofertadas"></canvas>
      </div>

      <div class="grid-pequeno gf-categorias-relacao"><canvas id="categorias-relacao"></canvas></div>
    </div>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import Indicadores from '../services/IndicadoresService';

export default {
  data() {
    return {
      quantidadeUsuariosCriadosMes: [],
      quantidadePostagensCriadasMes: [],
      categoriasMaisDesejadas: [],
      categoriasMaisOfertadas: [],
    };
  },

  mounted() {
    this.carregarGraficos();
    this.gerarGraficoRADAR(
      this.categoriasMaisDesejadas,
      this.categoriasMaisOfertadas,
      'categorias-relacao',
      'Categorias'
    );
    this.quantidadeUsuariosCriadosMesAtual();
    this.quantidadePostagensCriadasMesAtual();
    this.quantidadeTrocasCriadasMesAtual();
  },

  methods: {
    quantidadeUsuariosCriadosMesAtual() {
      Indicadores.quantidadeUsuariosCriadosMesAtual()
        .then((resposta) => {
          document.querySelector('.indicador-qtde-usuario').innerHTML = resposta.data;
        })
        .catch((e) => console.log(e));
    },

    quantidadePostagensCriadasMesAtual() {
      Indicadores.quantidadePostagensCriadasMesAtual()
        .then((resposta) => {
          document.querySelector('.indicador-qtde-postagem').innerHTML = resposta.data;
        })
        .catch((e) => console.log(e));
    },

    quantidadeTrocasCriadasMesAtual() {
      Indicadores.quantidadeTrocasCriadasMesAtual()
        .then((resposta) => {
          document.querySelector('.indicador-qtde-troca').innerHTML = resposta.data;
        })
        .catch((e) => console.log(e));
    },

    carregarGraficos() {
      const graficos = [
        {
          indicador: this.quantidadeUsuariosCriadosMes,
          parametro: Indicadores.quantidadeUsuariosCriadosMes,
          canvasId: 'usuarios-mes',
          titulo: 'Usuários criados por mês',
          tipo: 'bar',
          eixo01: 'mes',
          eixo02: 'quantidade',
        },
        {
          indicador: this.quantidadePostagensCriadasMes,
          parametro: Indicadores.quantidadePostagensCriadosMes,
          canvasId: 'postagens-mes',
          titulo: 'Postagens criados por mês',
          tipo: 'bar',
          eixo01: 'mes',
          eixo02: 'quantidade',
        },
        {
          indicador: this.categoriasMaisDesejadas,
          parametro: Indicadores.categoriasMaisDesejadas,
          canvasId: 'categorias-desejadas',
          titulo: 'Categorias mais desejadas',
          tipo: 'doughnut',
          eixo01: 'categoria',
          eixo02: 'quantidade',
        },
        {
          indicador: this.categoriasMaisOfertadas,
          parametro: Indicadores.categoriasMaisOfertadas,
          canvasId: 'categorias-ofertadas',
          titulo: 'Categorias mais ofertadas',
          tipo: 'doughnut',
          eixo01: 'categoria',
          eixo02: 'quantidade',
        },
      ];

      graficos.forEach((grafico) => {
        grafico
          .parametro()
          .then((resposta) => {
            this.carregarDados(resposta.data, grafico.indicador, grafico.eixo01, grafico.eixo02);
          })
          .then(() => {
            this.gerarGrafico(
              grafico.indicador,
              grafico.canvasId,
              grafico.titulo,
              grafico.tipo,
              grafico.eixo01,
              grafico.eixo02
            );
          })
          .catch((e) => console.log(e));
      });
    },

    // -----------------------------------------------------------------
    // Fucoes auxiliares para a geracao de graficos
    // Eixos01 e 02 se referem aos eixos do grafico (X e Y, respectivamente)
    // -----------------------------------------------------------------
    carregarDados(dados, indicador, eixo01, eixo02) {
      for (let i = 0; i < dados.length; i++) {
        indicador[i] = {
          [eixo01]: eixo01 == 'mes' ? this.converterMes(dados[i][0]) : dados[i][0],
          [eixo02]: dados[i][1],
        };
      }
    },

    converterMes(indice) {
      const meses = [
        'Janeiro',
        'Fevereiro',
        'Março',
        'Abril',
        'Maio',
        'Junho',
        'Julho',
        'Agosto',
        'Setembro',
        'Outubro',
        'Novembro',
        'Dezembro',
      ];
      return meses[indice - 1];
    },

    gerarGrafico(indicador, canvas, titulo, tipo, eixo01, eixo02) {
      this[`gerarGrafico${tipo.toUpperCase()}`](indicador, canvas, titulo, eixo01, eixo02);
    },

    gerarGraficoBAR(indicador, canvas, titulo, eixo01, eixo02) {
      new Chart(canvas, {
        type: 'bar',
        data: {
          labels: indicador.map((obj) => obj[eixo01]),
          datasets: [
            {
              label: titulo,
              data: indicador.map((obj) => obj[eixo02]),
            },
          ],
        },
      });
    },

    gerarGraficoDOUGHNUT(indicador, canvas, titulo, eixo01, eixo02) {
      new Chart(canvas, {
        type: 'doughnut',
        data: {
          labels: indicador.map((obj) => obj[eixo01]),
          datasets: [
            {
              label: titulo,
              data: indicador.map((obj) => obj[eixo02]),
              hoverOffset: 15,
            },
          ],
        },
      });
    },

    gerarGraficoRADAR(indicador01, indicador02, canvas, titulo) {
      const categorias = ['Eletrônicos', 'Moda e beleza', 'Música', 'Casa', 'Serviços'];

      (async () => {
        const resposta = await this.formatarDadosCategorias(indicador01, indicador02, categorias);

        new Chart(canvas, {
          type: 'radar',
          data: {
            labels: categorias,
            datasets: [
              {
                label: titulo,
                data: resposta[0],
              },
              {
                label: titulo,
                data: resposta[1],
              },
            ],
          },
        });
      })();
    },

    async formatarDadosCategorias(indicador01, indicador02, categorias) {
      const resposta01 = await Indicadores.categoriasMaisDesejadas().then((resposta) => {
        this.carregarDados(resposta.data, indicador01, 'categoria', 'quantidade');
        return categorias.map((categoria) => {
          const item = indicador01.find((obj) => obj.categoria === categoria);
          return item ? item.quantidade : 0;
        });
      });

      const resposta02 = await Indicadores.categoriasMaisOfertadas().then((resposta) => {
        this.carregarDados(resposta.data, indicador02, 'categoria', 'quantidade');
        return categorias.map((categoria) => {
          const item = indicador02.find((obj) => obj.categoria === categoria);
          return item ? item.quantidade : 0;
        });
      });

      return [resposta01, resposta02];
    },
  },
};
</script>

<style scoped>
.grid {
  background: lightblue;
  width: 100%;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(4, 1fr);
  gap: 10px;
}

.grid-grande {
  background-color: lightgoldenrodyellow;
  grid-column: 1 / span 4;
  height: 500px;
}

.grid-medio {
  background-color: lightpink;
  grid-column: 1 / span 3;
  height: 500px;
}

.grid-pequeno {
  background-color: lightgray;
  grid-column: 4 / span 1;
  height: 500px;
}

.gf-usuarios-mes,
.gf-categorias-desejadas {
  grid-row: 2;
}

.gf-postagens-mes,
.gf-categorias-ofertadas {
  grid-row: 3;
}

.gf-categorias-relacao {
  grid-row: 1;
}
</style>
