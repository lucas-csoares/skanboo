<template>
  <section class="products">
    <h1>Produtos oferecidos</h1>
    <div v-for="oferta in ofertas" :key="oferta[0].id" class="container">
      <div class="card">
        <h2>{{ oferta[0].postagemOfertada.titulo }}</h2>
        <div class="card-img">
          <img :src="oferta[0].postagemOfertada.foto" class="card-img-produto" />
        </div>
        <div class="div-botao">
          <button>
            <router-link :to="{ name: 'TheProductPage', params: { id: oferta[0].postagemOfertada.id } }"
              >Ver produto</router-link
            >
          </button>
        </div>
      </div>

      <img src="../assets/setaDireita.png" class="flecha-img" />

      <div class="card">
        <h2>{{ oferta[0].postagemOrigem.titulo }}</h2>
        <div class="card-img">
          <img :src="oferta[0].postagemOrigem.foto" class="card-img-produto" />
        </div>
        <div class="div-botao">
          <button>
            <router-link :to="{ name: 'TheProductPage', params: { id: oferta[0].postagemOrigem.id } }"
              >Ver produto</router-link
            >
          </button>
        </div>
      </div>
      <div class="div-botao">
        <button @click="cancelarOferta(oferta[0].id)">Cancelar oferta</button>
      </div>
    </div>
  </section>
</template>

<script>
import Oferta from '../services/OfertaService';

export default {
  data() {
    return {
      ofertas: [],
      oferta: {
        id: '',
        status: '',
      }
    };
  },

  mounted() {
    Oferta.exibirOfertasFeitas()
      .then((resposta) => {
        const ofertas = resposta.data;
        this.ofertas = ofertas;
      })
      .catch((e) => console.log(e.message));
  },

  methods: {
    criarOferta() {
      const idOfertada = sessionStorage.getItem('idOfertada');
      const idOrigem = sessionStorage.getItem('idOrigem');

      console.log('Origem Id:', idOfertada);
      console.log('Origem Id:', idOrigem);

      Oferta.criar(idOfertada, idOrigem)
        .then(() => {
          alert('Oferta realizada com sucesso!');
          this.errors = [];
        })
        .catch((e) => console.log(e.message));
    },

    cancelarOferta(id) {
      this.oferta.status = 'RECUSADA';

      Oferta.atualizar(id, this.oferta)
        .then(() => {
          alert('Oferta recusada com sucesso!');
        })
        .catch((e) => console.log(e));
    },
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}

body,
h1,
h2,
h3,
ul,
li,
p,
dd,
dt,
dl {
  margin: 0px;
  padding: 0px;
}

a {
  text-decoration: none;
  color: #515864;
}

img {
  display: block;
  max-width: 100%;
}

.flecha-img {
  width: 80px;
  height: 80px;
  margin-top: 150px;
  margin-left: 10px;
  margin-right: 10px;
}

.container {
  width: 720px;
  display: flex;
  justify-content: left;
  margin-top: 20px;
  gap: 20px;
  flex-wrap: wrap;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 30px;
  border: 1px solid #e5e9eb;
  border-radius: 4px;
}

.products {
  justify-content: center;
}

.card {
  display: block;
  width: 274px;
  height: 370px;
  background: #ffffff;
  flex: none;
}

.card img {
  margin-top: 10px;
}

.card h2 {
  padding: 8px;
  box-sizing: border-box;
  margin-left: 5px;
  margin-top: 5px;
}

.card-img-produto {
  height: 250px;
  width: 250px;
  object-fit: cover;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

.categoria-produto {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 240px;
  height: 32px;
  background: #fdfcfc;
  border: 1px solid #36435a;
  border-radius: 4px;
  font-weight: 600;
  color: #36435a;
  margin-left: 15px;
}

h2 {
  font-size: 18px;
  color: rgb(12, 23, 29);
  kerning: -0.6%;
  font-weight: 400;
  text-align: left;
}

h1 {
  text-align: center;
}
</style>
