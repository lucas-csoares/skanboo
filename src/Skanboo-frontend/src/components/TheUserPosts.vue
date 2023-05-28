<template>
  <section class="products">
    <div class="adicionar">
      <h1>Meus anúncios</h1>

      <a href="/postarProdutoView"><img src="../assets/plus-icon.png" alt="Adicionar postagem" class="adicionar_postagem"
          style="width: 20px; height: 20px" /></a>
    </div>
    <div class="container">
      <div v-for="postagem in postagens" :key="postagem.id" class="card">
        <router-link :to="{ name: 'TheProductPage', params: { id: postagem.id } }">
          <h2>{{ postagem.titulo }}</h2>
          <div class="card-img-produto">
            <img :src="postagem.foto" alt="" class="card-img" />
          </div>
        </router-link>

        <div class="editar-div">
          <button class="editar">
            <router-link :to="{ name: 'TheEditProduct', params: { id: postagem.id } }">Editar</router-link>
          </button>
        </div>

        <button class="excluir" @click="excluirPostagem(postagem.id)">
          Excluir
        </button>
      </div>

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

.products {}

.adicionar {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.card {
  display: block;
  width: 274px;
  height: 370px;
  background: #ffffff;
  border: 1px solid #e5e9eb;
  border-radius: 4px;
  flex: none;
  justify-content: center;
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

.editar-div {
  display: flex;
  justify-content: center;
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
  border-radius: 20px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
  margin-top: 8px;
  margin-bottom: 0;
  transition: 0.3s;
}

.editar:hover {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 230px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 20px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
  transition: 0.2s;
}

.excluir {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 80px;
  height: 20px;
  background: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
  margin-top: 6px;
  transition: 0.2s;
}

.excluir:hover {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 75px;
  height: 20px;
  background: #ff3939;
  border: 1px solid #515864;
  border-radius: 4px;
  font-weight: 600;
  color: #e2e2e2;
  margin-left: 15px;
  margin-top: 5px;
  transition: 0.3s;
}

.adicionar_postagem {
  display: block;
  margin-left: 20px;
}

h2 {
  font-size: 14px;
  color: #252c32;
  kerning: -0.6%;
  font-weight: 400;
  text-align: left;
}

h1 {
  display: block;
  text-align: left;
}
</style>
