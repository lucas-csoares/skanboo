<template>
  <section class="Avaliacao">
    <br />
    <div class="produto">
      <div class="card">
        <div class="card-img-produto">
          <img :src="avaliacao.foto" class="card-img-produto" />
        </div>
        <h2>{{ avaliacao.titulo }}</h2>
      </div>
    </div>

    <div class="comentario">
      <form>
        <form action="rating">
          <label>
            <input type="radio" name="stars" value="1" v-model="avaliacao.nota" />
            <span class="icon">★</span>
          </label>
          <label>
            <input type="radio" name="stars" value="2" v-model="avaliacao.nota" />
            <span class="icon">★</span>
            <span class="icon">★</span>
          </label>
          <label>
            <input type="radio" name="stars" value="3" v-model="avaliacao.nota" />
            <span class="icon">★</span>
            <span class="icon">★</span>
            <span class="icon">★</span>
          </label>
          <label>
            <input type="radio" name="stars" value="4" v-model="avaliacao.nota" />
            <span class="icon">★</span>
            <span class="icon">★</span>
            <span class="icon">★</span>
            <span class="icon">★</span>
          </label>
          <label>
            <input type="radio" name="stars" value="5" v-model="avaliacao.nota" />
            <span class="icon">★</span>
            <span class="icon">★</span>
            <span class="icon">★</span>
            <span class="icon">★</span>
            <span class="icon">★</span>
          </label>
        </form>
        <button class="enviar" @click="criarAvaliacao">Enviar</button>
      </form>
    </div>
  </section>
</template>

<script>
import Avaliacao from '@/services/AvaliacaoService';
// import Troca from '@/services/TrocaService';

export default {
  data() {
    return {
      avaliacao: {
        nota: null,
      },
    };
  },

  // mounted() {
  //   const trocaId = this.$route.params.trocaId;

  //   Troca.exibirtroca(trocaId)
  //     .then((resposta) => {
  //       this.troca = resposta.data;
  //       return this.troca;
  //     })
  //     .catch((e) => console.log(e.message));
  // },

  methods: {
    criarAvaliacao() {

      // const ofertaId = this.$route.params.idOferta;
      const trocaId = this.$route.params.trocaId;

      Avaliacao.criar(trocaId, this.avaliacao)
        .then(() => {
          alert("Avaliação enviada com sucesso!");
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

.rating {
  display: inline-block;
  position: relative;
  height: 50px;
  line-height: 50px;
  font-size: 50px;
}

.rating label {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  cursor: pointer;
}

.rating label:last-child {
  position: static;
}

.rating label:nth-child(1) {
  z-index: 5;
}

.rating label:nth-child(2) {
  z-index: 4;
}

.rating label:nth-child(3) {
  z-index: 3;
}

.rating label:nth-child(4) {
  z-index: 2;
}

.rating label:nth-child(5) {
  z-index: 1;
}

.rating label input {
  position: absolute;
  top: 0;
  left: 0;
  opacity: 0;
}

.rating label .icon {
  float: left;
  color: transparent;
}

.rating label:last-child .icon {
  color: #000;
}

.rating:not(:hover) label input:checked~.icon,
.rating:hover label:hover input~.icon {
  color: #09f;
}

.rating label input:focus:not(:checked)~.icon:last-child {
  color: #000;
  text-shadow: 0 0 5px #09f;
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

textarea {
  border: 1px solid #e5e9eb;
  padding: 10px;
}

.enviar {
  width: 200px;
  transition: 0.3s;
}

.enviar:hover {
  width: 200px;
  background: #f9dc5c;
}

.Avaliacao {
  display: flex;
  margin-top: 30px;
  gap: 20px;
  justify-content: center;
  margin-bottom: 20px;
}

.card {
  display: block;
  width: 274px;
  height: 370px;
  background: #ffffff;
  border: px solid #e5e9eb;
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

.editar {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 240px;
  height: 32px;
  background: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
}

.editar:hover {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 240px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
}

.excluir {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 240px;
  height: 32px;
  background: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
}

.excluir:hover {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 240px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
}

h2 {
  font-size: 14px;
  color: #252c32;
  kerning: -0.6%;
  font-weight: 400;
  text-align: left;
}

h1 {
  text-align: left;
}

label {
  display: block;
  text-align: left;
}
</style>
