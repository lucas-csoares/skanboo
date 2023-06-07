<template>
  <section class="products">
    <h1>Anúncios para você</h1>

    <div class="carrossel">
      <div class="carrossel-container" :style="{ transform: `translateX(-${currentSlideIndex * 100}%)` }">
        <div class="carrossel-item" v-for="postagem in carrosselPostagens" :key="postagem.id">
          <router-link :to="{ name: 'TheProductPage', params: { id: postagem.id } }">
            <div class="card-img-carrossel"><img :src="postagem.foto" alt="" class="card-img" /></div>
          </router-link>
        </div>
      </div>

      <div class="carrossel-navigation">
        <button class="arrow" @click="slideAnterior"><img src="../assets/left-arrow.png" alt="" /></button>
        <button class="arrow" @click="proximoSlide"><img src="../assets/right-arrow.png" alt="" /></button>
      </div>
    </div>

    <div class="container">
      <div v-for="postagem in postagensPaginadas" :key="postagem.id" class="card">
        <router-link :to="{ name: 'TheProductPage', params: { id: postagem.id } }">
          <h2>{{ postagem.titulo }}</h2>
          <div class="card-img-produto"><img :src="postagem.foto" alt="" class="card-img" /></div>
          <button class="ver-produto">Ver produto</button>
        </router-link>
      </div>
    </div>

    <div class="pagination">
      <div class="center">
        <div class="arrow">
          <a href="#" @click="paginaAnterior"><img src="../assets/left-arrow.png" alt="" /></a>
        </div>
        <div class="pagination">
          <a
            v-for="pagina in numeroPaginas"
            :key="pagina"
            :class="{ active: pagina === paginaAtual }"
            @click="irParaPagina(pagina)"
            >{{ pagina }}</a
          >
        </div>
        <div class="arrow">
          <a href="#" @click="proximaPagina"><img src="../assets/right-arrow.png" alt="" /></a>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Postagem from '../services/PostagemService';

export default {
  data() {
    return {
      postagem: {
        id: '',
        titulo: '',
        imagem: '',
      },
      postagens: [],
      paginaAtual: 1,
      postagensPorPagina: 16,
      currentSlideIndex: 0,
      carrosselPostagens: 0,
    };
  },

  computed: {
    postagensPaginadas() {
      const indiceInicial = (this.paginaAtual - 1) * this.postagensPorPagina;
      const indiceFinal = indiceInicial + this.postagensPorPagina;
      return this.postagens.slice(indiceInicial, indiceFinal);
    },

    numeroPaginas() {
      return Math.ceil(this.postagens.length / this.postagensPorPagina);
    },
  },

  methods: {
    exibirTodasPostagens() {
      Postagem.exibirTodasPostagens().then((resposta) => {
        this.postagens = resposta.data;
        this.carrosselPostagens = this.obterPostagensAleatorias();
      });
    },

    obterPostagensAleatorias() {
      const postagensAleatorias = this.postagens.sort(() => 0.5 - Math.random());
      return postagensAleatorias.slice(0, 4);
    },

    proximoSlide() {
      if (this.currentSlideIndex < this.carrosselPostagens.length - 1) {
        this.currentSlideIndex++;
      } else if (this.currentSlideIndex == this.carrosselPostagens.length - 1) this.currentSlideIndex = 0;
    },

    slideAnterior() {
      if (this.currentSlideIndex > 0) {
        this.currentSlideIndex--;
      } else if (this.currentSlideIndex == 0) this.currentSlideIndex = this.carrosselPostagens.length - 1;
    },

    irParaPagina(pagina) {
      this.paginaAtual = pagina;
    },

    paginaAnterior() {
      if (this.paginaAtual > 1) {
        this.paginaAtual--;
      }
    },

    proximaPagina() {
      if (this.paginaAtual < this.numeroPaginas) {
        this.paginaAtual++;
      }
    },
  },

  mounted() {
    this.exibirTodasPostagens();
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
  text-decoration: none;
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
  margin: 0;
  padding: 0;
}

img {
  display: block;
  max-width: 100%;
}

.container {
  width: 1156px;
  display: flex;
  justify-content: left;
  margin-top: 20px;
  gap: 20px;
  flex-wrap: wrap;
  margin-left: auto;
  margin-right: auto;
}

.card {
  display: block;
  width: 274px;
  height: 370px;
  background: #ffffff;
  border: 1px solid #e5e9eb;
  border-radius: 4px;
  transition: all 300ms;
  flex: none;
}

.card:hover {
  transform: scale(1.02);
}

.card img,
.carrossel-item img {
  margin-top: 10px;
  border-radius: 4px;
  min-height: 250px;
}

.card h2,
h2 {
  text-align: center;
  font-weight: bold;
  padding: 8px;
  box-sizing: border-box;
  margin-left: 5px;
  margin-top: 5px;
  font-size: 15px;
  color: #252c32;
  kerning: -0.6%;
  font-weight: 400;
  text-align: left;
}

.card-img-produto img {
  max-height: 250px;
  width: 250px;
  background-color: grey;
  object-fit: cover;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

.ver-produto {
  cursor: pointer;
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  position: absolute;
  width: 240px;
  height: 32px;
  background: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
}

.ver-produto:hover {
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
}

.like {
  background-color: transparent;
  border: none;
}

.like img {
  width: 20px;
  margin-left: 212px;
  margin-top: -20px;
}

.center {
  display: flex;
  justify-content: space-around;
  text-align: center;
  margin-top: 100px;
  margin-bottom: 100px;
}

.pagination {
  display: inline-block;
  font-size: 14px;
}

.pagination a {
  cursor: pointer;
  color: rgba(102, 112, 133, 1);
  float: left;
  padding: 10px 16px;
  text-decoration: none;
  transition: background-color 0.3s;
  border: 0 solid #ddd;
  border-radius: 100%;
  margin: 0 4px;
}

.pagination a.active {
  background-color: #f9dc5c;
  color: rgba(102, 112, 133, 1);
  border: 0 solid #f9dc5c;
}

.pagination a:hover:not(.active) {
  background-color: #f5f5f5;
  padding: 11px 16px;
}

.arrow img {
  width: 24px;
}

.arrow img:hover {
  background-color: transparent;
}

.carrossel {
  width: 100%;
  height: 400px;
  overflow: hidden;
  transition: transform 0.5s ease-in-out;
}

.carrossel-container {
  display: flex;
  margin: 30px 0 15px 0;
  transition: transform 0.5s ease-in-out;
}

.carrossel-item {
  flex: 0 0 100%;
  padding: 0 10px;
  display: flex;
  flex-wrap: nowrap;
  justify-content: center;
}

.card-img-carrossel {
  width: 100%;
  height: 300px;
}

.carrossel-item img {
  width: 500px;
  height: 300px;
  border-radius: 10px;
}

.carrossel-navigation {
  top: 50%;
  transform: translateY(-50%);
  display: flex;
  justify-content: center;
  margin-top: 30px;
}

.carrossel-navigation .arrow:hover {
  background-color: #f5f5f5;
}
</style>
