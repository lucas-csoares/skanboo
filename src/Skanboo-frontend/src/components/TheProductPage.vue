<template>
  <div v-if="postagem">
    <section>
      <div class="container">
        <div class="descricao">
          <div class="imagem">
            <img :src="postagem.foto" alt="Foto do Produto" />
          </div>
          <div class="categorias-produto">
            <br />

            <span><b>Categoria: </b></span>
            <span class="categoria">{{ postagem.categoriaProduto }}</span>

            <br />

            <span><b>Interesse: </b></span>
            <span class="oferta">{{ postagem.categoriaProdutoDesejado }}</span>

            <br />

            <span><b>Local: </b></span>
            <span class="oferta">estado e cidade</span>

            <br />

            <span><b>Postador por: </b></span>
            <span class="usuario-postagem">{{ postagem.usuario }}</span>
            <span>nota do usuario ****a ser editada****</span>
          </div>
          <div class="informacoes-produto">
            <div class="titulo">
              <h1>{{ postagem.titulo }}</h1>
            </div>
            <p>{{ postagem.descricao }}</p>
          </div>
        </div>
      </div>
      <div class="div-botao">
        <button @click="saveIdOfertada">
          <router-link :to="{ name: 'EscolherProdutoView' }"
            >Negociar</router-link
          >
        </button>
      </div>
    </section>
    <!-- <EscolherProdutoView idOfertada="kljfdlkjf" /> -->
  </div>
</template>

<script>
import Postagem from "../services/PostagemService";
import Oferta from "../services/OfertaService";
// import EscolherProdutoView from '@/views/EscolherProdutoView.vue';

export default {
  props: ["id"],
  data() {
    return {
      postagem: null,
      Oferta: null,
    };
  },
  mounted() {
    Postagem.exibirInfoPostagem(this.id).then((resposta) => {
      this.postagem = resposta.data;
      return this.postagem;
    });
  },
  methods: {
    criarOferta() {
      Oferta.criar()
        .then((resposta) => {
          const oferta = resposta.data;
          this.oferta = oferta;
        })
        .catch((e) => console.log(e.message));
    },
    saveIdOfertada() {
      sessionStorage.setItem("idOfertada", this.postagem.id);
    },
  },
  // components: { EscolherProdutoView }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  max-width: 1000px;
  padding: 10px;
  text-align: left;
  margin: 0 auto;
}

.imagem {
  display: flex;
  width: 300px;
  height: 300px;
  background-color: rgba(128, 128, 128, 0);
  border: 0px solid #515864;
  justify-content: center;
}

img {
  width: 300px;
  height: 300px;
  object-fit: cover;
  border-radius: 4px;
}

.titulo {
  display: flex;
  flex-direction: column;
  height: 100%;
  min-width: 100px;
  margin-left: 10px;
  margin-top: 10px;
  padding: 10px;
  padding-top: 0;
  flex: 0;
}

.informacoes-produto {
  display: flex;
  flex-direction: column;
  height: 20;
  width: 500px;
  margin-left: 0px;
  margin-top: 10px;
  padding: 10px;
  padding-top: 0;
  flex: 1;
  border: 1px solid #e9eced;
  border-radius: 4px;
}

.categorias-produto {
  display: flex;
  flex-direction: column;
  height: 300px;
  margin-left: 10px;
  margin-top: 0px;
  padding: 10px;
  padding-top: 0;
  padding-bottom: 0;
  flex: 1;
  border: 1px solid #e9eced;
  border-radius: 4px;
}

span {
  line-height: 1.5em;
}

.descricao {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  max-width: 500px;
  padding: 0px;
  text-align: left;
  margin: 0 auto;
}

button {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 300px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
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
  width: 300px;
  height: 32px;
  background: #ffe574;
  border: 1px solid #ffe574;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;

  margin-top: 10px;
}

p {
  line-height: 1.5em;
  padding: 0 20px;
  margin-top: -25px;
  font-size: 16px;
  text-align: justify;
  word-break: break-all;
  letter-spacing: 0.5px;
}

h1 {
  font-size: 2em;
  letter-spacing: 0.5px;
}

span {
  letter-spacing: 0.5px;
}

span b {
  font-size: 14px;
}
</style>
