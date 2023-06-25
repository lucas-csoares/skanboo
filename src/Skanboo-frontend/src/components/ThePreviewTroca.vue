<template>
  <section>
    <div>
      <h1>Resumo da troca</h1>
    </div>

    <div v-if="troca" class="container">
      <div class="descricao">
        <div class="imagem">
          <img :src="troca.oferta.postagemOrigem.foto" alt="Foto do Produto" />
        </div>
        <div class="categorias-produto">
          <br />

          <span><b>Categoria: </b></span>
          <span class="categoria">{{ troca.oferta.postagemOrigem.categoriaProduto }}</span>

          <br />

          <span><b>Interesse: </b></span>
          <span class="oferta">{{ troca.oferta.postagemOrigem.categoriaProdutoDesejado }}</span>

          <br />

          <span><b>Telefone: </b></span>
          <span class="oferta">{{ usuarioPostagemOrigem.telefone }}</span>

          <br />

          <span><b>Postador por: </b></span>
          <span
            >{{ usuarioPostagemOrigem.nome }}
            <span class="nota-usuario">{{ usuarioPostagemOrigem.notaFinal }}</span>
          </span>
        </div>
        <div class="informacoes-produto">
          <div class="titulo">
            <h1>{{ troca.oferta.postagemOrigem.titulo }}</h1>
          </div>
          <p>{{ troca.oferta.postagemOrigem.descricao }}</p>
        </div>
      </div>

      <div class="seta-div">
        <img class="seta" src="../assets/setaDireita.png" alt="seta" />
        <img class="seta" src="../assets/setaEsquerda.png" alt="seta" />
      </div>

      <div class="descricao">
        <div class="imagem">
          <img :src="troca.oferta.postagemOfertada.foto" alt="Foto do Produto" />
        </div>
        <div class="categorias-produto">
          <br />

          <span><b>Categoria: </b></span>
          <span class="categoria">{{ troca.oferta.postagemOfertada.categoriaProduto }}</span>

          <br />

          <span><b>Interesse: </b></span>
          <span class="oferta">{{ troca.oferta.postagemOfertada.categoriaProdutoDesejado }}</span>

          <br />

          <span><b>Telefone: </b></span>
          <span class="oferta">{{ usuarioPostagemOfertada.telefone }}</span>

          <br />

          <span><b>Postador por: </b></span>
          <span
            >{{ usuarioPostagemOfertada.nome }} <span class="nota-usuario">{{ usuarioPostagemOfertada.notaFinal }}</span>
          </span>
        </div>
        <div class="informacoes-produto">
          <div class="titulo">
            <h1>{{ troca.oferta.postagemOfertada.titulo }}</h1>
          </div>
          <p>{{ troca.oferta.postagemOfertada.descricao }}</p>
        </div>
      </div>
    </div>
    <div class="container-botoes">
      <button class="voltar" @click="paginaTrocas()"><a href="">Voltar</a></button>

      <button v-if="troca && troca.status === 'EM_ANDAMENTO'" id="aceitar" @click="confirmacaoTroca()">
        <a>Produto recebido</a>
      </button>

      <button v-if="troca && troca.status === 'FINALIZADA'" id="aceitar" @click="toggle()"><a>Avaliar troca</a></button>
    </div>

    <div class="popup-container">
      <div class="popup">
        <h3>Como foi essa troca?</h3>

        <input type="radio" name="buttons" id="btn1" value="1" v-model="avaliacao.nota" />
        <input type="radio" name="buttons" id="btn2" value="2" v-model="avaliacao.nota" />
        <input type="radio" name="buttons" id="btn3" value="3" v-model="avaliacao.nota" />
        <input type="radio" name="buttons" id="btn4" value="4" v-model="avaliacao.nota" />
        <input type="radio" name="buttons" id="btn5" value="5" v-model="avaliacao.nota" />

        <div class="icons">
          <label for="btn1">🙁</label>
          <label for="btn2">😐</label>
          <label for="btn3">😊</label>
          <label for="btn4">😀</label>
          <label for="btn5">😍</label>
        </div>

        <input type="submit" value="Enviar" class="enviar" @click="criarAvaliacao" />

        <div @click="toggle()" id="close">✖</div>
      </div>
    </div>
  </section>
</template>

<script>
import Troca from '../services/TrocaService';
import Usuario from '../services/UsuarioService';
import Avaliacao from '@/services/AvaliacaoService';
import Postagem from '../services/PostagemService';

export default {
  data() {
    return {
      oferta: null,
      troca: null,
      usuario: null,
      id_usuario: null,
      idTroca: null, // Add the idTroca property
      avaliacao: {
        nota: null,
      },
      usuarioPostagemOrigem: '',
      usuarioPostagemOfertada: '',
    };
  },
  mounted() {
    // const ofertaId = this.$route.params.idOferta;
    const trocaId = this.$route.params.idTroca;

    // Fetch the troca data
    Troca.exibirTroca(trocaId).then((resposta) => {
      this.troca = resposta.data;
      this.exibirUsuarioPostagemOrigem(this.troca.oferta.postagemOrigem.id);
      this.exibirUsuarioPostagemOfertada(this.troca.oferta.postagemOfertada.id);
      return this.troca;
    });

    // Fetch the user data
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
      const trocaId = this.$route.params.idTroca;

      Troca.atualizar(trocaId)
        .then(() => {
          console.log(this.id_usuario);
          console.log(trocaId);
          alert('Confirmação enviada!');
        })
        .catch((e) => console.log(e.message));
    },
    paginaTrocas(id_troca) {
      this.$router.push({
        name: 'PaginaTrocasView',
        params: { idTroca: id_troca },
      });
    },
    toggle() {
      let toggle = document.querySelector('.popup-container');
      toggle.classList.toggle('toggle');
    },

    criarAvaliacao() {
      const trocaId = this.$route.params.idTroca;

      Avaliacao.criar(trocaId, this.avaliacao)
        .then(() => {
          alert('Avaliação criada com sucesso');
          this.$router.push({
            name: 'PaginaTrocasView',
            params: { idTroca: trocaId },
          });
          this.errors = [];
        })
        .catch((e) => {
          console.log(e.message);
        });
    },

    exibirUsuarioPostagemOrigem(id) {
      Postagem.exibirUsuarioPostagem(id).then((resposta) => {
        this.usuarioPostagemOrigem = resposta.data;
        return this.usuarioPostagemOrigem;
      });
    },

    exibirUsuarioPostagemOfertada(id) {
      Postagem.exibirUsuarioPostagem(id).then((resposta) => {
        this.usuarioPostagemOfertada = resposta.data;
        return this.usuarioPostagemOfertada;
      });
    },
  },
};
</script>

<style scoped>
* {
  transition: all 0.2s linear;
}

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

.seta-div {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 150px;
  height: 100%;
}

.seta {
  width: 100px;
  height: 50px;
  padding-top: 100px;
  margin: 0 20px;
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

.nota-usuario {
  font-weight: bold;
  color: #f26a63;
}

span {
  line-height: 1.5em;
}

.telefone {
  font-size: 10px;
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
  width: 300px;
  text-transform: capitalize;
  border: 1px solid #e9eced;
  transition: all 300ms;
}

button:hover {
  background: #f9dc5c;
  transition: 0.3s;
}

a {
  text-decoration: none;
  color: #515864;
  font-weight: bold;
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

h2 {
  font-size: 1em;
}

.popup {
  height: 320px;
  width: 400px;
}

.popup-container {
  position: fixed;
  top: -120%;
  left: 0;
  z-index: 1000;
  background: rgba(0, 0, 0, 0.3);
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100%;
}

.popup-container.toggle {
  top: 0%;
}

.popup-container .popup {
  background: #fff;
  text-align: center;
  margin: 10px;
  padding: 10px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.5);
  border-radius: 5px;
  position: relative;
}

.popup-container .popup h3 {
  color: #444;
  padding: 20px 40px;
  font-size: 25px;
}

.popup-container .popup .btn {
  margin: 30px;
  font-size: 20px;
  height: 40px;
  width: 150px;
}

.popup-container .popup input[type='radio'] {
  display: none;
}

.popup-container .popup .icons {
  padding: 10px;
}

.popup-container .popup .icons label {
  font-size: 50px;
  cursor: pointer;
  opacity: 0.4;
}

.popup-container .popup .icons:hover label {
  opacity: 0.2;
}

.popup-container .popup #btn1:checked ~ .icons label:nth-child(1),
.popup-container .popup #btn2:checked ~ .icons label:nth-child(2),
.popup-container .popup #btn3:checked ~ .icons label:nth-child(3),
.popup-container .popup #btn4:checked ~ .icons label:nth-child(4),
.popup-container .popup #btn5:checked ~ .icons label:nth-child(5),
.popup-container .popup .icons label:hover {
  opacity: 1;
  font-size: 60px;
}

.popup-container .popup #close {
  position: absolute;
  top: -15px;
  right: -15px;
  border-radius: 50%;
  border: 4px solid #f9dc5c;
  height: 40px;
  width: 40px;
  line-height: 35px;
  cursor: pointer;
  background: #333;
  color: #fff;
  font-size: 20px;
}

.popup-container .popup #close:hover {
  transform: rotate(90deg);
}
</style>
