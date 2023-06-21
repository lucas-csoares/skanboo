<template>
  <section class="products">
    <div class="adicionar">
      <h1>Meus anúncios</h1>

      <a href="/postarProdutoView" class="postar-produto"
        ><abbr title="Adicionar nova postagem"
          ><img
            src="../assets/plus.svg"
            alt="Adicionar postagem"
            class="adicionar_postagem plus"
            style="width: 24px; height: 24px" /></abbr
      ></a>
    </div>
    <div class="container">
      <div v-for="postagem in postagens" :key="postagem.id" class="card">
        <span class="icon-excluir" @click="excluirPostagem(postagem.id)"><img src="../assets/delete-icon.svg" /></span>
        <div class="card-conteudo">
          <router-link :to="{ name: 'TheProductPage', params: { id: postagem.id } }">
            <h2>{{ postagem.titulo }}</h2>
            <div class="card-img-produto">
              <img :src="postagem.foto" alt="" class="card-img" />
            </div>
          </router-link>
        </div>

        <div class="editar-div">
          <router-link :to="{ name: 'TheEditProduct', params: { id: postagem.id } }">
            <button class="editar">Editar</button>
          </router-link>
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
        descricao: '',
        categoriaProduto: '',
        categoriaProdutoDesejado: '',
        status: '',
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
      if (confirm('Deseja realmente excluir esta postagem?')) {
        Postagem.excluirPostagem(id)
          .then(() => {
            // Atualizar a lista de postagens
            this.carregarPostagens();
          })
          .catch((error) => {
            console.error('Erro ao excluir a postagem', error);
          });
      }
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

.icon-excluir {
  position: relative;
  left: 260px;
  top: -10px;
}
.icon-excluir img {
  filter: invert(99%) sepia(1%) saturate(6369%) hue-rotate(174deg) brightness(90%) contrast(77%);
}

.icon-excluir img:hover {
  cursor: pointer;
  filter: invert(35%) sepia(15%) saturate(5084%) hue-rotate(328deg) brightness(106%) contrast(91%);
}

.card-conteudo {
  margin-top: -25px;
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
  transition: all 300ms;
  flex: none;
}

.card:hover .card-img-produto img {
  opacity: 1;
}

button {
  cursor: pointer;
}

.card-img-produto img,
.carrossel-item img {
  margin-top: 10px;
  border-radius: 4px;
  min-height: 250px;
}

.adicionar_postagem {
  transition: 0.1s;
  transition-timing-function: ease;
}

.card h2 {
  text-align: center;
  font-weight: bold;
  padding: 8px;
  box-sizing: border-box;
  margin-left: 5px;
  margin-top: 5px;
  font-size: 15px;
  color: #252c32;
  kerning: -0.6%;
}

.card-img-produto img {
  max-height: 250px;
  width: 250px;
  background-color: grey;
  object-fit: cover;
  display: block;
  margin-left: auto;
  margin-right: auto;
  opacity: 0.8;
  transition: 300ms;
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
  width: 250px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
  margin-top: 12px;
  margin-bottom: 0;
}

.editar:hover {
  background: #ffe677;
  border: 1px solid #f9dc5c;
  color: white;
}

.excluir {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 250px;
  height: 32px;
  background: #f26a63;
  border: 1px solid #f26a63;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  margin-left: 10px;
  margin-top: 12px;
  margin-bottom: 0;
}

.excluir:hover {
  background: #ee433a;
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
  display: inline-block;
  font-size: 1.5em;
  margin-bottom: 20px;
}

abbr {
  position: relative;
  transition: 0.2s;
  transition-timing-function: linear;
  font-size: 12px;
}
abbr:hover::after {
  width: 150px;
  height: 15px;
  border-radius: 34px;
  position: absolute;
  bottom: 100%;
  left: 100%;
  display: block;
  padding: 1em;
  background: #fff;
  border: 1px solid #e9eced;
  content: attr(title);
}

.postar-produto img {
  filter: invert(89%) sepia(4%) saturate(95%) hue-rotate(155deg) brightness(86%) contrast(89%);
  transition: all 400ms;
}

.postar-produto img:hover {
  filter: invert(50%) sepia(27%) saturate(1426%) hue-rotate(48deg) brightness(99%) contrast(84%);
}
</style>
