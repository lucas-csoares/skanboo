<template>
  <section class="container">
    <h1>Alterar informações da conta</h1>

    <ul>
      <li v-for="(error, index) of errors" :key="index">
        campo <b>{{ error.field }}</b> - {{ error.defaultMessage }}
      </li>
    </ul>

    <div class="usuario">

      <div class="informacoes">
        <div class="dados-usuario">
          <div class="dados-conta">
            <form @submit.prevent="atualizar">

              <h2>Dados pessoais</h2>
              <label for="fotos">Adicionar foto</label>
              <input type="file" id="fotos" name="fotos" />

              <label for="nome">Editar nome</label>
              <input type="text" id="nome" :placeholder="usuario.nome ? usuario.nome : ''" v-model="usuario.nome" />

              <label for="telefone">Editar telefone</label>
              <input type="text" id="telefone" name="telefone" class="form-control cel-sp-mask"
                :placeholder="usuario.telefone ? usuario.telefone : ''" v-model="usuario.telefone" />

              <label for="uf">UF</label>
              <select v-model="usuario.uf">
                <option value="">Selecione</option>
                <option value="AC">Acre</option>
                <option value="AL">Alagoas</option>
                <option value="AP">Amapá</option>
                <option value="AM">Amazonas</option>
                <option value="BA">Bahia</option>
                <option value="CE">Ceará</option>
                <option value="DF">Distrito Federal</option>
                <option value="ES">Espirito Santo</option>
                <option value="GO">Goiás</option>
                <option value="MA">Maranhão</option>
                <option value="MS">Mato Grosso do Sul</option>
                <option value="MT">Mato Grosso</option>
                <option value="MG">Minas Gerais</option>
                <option value="PA">Pará</option>
                <option value="PB">Paraíba</option>
                <option value="PR">Paraná</option>
                <option value="PE">Pernambuco</option>
                <option value="PI">Piauí</option>
                <option value="RJ">Rio de Janeiro</option>
                <option value="RN">Rio Grande do Norte</option>
                <option value="RS">Rio Grande do Sul</option>
                <option value="RO">Rondônia</option>
                <option value="RR">Roraima</option>
                <option value="SC">Santa Catarina</option>
                <option value="SP">São Paulo</option>
                <option value="SE">Sergipe</option>
                <option value="TO">Tocantins</option>
              </select>

              <button class="salvar">Salvar alterações</button>
              <button class="cancelar">
                <router-link :to="{ name: 'usuarioView' }">Cancelar</router-link>
              </button>
            </form>
          </div>
        </div>
      </div>

    </div>
  </section>
</template>

<script>
import Usuario from "../services/UsuarioService";
import { mask } from "vue-the-mask";

export default {
  directives: { mask },
  data() {
    return {
      usuario: {
        nome: "",
        telefone: "",
        uf: "",
      },
    };
  },

  mounted() {
    Usuario.exibirInfo().then((resposta) => {
      console.log(resposta.data);
      this.usuario = resposta.data;
    })
  },

  methods: {
    atualizar() {

      Usuario.atualizar(this.usuario)
        .then((/*resposta*/) => {
          alert("Informações do usuario editadas com sucesso");
          this.errors = [];
        })
        .catch((e) => {
          this.errors = e.response.data.errors;
          console.log(this.errors);
          console.log(this.usuario);
        });
    },
  }
};
</script>

<style scoped>
.container {
  max-width: 720px;
  margin: 0 auto;
  text-align: left;
  margin-bottom: 30px;
}

.usuario {
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
  font-size: 14px;
  color: #515864;
  transition: 0.3s;
  margin-top: 10px;
  margin-left: 0;
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

select {
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
  padding: 0px;
  margin-bottom: 15px;
  margin-left: 0;
}

input::placeholder {
  color: #9798b0;
}

label {
  display: inline-block;
  color: #515864;
  margin-bottom: 5px;
  text-align: left !important;
}

h1,
h2 {
  padding: 10px;
  padding-left: 0;
}

.cancelar {
  width: 300px;
  height: 30px;
  background-color: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  color: #9798b0;
  font-size: 14px;
  letter-spacing: -0.006em;
}
</style>
