<template>
  <div>
    <h1>Graficos</h1>
    <div style="width: 800px"><canvas id="usuarios-criados-mes"></canvas></div>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import Indicadores from '../services/IndicadoresService';

export default {
  data() {
    return {
      quantidadeUsuariosCriadosMes: [{ mes: '', quantidade: '' }],
    };
  },

  mounted() {
    //todo: manter a funcao?
    Indicadores.quantidadeUsuariosCriadosMesAtual()
      .then((resposta) => {
        console.log('Mes atual -> ', resposta.data);
      })
      .catch((e) => {
        console.log(e);
      });

    Indicadores.quantidadeUsuariosCriadosMes()
      .then((resposta) => {
        let data = resposta.data;

        // Criar base de dados de usuarios criados por mes
        for (let i = 0; i < data.length; i++) {
          this.quantidadeUsuariosCriadosMes[i] = {
            mes: this.converterMes(data[i][0]),
            quantidade: data[i][1],
          };
        }
      })
      .then(() => {
        this.gerarGrafico();
      })
      .catch((e) => {
        console.log(e);
      });
  },

  methods: {
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

      return meses[indice-1];
    },

    gerarGrafico() {
      new Chart(document.getElementById('usuarios-criados-mes'), {
        type: 'bar',
        data: {
          labels: this.quantidadeUsuariosCriadosMes.map((row) => row.mes),
          datasets: [
            {
              label: 'Usuários criados por mês',
              data: this.quantidadeUsuariosCriadosMes.map((row) => row.quantidade),
            },
          ],
        },
      });
    },
  },
};
</script>

<style scoped></style>
