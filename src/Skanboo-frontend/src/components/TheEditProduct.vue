<template>
  <div v-if="postagem">
    <section class="container">
      <h1>Editar produto</h1>

      <div class="postagem">
        <div class="dados-postagem">
          <form @submit.prevent="atualizar">
            <label for="fotos">Adicionar foto</label>
            <input type="file" id="fotos" name="fotos" />

            <label for="titulo">Título</label>
            <input
              type="text"
              id="titulo"
              maxlength="30"
              :placeholder="postagem.titulo"
              v-model="postagem.titulo"
            />

            <label for="descricao">Descrição</label>
            <textarea
              id="descricao"
              name="descricao"
              rows="4"
              cols="50"
              maxlength="140"
              :placeholder="postagem.descricao"
              v-model="postagem.descricao"
            ></textarea>

            <div class="categoria">
              <fieldset>
                <legend>Selecione a categoria do produto:</legend>

                <div>
                  <input
                    type="radio"
                    id="eletronico"
                    name="categoria"
                    value="Eletrônico"
                    v-model="postagem.categoriaProduto"
                  />
                  <label for="eletronico">Eletrônicos</label>
                </div>

                <div>
                  <input
                    type="radio"
                    id="modaBeleza"
                    name="categoria"
                    value="Moda e Beleza"
                    v-model="postagem.categoriaProduto"
                  />
                  <label for="modaBeleza">Moda e Beleza</label>
                </div>

                <div>
                  <input
                    type="radio"
                    id="musica"
                    name="categoria"
                    value="Música"
                    v-model="postagem.categoriaProduto"
                  />
                  <label for="musica">Música</label>
                </div>

                <div>
                  <input
                    type="radio"
                    id="casa"
                    name="categoria"
                    value="Casa"
                    v-model="postagem.categoriaProduto"
                  />
                  <label for="casa">Casa</label>
                </div>

                <div>
                  <input
                    type="radio"
                    id="servicos"
                    name="categoria"
                    value="Serviços"
                    v-model="postagem.categoriaProduto"
                  />
                  <label for="servicos">Serviços</label>
                </div>
              </fieldset>
              <br />
              <fieldset>
                <legend>Selecione a categoria de interesse:</legend>

                <div>
                  <input
                    type="radio"
                    id="eletronico-interesse"
                    name="categoria-interesse"
                    value="Eletrônico"
                    v-model="postagem.categoriaProdutoDesejado"
                  />
                  <label for="eletronico">Eletrônicos</label>
                </div>

                <div>
                  <input
                    type="radio"
                    id="modaBeleza-interesse"
                    name="categoria-interesse"
                    value="Moda e Beleza"
                    v-model="postagem.categoriaProdutoDesejado"
                  />
                  <label for="modaBeleza">Moda e Beleza</label>
                </div>

                <div>
                  <input
                    type="radio"
                    id="musica-interesse"
                    name="categoria-interesse"
                    value="Música"
                    v-model="postagem.categoriaProdutoDesejado"
                  />
                  <label for="musica">Música</label>
                </div>

                <div>
                  <input
                    type="radio"
                    id="casa-interesse"
                    name="categoria-interesse"
                    value="Casa"
                    v-model="postagem.categoriaProdutoDesejado"
                  />
                  <label for="casa">Casa</label>
                </div>

                <div>
                  <input
                    type="radio"
                    id="servicos-interesse"
                    name="categoria-interesse"
                    value="Serviços"
                    v-model="postagem.categoriaProdutoDesejado"
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
  </div>
</template>

<script>
import Postagem from "../services/PostagemService";

export default {
  props: ["id"],
  data() {
    return {
      postagem: null,
    };
  },

  mounted() {
    Postagem.exibirInfoPostagem(this.id).then((resposta) => {
      this.postagem = resposta.data;
      return this.postagem;
    });
  },

  methods: {
    atualizar() {
      this.uploadFoto().then((foto) => {
        this.postagem.foto = foto;

        Postagem.atualizar(this.postagem.id, this.postagem)
          .then(() => {
            alert("Postagem editada com sucesso!");
            this.errors = [];

            this.$router.push({ name: "PostsDoUsuarioView" });
          })
          .catch((e) => {
            alert("Todos os campos da postagem devem ser preenchidos!");
            this.errors = e.response.data.errors;
            console.log(this.errors);
          });
      });
    },

    uploadFoto() {
      return new Promise((resolve, reject) => {
        const fileInput = document.querySelector("input[type=file]");
        const file = fileInput.files[0];

        const reader = new FileReader();
        reader.addEventListener(
          "load",
          () => {
            resolve(reader.result);
          },
          false
        );

        reader.addEventListener("error", reject);

        if (file) {
          reader.readAsDataURL(file);
        } else {
          resolve(null);
        }
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
  border: 1px solid #f9dc5c;
  border-radius: 32px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;
  margin-left: 0;
  margin-top: 15px;
  transition: 0.3s;
}

button:hover {
  background: #ffe574;
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
