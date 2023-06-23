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
          <span><b>Categoria: </b></span>
          <span class="categoria">{{ troca.oferta.postagemOrigem.categoriaProduto }}</span>
          <br />
          <span><b>Interesse: </b></span>
          <span class="oferta">{{ troca.oferta.postagemOrigem.categoriaProdutoDesejado }}</span>
          <br />
          <span><b>Contato: </b></span>
          <span class="oferta">telefone</span>
        </div>
        <div class="informacoes-produto">
          <div class="titulo">
            <h2>{{ troca.oferta.postagemOrigem.titulo }}</h2>
          </div>
          <p>
            {{ troca.oferta.postagemOrigem.descricao }}
          </p>
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
          <span><b>Categoria: </b></span>
          <span class="categoria">{{ troca.oferta.postagemOfertada.categoriaProduto }}</span>
          <br />
          <span><b>Interesse: </b></span>
          <span class="oferta">{{ troca.oferta.postagemOfertada.categoriaProdutoDesejado }}</span>
          <br />
          <span><b>Contato: </b></span>
          <span class="oferta">telefone</span>
        </div>
        <div class="informacoes-produto">
          <div class="titulo">
            <h2>{{ troca.oferta.postagemOfertada.titulo }}</h2>
          </div>
          <p>
            {{ troca.oferta.postagemOfertada.descricao }}
          </p>
        </div>
      </div>
    </div>
    <div class="container-botoes">
      <button class="voltar" @click="paginaTrocas()">Voltar</button>

      <button v-if="troca && troca.status === 'EM_ANDAMENTO'" id="aceitar" @click="confirmacaoTroca()">Produto
        recebido</button>

      <button v-if="troca && troca.status === 'FINALIZADA'" id="aceitar" @click="toggle()">Avaliar
        troca</button>


    </div>

    <div class="popup-container">

      <div class="popup">

        <h3>Como foi essa troca?</h3>

        <input type="radio" name="buttons" id="btn1" value="1" v-model="avaliacao.nota">
        <input type="radio" name="buttons" id="btn2" value="2" v-model="avaliacao.nota">
        <input type="radio" name="buttons" id="btn3" value="3" v-model="avaliacao.nota">
        <input type="radio" name="buttons" id="btn4" value="4" v-model="avaliacao.nota">
        <input type="radio" name="buttons" id="btn5" value="5" v-model="avaliacao.nota">

        <div class="icons">
          <label for="btn1">🙁</label>
          <label for="btn2">😐</label>
          <label for="btn3">😊</label>
          <label for="btn4">😀</label>
          <label for="btn5">😍</label>
        </div>

        <input type="submit" value="Enviar" class="enviar" @click="criarAvaliacao">

        <div @click="toggle()" id="close">✖</div>

      </div>

    </div>
  </section>
</template>


<script>
import Troca from '../services/TrocaService';
import Usuario from '../services/UsuarioService';
import Avaliacao from '@/services/AvaliacaoService';

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
    };
  },
  mounted() {
    // const ofertaId = this.$route.params.idOferta;
    const trocaId = this.$route.params.idTroca;
    console.log(trocaId);

    // Fetch the oferta data
    // Oferta.exibirOferta(ofertaId)
    //   .then((resposta) => {
    //     const ofertas = resposta.data;
    //     this.oferta = ofertas;
    //     console.log(this.oferta);
    //     return this.oferta;
    //   })
    //   .catch((e) => console.log(e.message));

    // Fetch the troca data
    Troca.exibirTroca(trocaId).then((resposta) => {
      this.troca = resposta.data;
      console.log(resposta.data);
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

          // this.$router.push({
          //   name: "PaginaAvaliacaoView",
          //   params: { idTroca: trocaId },
          // });
          // this.errors = [];
        })
        .catch((e) => console.log(e.message));
    },
    paginaTrocas(id_troca) {
      this.$router.push({
        name: "PaginaTrocasView",
        params: { idTroca: id_troca },
      });
    },
    toggle() {
      let toggle = document.querySelector('.popup-container')
      toggle.classList.toggle('toggle');
    },

    criarAvaliacao() {
      const trocaId = this.$route.params.idTroca;

      Avaliacao.criar(trocaId, this.avaliacao)
        .then(() => {
          alert("Avaliação criada com sucesso");
          this.$router.push({
            name: "PaginaTrocasView",
            params: { idTroca: trocaId },
          });
          this.errors = [];
        })
        .catch((e) => {
          console.log(e.message);
        });
    },
  },
};
</script>

<style scoped>
* {
  transition: all .2s linear;
}

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
  width: 180px;
  height: 180px;
  background-color: rgb(255, 255, 255);
  border: 1px solid #cacaca;
  border-radius: 4px;

}

.card-img {
  width: 180px;
  height: 180px;
  object-fit:cover;

}

img {
  max-width: 180px;
  max-height: 180px;
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
  border: 1px solid #cacaca;
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
  border: 1px solid #cacaca;
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
  width: 100px;
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
  width: 110px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;
  margin-top: 10px;
}

#aceitar {
  border: 1px solid #282c33;
}

#aceitar:hover {
  border: 1px solid #f9dc5c;
  background: #f9dc5c;
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

.popup {
  height: 320px;
  width: 400px;
}

.popup-container {
  position: fixed;
  top: -120%;
  left: 0;
  z-index: 1000;
  background: rgba(0, 0, 0, .3);
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
  box-shadow: 0 5px 10px rgba(0, 0, 0, .5);
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

.popup-container .popup input[type="radio"] {
  display: none;
}

.popup-container .popup .icons {
  padding: 10px;
}

.popup-container .popup .icons label {
  font-size: 50px;
  cursor: pointer;
  opacity: .4;
}

.popup-container .popup .icons:hover label {
  opacity: .2;
}


.popup-container .popup #btn1:checked~.icons label:nth-child(1),
.popup-container .popup #btn2:checked~.icons label:nth-child(2),
.popup-container .popup #btn3:checked~.icons label:nth-child(3),
.popup-container .popup #btn4:checked~.icons label:nth-child(4),
.popup-container .popup #btn5:checked~.icons label:nth-child(5),
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
