<template>
  <div>
    <h1>Graficos</h1>
    <div class="grid">
      <div class="grid-medio gf-usuarios-mes"><canvas id="usuarios-mes"></canvas></div>

      <div class="grid-pequeno gf-categorias-desejadas">
        <h1>Categorias mais desejadas</h1>
        <canvas id="categorias-desejadas"> </canvas>
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
      quantidadeUsuariosCriadosMes: [{ mes: '', quantidade: '' }],
      quantidadePostagensCriadasMes: [{ mes: '', quantidade: '' }],
      categoriasMaisDesejadas: [{ categoria: '', quantidade: '' }],
      categoriasMaisOfertadas: [{ categoria: '', quantidade: '' }],
    };
  },

  mounted() {
    this.carregarGraficos();

    let canvas = document.getElementById('categorias-relacao');

    this.gerarGraficoRADAR(this.categoriasMaisDesejadas, this.categoriasMaisOfertadas, canvas, 'Categorias');
  },

  methods: {
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
          .catch((e) => {
            console.log(e);
          });
      });
    },

    // -----------------------------------------------------------------
    // Fucoes auxiliares para a geracao de graficos
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


      new Chart(canvas, {
        type: 'radar',
        data: {
          labels: categorias,
          datasets: [
            {
              label: titulo,
              data: this.formatarDadosCategorias(indicador01, categorias),
            },
            {
              label: titulo,
              // data: this.formatarDadosGraficoRADAR(indicador02, categorias),
            },
          ],
        },
      });
    },

    formatarDadosCategorias(indicador, categorias) {
      let obj = indicador;

      console.log(obj);
    },

    ehCategoria(obj) {
      return obj.categoria === 'Música';
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
  grid-column: 1 / span 4;
  height: 500px;
}

.grid-medio {
  grid-column: 1 / span 3;
  height: 500px;
}

.grid-pequeno {
  background-color: lightcoral;
  grid-column: 4 / span 1;
  height: 500px;
}

.gf-usuarios-mes,
.gf-categorias-desejadas {
  grid-row: 1;
  background-color: lightpink;
}

.gf-postagens-mes,
.gf-categorias-ofertadas {
  grid-row: 2;
  background-color: lightgray;
}

.gf-categorias-relacao {
  grid-row: 3;
}
</style>
