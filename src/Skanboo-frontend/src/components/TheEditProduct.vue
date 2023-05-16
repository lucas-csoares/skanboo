<template>
  <section class="container">
    <h1>Editar produto</h1>

    <ul>
      <li v-for="(error, index) of errors" :key="index">
        campo <b>{{ error.field }}</b> - {{ error.defaultMessage }}
      </li>
    </ul>

    <div class="postagem">
      <div class="dados-postagem">
        <form @submit.prevent="atualizar">

          <label for="titulo">Título</label>
          <input type="text" id="titulo" maxlength="30" 
          :placeholder="umaPostagem.titulo ? umaPostagem.titulo : ''" 
          v-model="postagem.titulo" 
          />

          <label for="descricao">Descrição</label><br />
          <textarea
            id="descricao"
            name="descricao"
            rows="4"
            cols="50"
            maxlength="140"
            :placeholder="umaPostagem.titulo ? umaPostagem.descricao : ''"
            v-model="postagem.descricao" 
          ></textarea
          ><br />

          <fieldset>
            <legend>Aberto a ofertas?</legend>
            <input type="radio" id="sim" name="oferta" value="sim" />
            <label for="sim">Sim</label>

            <input type="radio" id="nao" name="oferta" value="nao" />
            <label for="nao">Não</label>
          </fieldset>
          <br /><br />
          <label for="fotos">Adicionar fotos</label>
          <input type="file" id="fotos" name="fotos" /><br />

          <div class="categoria">
            <fieldset>
              <legend>Seleciona a categoria:</legend>

              <div>
                <input
                  type="radio"
                  id="eletronico"
                  name="categoria"
                  value="eletronico"
                />
                <label for="eletronico">Eletrônicos</label>
              </div>

              <div>
                <input
                  type="radio"
                  id="modaBeleza"
                  name="categoria"
                  value="modaBeleza"
                />
                <label for="modaBeleza">Moda e Beleza</label>
              </div>

              <div>
                <input
                  type="radio"
                  id="musica"
                  name="categoria"
                  value="musica"
                />
                <label for="musica">Música</label>
              </div>

              <div>
                <input type="radio" id="casa" name="categoria" value="casa" />
                <label for="casa">Casa</label>
              </div>

              <div>
                <input
                  type="radio"
                  id="servicos"
                  name="categoria"
                  value="servicos"
                />
                <label for="servicos">Serviços</label>
              </div>
            </fieldset>
          </div>
          <button class="editar">Editar</button>
        </form>
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
        categoria: "",
        status: "",
      },
      umaPostagem: {}
    };
  },

  mounted() {
    Postagem.exibirInfo().then(resposta => {
      console.log(resposta.data);
      const postagens = resposta.data; // Todas postagens do usuario
      const umaPostagem = postagens.find(postagem => postagem.id === 2) //Depois mudar a ID do produto escolhido
      console.log(umaPostagem.titulo);
      this.postagem = umaPostagem; // So a postagem escolhida
    })
  },

  methods: {
    atualizar() {
      Postagem.atualizar(this.postagem.id, this.postagem)
        .then((/*resposta*/) => {
          alert("Postagem editada com sucesso");
          this.errors = [];
        })
        .catch((e) => {
          this.errors = e.response.data.errors;
          console.log(this.errors);
        });
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 720px;
  margin: 0 auto;
  text-align: left;
  margin-bottom: 30px;
}

.postagem {
  border: 1px solid #eae9e9;
  border-radius: 4px;
  padding: 20px;
}

.informacoes {
  display: flex;
  justify-content: flex-start;
}

li {
  list-style: none;
}

.foto-usuario {
  width: 120px;
  height: 120px;
  border-radius: 100%;
  background-color: lightgray;
  align-items: center;
  margin-left: 150px;
}

button {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 300px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;
  margin-left: 0;
  margin-top: 15px;
}

.info-usuario p {
  color: #c0c2c7;
}

input {
  display: block;
  box-sizing: border-box;
  width: 680px;
  height: 40px;
  background-color: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  color: #9798b0;
  font-size: 16px;
  letter-spacing: -0.006em;
  padding: 20px;
  margin-bottom: 15px;
}

input::placeholder {
  color: #9798b0;
}

label {
  display: inline-block;
  color: #515864;
  margin-bottom: 5px;
}

textarea {
  display: block;
  box-sizing: border-box;
  width: 680px;
  height: 200px;
  background-color: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  color: #9798b0;
  font-size: 16px;
  letter-spacing: -0.006em;
  padding: 20px;
  margin-bottom: 15px;
}

h1,
h2 {
  padding: 10px;
  padding-left: 0;
}

input[type="radio"] {
  display: inline-block;
  width: auto;
  height: auto;
}

fieldset {
  padding: 10px;
}

legend {
  display: inline-block;
  padding: 0 10px;
}
</style>
