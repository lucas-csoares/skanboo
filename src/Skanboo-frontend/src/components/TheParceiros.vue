<template>
  <section class="products">

    <h1>Os parceiros</h1>
    <!-- Revisar, icone provisório -->
    <a href="/CooptarParceirosView"><img src="../assets/plus-icon.png" alt="Cadastrar novo parceiro" class="adicionar_parceiro" style="width: 20px; height: 20px;"/></a>

    <div class="container">
      <div v-for="parceiro in parceiros" :key="parceiro.id" class="card">

        <!-- <router-link :to="{ name: 'TheProductPage', params: { id: postagem.id } }"> -->
          <h2>{{ parceiro.nome }}</h2>
          <div class="card-img-produto"><img :src="parceiro.foto" alt="" class="card-img" /></div>
        <!-- </router-link> -->

        <button class="editar"><router-link :to="{ name: 'TheEditPartner', params: { id: parceiro.id } }" >Editar</router-link></button>
        <button class="excluir" @click="excluir(parceiro.id)">Excluir</button>

      </div>
    </div>

  </section>
</template>

<script>
import Postagem from '../services/ParceiroService';

export default {
  data() {

    return {
      parceiro: {
        nome: '',
        foto: null,
        contrato: null,
        cnpj: '',
        plano: '',
      },
      parceiro: [],
    };
  },

  mounted() {

    Parceiro.exibirInfo()
      .then((resposta) => {
        const parceiro = resposta.data;
        this.parceiro = parceiro;
      })
      .catch((e) => console.log(e.message));
  },

  methods: {
    excluirParceiro(id) {
      Parceiro.excluir(id)
        .then(() => {
          this.carregarParceiros();
        })
        .catch((error) => {
          console.error('Erro ao excluir parceiro', error);
        });
    },

    carregarParceiro() {
      Parceiro.exibirInfo()
        .then((resposta) => {
          const parceiro = resposta.data;
        this.parceiro = parceiro;
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

.container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  gap: 20px;
}

.products {
  margin-left: 200px;
  margin-right: 200px;
}

.card {
  width: 274.5px;
  height: 400px;
  background: #ffffff;
  border: 1px solid #e5e9eb;
  border-radius: 4px;
  flex: none;
  order: 0;
  flex-grow: 1;
  margin-bottom: 30px;
}

.card img {
  margin-top: -20px;
}

.card h2 {
  padding: 10px;
  box-sizing: border-box;
  margin-left: 5px;
  margin-top: 20px;
}

.card-img-produto img {
  max-height: 200px;
  margin-top: 20px;
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
