<template>
  <div v-if="postagem">
    <section class="container">
    <div class="imagem">
      <img src="../assets/imagem-produto.avif" alt="Foto do Produto" />
    </div>
    <div class="informacoes-produto">
      <h1>{{ postagem.titulo }}</h1>
      <p>
        {{ postagem.descricao }}
      </p>

      <!-- <span><b>Preferências: </b></span>
      <span class="preferencias">Video-game ou bolsa</span><br /> -->

      <span><b>Aberto a ofertas? </b></span>
      <span class="oferta">Sim</span><br />

      <span><b>Categoria: </b></span>
      <span class="categoria">Moda</span><br /><br />
      <button>Negociar</button>
    </div>
  </section>
  </div>
</template>

<script>

import Postagem from "../services/PostagemService";

export default {
  props: ["id"],
  data() {
    return {
      postagem: null
    }
  },

  mounted() {

    // fetch('http://localhost:8080/postagem/' + this.id)
    // .then(resposta => {})


    Postagem.exibirInfoPostagem(this.id).then(resposta => {
      this.postagem = resposta.data;
      console.log(resposta.data);
      return this.postagem;
    })
  },
};

</script>

<style scoped>
.container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  max-width: 800px;
  padding: 10px;
  text-align: left;
  margin: 0 auto;
}

.imagem {
  width: 400px;
  height: 500px;
  background-color: grey;
}

img {
  max-width: 400px;
  max-height: 500px;
  object-fit: cover;
}

.informacoes-produto {
  margin-left: 50px;
}

span {
  line-height: 1.5em;
}

button {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 400px;
  height: 32px;
  background: transparent;
  border: 1px solid #000;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;

  margin-top: 10px;
}

button:hover {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 400px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;

  margin-top: 10px;
}

p {
  line-height: 1.5em;
  padding: 20px 0px;
  font-size: 16px;
  text-align: justify;
}

h1 {
  font-size: 2em;
}
</style>
