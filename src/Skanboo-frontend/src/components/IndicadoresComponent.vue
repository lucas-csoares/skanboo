<template>
  <div>
    <h1>Indicadores de desempenho</h1>
    <div class="grid">
      <div class="gf-categorias-desejadas linha-01 item">
        <h4>Categorias mais desejadas</h4>
        <canvas id="categorias-desejadas"></canvas>
      </div>

      <div class="gf-categorias-ofertadas linha-01 item">
        <h4>Categorias mais ofertadas</h4>
        <canvas id="categorias-ofertadas"></canvas>
      </div>

      <div class="gf-categorias-relacao linha-01 item">
        <h4>Métricas de postagem</h4>
        <canvas id="categorias-relacao"></canvas>
      </div>

      <div class="grid-indicadores-mensais">
        <div class="linha-01 card-indicador item">
          <p>Novos usuários</p>
          <p class="indicador indicador-qtde-usuario"></p>
        </div>

        <div class="linha-01 card-indicador item">
          <p>Taxa criação de trocas</p>
          <p class="indicador indicador-taxa-criacao-trocas"></p>
        </div>

        <div class="linha-01 card-indicador item">
          <p>Taxa conclusão trocas</p>
          <p class="indicador indicador-taxa-conclusao-trocas"></p>
        </div>
      </div>

      <div class="gf-usuarios-mes linha-02 item"><canvas id="usuarios-mes"></canvas></div>

      <div class="gf-postagens-mes linha-02 item"><canvas id="postagens-mes"></canvas></div>
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
      'Categorias mais desejadas',
      'Categorias mais ofertadas',
      'categorias-relacao'
    );
    this.quantidadeUsuariosCriadosMesAtual();
    this.taxaCriacaoTrocas();
    this.taxaConclusaoTrocas();
    this.calcularSpanGrid();
  },

  methods: {
    quantidadeUsuariosCriadosMesAtual() {
      Indicadores.quantidadeUsuariosCriadosMesAtual()
        .then((resposta) => {
          document.querySelector('.indicador-qtde-usuario').innerHTML = resposta.data;
        })
        .catch((e) => console.log(e));
    },

    taxaCriacaoTrocas() {
      Indicadores.taxaCriacaoTrocas()
        .then((resposta) => {
          document.querySelector('.indicador-taxa-criacao-trocas').innerHTML = `${Math.round(resposta.data * 100)}%`;
        })
        .catch((e) => console.log(e));
    },

    taxaConclusaoTrocas() {
      Indicadores.taxaConclusaoTrocas()
        .then((resposta) => {
          document.querySelector('.indicador-taxa-conclusao-trocas').innerHTML = `${Math.round(resposta.data * 100)}%`;
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

    gerarGraficoRADAR(indicador01, indicador02, titulo01, titulo02, canvas) {
      const categorias = ['Eletrônicos', 'Moda e beleza', 'Música', 'Casa', 'Serviços'];

      (async () => {
        const resposta = await this.formatarDadosCategorias(indicador01, indicador02, categorias);

        new Chart(canvas, {
          type: 'radar',
          data: {
            labels: categorias,
            fill: true,
            datasets: [
              {
                label: titulo01,
                data: resposta[0],
              },
              {
                label: titulo02,
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

    calcularSpanGrid() {
      const quantidadeLinhasGrid = 2;
      const quantidadeColunasGrid = 4;

      for (let i = 1; i <= quantidadeLinhasGrid; i++) {
        let itemLinha = document.querySelectorAll(`.linha-0${i}`);
        this.setGridColumnRow(itemLinha, quantidadeColunasGrid);
      }
    },

    setGridColumnRow(itemLinha, quantidadeColunasGrid) {
      const qtdeItens = itemLinha.length;

      for (let i = 0; i < itemLinha.length; i++) 
         itemLinha[i].style.setProperty('grid-column', `span ${quantidadeColunasGrid / qtdeItens}`);
      
    },
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.grid {
  width: 100%;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(2, 1fr);
  gap: 10px;
  padding: 15px;
}

.linha-01 {
  grid-row: 1;
}

.linha-02 {
  grid-row: 2;
}

.linha-03 {
  grid-row: 3;
}

.item {
  background-color: #fafafa;
  border: 1px solid #e5e9eb;
  border-radius: 5px;
}

.grid-indicadores-mensais {
  display: flex;
  flex-wrap: wrap;
}

.card-indicador {
  width: 100%;
  margin: 5px 0 0 0;
}

.card-indicador p:nth-of-type(1) {
  font-weight: bold;
}

.card-indicador p:nth-of-type(2) {
  font-weight: bold;
  font-size: 2em;
}
</style>
