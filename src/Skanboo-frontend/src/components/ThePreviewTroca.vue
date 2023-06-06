<template>
  <section>
    <div>
      <h1>Resumo da troca</h1>
    </div>

    <div v-if="oferta" class="container">
      <div class="descricao">
        <div class="imagem">
          <img :src="oferta.postagemOrigem.foto" alt="Foto do Produto" />
        </div>
        <div class="categorias-produto">
          <span><b>Categoria: </b></span>
          <span class="categoria">{{
            oferta.postagemOrigem.categoriaProduto
          }}</span>

          <br />

          <span><b>Interesse: </b></span>
          <span class="oferta">{{
            oferta.postagemOrigem.categoriaProdutoDesejado
          }}</span>

          <br />

          <span><b>Contato: </b></span>
          <span class="oferta">telefone</span>
        </div>
        <div class="informacoes-produto">
          <div class="titulo">
            <h2>{{ oferta.postagemOrigem.titulo }}</h2>
          </div>
          <p>
            {{ oferta.postagemOrigem.descricao }}
          </p>
        </div>
      </div>

      <div class="seta-div">
        <img class="seta" src="../assets/setaDireita.png" alt="seta" />
        <img class="seta" src="../assets/setaEsquerda.png" alt="seta" />
      </div>

      <div class="descricao">
        <div class="imagem">
          <img :src="oferta.postagemOfertada.foto" alt="Foto do Produto" />
        </div>
        <div class="categorias-produto">
          <span><b>Categoria: </b></span>
          <span class="categoria">{{
            oferta.postagemOfertada.categoriaProduto
          }}</span>

          <br />

          <span><b>Interesse: </b></span>
          <span class="oferta">{{
            oferta.postagemOfertada.categoriaProdutoDesejado
          }}</span>

          <br />

          <span><b>Contato: </b></span>
          <span class="oferta">telefone</span>
        </div>
        <div class="informacoes-produto">
          <div class="titulo">
            <h2>{{ oferta.postagemOfertada.titulo }}</h2>
          </div>
          <p>
            {{ oferta.postagemOfertada.descricao }}
          </p>
        </div>
      </div>
    </div>
    <div class="container-botoes">
      <button class="voltar">Voltar</button>
      <button id="aceitar" @click="confirmacaoTroca">Produto recebido</button>
    </div>

  </section>
</template>

<script>
import Oferta from '../services/OfertaService';
import Endereco from '../services/EnderecoService';
import Troca from '../services/TrocaService';
import Usuario from '../services/UsuarioService';

export default {
  props: ["id"],
  data() {
    return {
      oferta: null,
      troca: null,
      usuario: null,
      id_usuario: null,
    };
  },
  mounted() {
    const ofertaId = this.$route.params.idOferta;
    console.log(ofertaId);
    Oferta.exibirOferta(ofertaId)
      .then((resposta) => {
        const ofertas = resposta.data;
        this.oferta = ofertas;
        console.log(this.oferta)
        return this.oferta;
      })
      .catch((e) => console.log(e.message));

    Endereco.exibirInfo()
      .then((resposta) => {
        this.endereco = resposta.data;
        return this.endereco;
      })
      .catch((e) => console.log(e.message));

      Usuario.exibirInfo()
      .then((resposta) => {
        this.usuario = resposta.data;
        this.id_usuario = this.usuario.id; // Assign the value to id_usuario
        return this.id_usuario;
      })
      .catch((e) => console.log(e.message));
  },
  methods: {
    confirmacaoTroca() {
      const ofertaId = this.$route.params.idOferta;

      Troca.atualizar(ofertaId)
        .then(() => {
          console.log(this.id_usuario);
          alert('Confirmação enviada!');
          this.errors = [];
        })
        .catch((e) => console.log(e.message));
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  max-width: 500px;
  padding: 10px;
  text-align: left;
  align-items: center;
  margin: 0 auto;
  gap: 80px;
}

.imagem {
  display: flex;
  justify-content: center;
  width: 150px;
  height: 150px;
  background-color: grey;
  border: 0px solid #515864;
  border-radius: 4px;

}

.card-img {
  width: 150px;
  height: 150px;
  object-fit: cover;

}

img {
  max-width: 150px;
  max-height: 150px;
  object-fit: cover;
  border-radius: 4px;
}

.seta-div {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 150px;
  height: 100%;
}

.seta {
  width: 50px;
  height: 50px;
  margin-top: 0px;
}

.titulo {
  display: flex;
  flex-direction: column;
  height: 100%;
  margin-left: 2px;
  margin-top: 20px;
  margin-bottom: -20px;
  padding: 0px;
  padding-top: 0;
  flex: 0;
  font-size: 18px;
}

.informacoes-produto {
  display: flex;
  flex-direction: column;
  height: 300px;
  width: 300px;
  min-width: 300px;
  margin-left: 0px;
  margin-top: 10px;
  padding: 10px;
  padding-top: 0;
  flex: 1;
  border: 1px solid #eae9e9;
  border-radius: 4px;
}

.categorias-produto {
  display: flex;
  flex-direction: column;
  height: 180px;
  margin-left: 10px;
  margin-top: 0px;
  padding: 20px;
  padding-top: 0;
  padding-bottom: 0;
  flex: 1;
  border: 1px solid #eae9e9;
  border-radius: 4px;
}

span {
  font-size: small;
  margin-top: 5px;
}

.descricao {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  max-width: 400px;
  padding: 0px;
  text-align: left;
  margin: 0 auto;
}

.voltar {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 90px;
  height: 32px;
  background: transparent;
  border: 1px solid #000;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;
  margin-top: 10px;
  left: 60px;
}

.voltar:hover {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 90px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;
  margin-top: 10px;
  left: 60px;
}

.enviar {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 500px;
  height: 32px;
  background: transparent;
  border: 1px solid #000;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;
  margin-top: 10px;
}

.enviar:hover {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 500px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;
  margin-top: 10px;
}

/* .invisivel {
    box-sizing: border-box;
    padding: 2px 6px 2px 8px;
    gap: 4px;
    width: 460px;
    height: 32px;
    font-weight: 600;
    margin-top: 10px;
    position: fixed;
    border: 0px;
}

.invisivel:hover+.enviar {
    box-sizing: border-box;
    padding: 2px 6px 2px 8px;
    gap: 4px;
    width: 150px;
    height: 32px;
    background: #f9dc5c;
    border: 1px solid #f9dc5c;
    border-radius: 16px;
    font-weight: 600;
    color: #515864;
    transition: 0.3s;
    margin-top: 10px;
} */

p {
  line-height: 1.7em;
  padding: 5px;
  font-size: 14px;
  text-align: justify;
  word-break: break-all;
}

h1 {
  font-size: 2em;
}

h2 {
  font-size: 1em;
}
</style>
