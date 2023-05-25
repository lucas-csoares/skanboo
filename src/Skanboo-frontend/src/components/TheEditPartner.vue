<template>
  <div v-if="parceiro">
    <section class="container">
      <h1>Editar parceiro</h1>

      <div class="parceiro">
        <div class="dados-parceiro">
          <form @submit.prevent="atualizar">
            <label for="titulo">Nome fantasia</label>
            <input type="text" id="nome" maxlength="30" v-model="parceiro.nome" />

            <label for="logo">Foto</label>
            <input type="file" id="foto" name="foto" />

            <button class="editar">Editar</button>
          </form>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Parceiro from '../services/ParceiroService';

export default {
  props: ['id'],
  data() {
    return {
      parceiro: null,
    };
  },

  mounted() {
    Parceiro.exibirInfo(this.id).then((resposta) => {
      this.parceiro = resposta.data;
      return this.parceiro;
    });
  },

  methods: {
    atualizar() {
      this.uploadFoto().then((foto) => {
        this.parceiro.foto = foto;
        this.parceiro.contrato = "placeholder";
        this.parceiro.cnpj = "placeholder";
        this.parceiro.plano = "placeholder";

        Parceiro.atualizar(this.parceiro.id, this.parceiro)
          .then(() => {
            alert('Parceiro editado com sucesso!');
            this.errors = [];

            this.$router.push({ name: 'ExibirParceirosView' });
          })
          .catch((e) => {
            alert('Todos os campos da parceiro devem ser preenchidos!');
            this.errors = e.response.data.errors;
            console.log(this.errors);
          });
      });
    },

    uploadFoto() {
      return new Promise((resolve, reject) => {
        const fileInput = document.querySelector('input[type=file]');
        const file = fileInput.files[0];

        const reader = new FileReader();
        reader.addEventListener(
          'load',
          () => {
            resolve(reader.result);
          },
          false
        );

        reader.addEventListener('error', reject);

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

.parceiro {
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

input[type='radio'] {
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
