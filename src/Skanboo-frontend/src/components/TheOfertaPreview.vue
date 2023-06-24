<template>
  <section class="products">
    <h1>Resumo da oferta</h1>
    <div class="container">
      <div class="card">
        <h2>{{ postagemOrigem.titulo }}</h2>
        <div class="card-img">
          <img :src="postagemOrigem.foto" class="card-img-produto" />
        </div>
        <p><span class="titulo-categoria">Categoria:</span> {{ postagemOrigem.categoriaProduto }}</p>
        <p><span class="titulo-categoria">Interesse:</span> {{ postagemOrigem.categoriaProdutoDesejado }}</p>
      </div>

      <img src="../assets/flecha.png" class="flecha-img" />

      <div class="card">
        <h2>{{ postagemOfertada.titulo }}</h2>
        <div class="card-img">
          <img :src="postagemOfertada.foto" class="card-img-produto" />
        </div>
        <p><span class="titulo-categoria">Categoria:</span> {{ postagemOfertada.categoriaProduto }}</p>
        <p><span class="titulo-categoria">Interesse:</span> {{ postagemOfertada.categoriaProdutoDesejado }}</p>
      </div>

      <button class="oferta-botao" @click="criarOferta">
        <router-link :to="{ name: 'ofertasfeitasview' }">Negociar</router-link>
      </button>
    </div>
  </section>
</template>

<script>
import Postagem from '../services/PostagemService';
import Oferta from '../services/OfertaService';

export default {
  data() {
    return {
      oferta: {
        id: '',
        id_postagem_ofertada: '',
        id_postagem_origem: '',
        status: '',
      },
      postagemOfertada: {
        id: '',
        titulo: '',
        descricao: '',
        categoriaProduto: '',
        categoriaProdutoDesejado: '',
        status: '',
      },
      postagemOrigem: {
        id: '',
        titulo: '',
        descricao: '',
        categoriaProduto: '',
        categoriaProdutoDesejado: '',
        status: '',
      },
      postagens: [],
      ofertas: null,
    };
  },

  mounted() {
    if (!window.location.hash) {
      window.location = window.location + '#loaded';
      window.location.reload();
    }

    Postagem.exibirPostagensUsuarioLogado()
      .then((resposta) => {
        const postagens = resposta.data;
        this.postagens = postagens;
      })
      .catch((e) => console.log(e.message));

    const idOfertada = sessionStorage.getItem('idOfertada');
    Postagem.exibirInfoPostagem(idOfertada)
      .then((resposta) => {
        const postagem = resposta.data;
        this.postagemOfertada = postagem;
      })
      .catch((e) => console.log(e.message));

    const idOrigem = sessionStorage.getItem('idOrigem');
    Postagem.exibirInfoPostagem(idOrigem)
      .then((resposta) => {
        const postagem = resposta.data;
        this.postagemOrigem = postagem;
      })
      .catch((e) => console.log(e.message));
  },

  methods: {
    criarOferta() {
      const idOfertada = sessionStorage.getItem('idOfertada');
      const idOrigem = sessionStorage.getItem('idOrigem');

      console.log('Ofertada Id:', idOfertada);
      console.log('Origem Id:', idOrigem);

      Oferta.criar(idOfertada, idOrigem)
        .then(() => {
          alert('Oferta realizada com sucesso!');
          this.errors = [];
        })
        .catch((e) => console.log(e.message));
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
  width: 100px;
  height: 50px;
  margin-top: 150px;
  margin-left: 10px;
  margin-right: 10px;
}

.container {
  width: 720px;
  height: 500px;
  display: flex;
  justify-content: center;
  margin-top: 20px;
  gap: 20px;
  flex-wrap: wrap;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 0px;
  border: 1px solid #b2b4b6;
  border-radius: 4px;
}

.products {
  justify-content: center;
}

.card {
  display: block;
  width: 260px;
  height: 300px;
  background: #ffffff;
  flex: none;
}

.card p {
  margin-top: 10px;
}

.card img {
  margin-top: 10px;
  border-radius: 4px;
}

.card h2 {
  padding: 8px 8px 8px 0px;
  box-sizing: border-box;
  margin-left: 0px;
  margin-top: 5px;
  text-align: center;
  font-weight: bold;
}

.titulo-categoria {
    text-decoration: none;
  color: #515864;
  font-weight: bold;
}

.card-img-produto {
  height: 250px;
  width: 250px;
  object-fit: cover;
  display: block;
  margin-left: auto;
  margin-right: auto;
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

.oferta-botao {
  width: 300px;
  height: 32px;
  text-transform: capitalize;
  border: 1px solid #e9eced;
  transition: all 300ms;
}

.oferta-botao:hover {
  background: #f9dc5c;
  transition: 0.3s;
}
</style>
