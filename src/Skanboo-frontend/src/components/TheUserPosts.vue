<template>
  <section class="products">
    <h1>Meus anúncios</h1>
    <!-- Revisar, icone provisório -->
    <a href="/postarProdutoView"
      ><img
        src="../assets/plus-icon.png"
        alt="Adicionar postagem"
        class="adicionar_postagem"
        style="width: 20px; height: 20px"
    /></a>

    <div class="container">
      <div v-for="postagem in postagens" :key="postagem.id" class="card">
        <router-link
          :to="{ name: 'TheProductPage', params: { id: postagem.id } }"
        >
          <h2>{{ postagem.titulo }}</h2>
          <div class="card-img-produto">
            <img :src="postagem.foto" alt="" class="card-img" />
          </div>
        </router-link>

        <button class="editar">
          <router-link
            :to="{ name: 'TheEditProduct', params: { id: postagem.id } }"
            >Editar</router-link
          >
        </button>
        <button class="excluir" @click="excluirPostagem(postagem.id)">
          Excluir
        </button>
      </div>

      <!-- DIV DE TESTE 
      <div class="card">
        <h2>Blusa top</h2>
        <div class="card-img-produto">
          <img src="../assets/imagem-produto.avif" class="card-img" />
        </div>
        <button class="negociar">NEGOCIAR</button>
      </div> -->
    </div>
  </section>
</template>

<script>
import Postagem from "../services/PostagemService";

export default {
  data() {
    return {
      postagem: {
        id: "",
        titulo: "",
        descricao: "",
        categoriaProduto: "",
        categoriaProdutoDesejado: "",
        status: "",
      },
      postagens: [],
    };
  },

  mounted() {
    Postagem.exibirPostagensUsuarioLogado()
      .then((resposta) => {
        const postagens = resposta.data;
        this.postagens = postagens;
      })
      .catch((e) => console.log(e.message));
  },

  methods: {
    excluirPostagem(id) {
      Postagem.excluirPostagem(id)
        .then(() => {
          // Atualizar a lista de postagens
          this.carregarPostagens();
        })
        .catch((error) => {
          console.error("Erro ao excluir a postagem", error);
        });
    },

    carregarPostagens() {
      Postagem.exibirPostagensUsuarioLogado()
        .then((resposta) => {
          const postagens = resposta.data;
          this.postagens = postagens;
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

.negociar:hover {
  background: #f9dc5c;
  transition: 0.3s;
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
  margin-bottom: 30px;
}

.products {
  margin-left: 200px;
  margin-right: 200px;
}

.card {
  display: block;
  width: 274px;
  height: 370px;
  background: #ffffff;
  border: 1px solid #e5e9eb;
  border-radius: 4px;
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
</style>
