<template>
  <section class="cadastro-final">
    <h1>Cadastrar Endereço</h1>

    <ul>
      <li v-for="(error, index) of errors" :key="index">
        campo <b>{{ error.field }}</b> - {{ error.defaultMessage }}
      </li>
    </ul>

    <form @submit.prevent="criar">


      <label for="uf">UF</label>
      <select v-model="endereco.uf">
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

      <label>CEP</label>
      <input type="text" name="cep" id="cep" placeholder="xxxxx-xxx" v-model="endereco.cep" v-mask="['#####-###']" />

      <label>Cidade</label>
      <input type="text" name="cidade" id="cidade" v-model="endereco.cidade" />

      <label>Bairro</label>
      <input type="text" name="bairro" id="bairro" v-model="endereco.bairro" />

      <label>Rua</label>
      <input type="text" name="rua" id="rua" v-model="endereco.rua" />

      <label>Número</label>
      <input type="text" name="numero" id="numero" v-model="endereco.numero" />

      <label>Complemento</label>
      <input type="text" name="complemento" id="complemento" v-model="endereco.complemento" />

      <button class="btn">Finalizar cadastro</button>

      <button class="cancelar">
                <router-link :to="{ name: 'PerfilUsuarioView' }">Cancelar</router-link>
              </button>
    </form>
  </section>
</template>
  
<script>
import Endereco from "../services/EnderecoService";
import { mask } from "vue-the-mask";

export default {
  directives: { mask },
  data() {
    return {
      endereco: {
        uf: "",
        cep: "",
        cidade: "",
        bairro: "",
        rua: "",
        numero: null,
        complemento: "",
      },
      errors: [],
    };
  },

  methods: {
    criar() {
      Endereco.criar(this.endereco)
        .then(() => {
          alert("Endereço salvo com sucesso");
          this.errors = [];
          return this.$router.push({ name: 'PerfilUsuarioView' });
        })
        .catch((e) => {
          console.log(this.errors);

          if (e.response && e.response.status === 400) {
            this.errorMessage = 'Por favor, preencha todos os campos corretamente.';

          } else if (e.response && e.response.status === 401) {
            this.errorMessage = 'Favor realizar o Login para continuar.';

          } else if (e.response && e.response.status === 500) {
            this.errorMessage = 'Por favor, tente novamente mais tarde.';

          }

          //   else if (e.response && e.response.status === 409) {
          //   this.errorMessage = 'Já existe um usuário com este emai, nome, telefone ou CPF!';
          // } 

          else {
            this.errorMessage = 'Houve um erro. Por favor, tente novamente.';
          }

          console.log(this.errorMessage);
          alert(this.errorMessage);
        });
    },
  },

};
</script>
  
<style scoped>
.cadastro-final {
  max-width: 500px;
  margin: 0 auto;
  margin-top: -20px;
}

form {
  display: grid;
}

input {
  width: 300px;
  height: 0px;
  background-color: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  color: #9798b0;
  font-size: 16px;
  letter-spacing: -0.006em;
  padding: 20px;
  margin-right: auto;
  margin-left: auto;
}

.btn {
  width: 300px;
  height: 38px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 32px;
  font-weight: 600;
  color: #23272f;
  transition: 0.3s;
  margin-right: auto;
  margin-left: auto;
  margin-top: 30px;
  transition: 0.3s;
}

.btn:hover {
  background: #fcfcfc;
  border: 1px solid #9798b0;
  color: white;
}

.cancelar {
  width: 300px;
  height: 38px;
  background: #fcfcfc;
  border: 1px solid #9798b0;
  border-radius: 32px;
  font-weight: 600;
  color: #23272f;
  transition: 0.3s;
  margin-right: auto;
  margin-left: auto;
  margin-top: 30px;
  transition: 0.3s;
}

.cancelar:hover {
  background: #e2e2e2;
  border: 1px solid #e2e2e2;
  color: white;
}

label {
  margin-right: auto;
  margin-left: 80px;
  padding: 10px 10px 10px 0;
}

a {
  text-decoration: none;
}
</style>
  