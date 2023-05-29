<template>
    <div v-if="endereco">
        <section class="container">
            <h1>Editar Endereço</h1>

            <div class="endereco">
                <div class="dados-endereco">
                    <form @submit.prevent="atualizar">


                        <label for="uf">UF</label>
                        <select :placeholder="endereco.uf" v-model="endereco.uf">
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
                        <input type="text" name="cep" id="cep" :placeholder="endereco.cep" v-model="endereco.cep"
                            v-mask="['#####-###']" />

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

                        <button class="btn">Salvar alterações</button>

                        <button class="cancelar">
                            <router-link :to="{ name: 'UsuarioView' }">Cancelar</router-link>
                        </button>
                    </form>
                </div>
            </div>
        </section>
    </div>
</template>
  
<script>
import Endereco from '../services/EnderecoService';
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

    mounted() {
        Endereco.exibirInfo().then((resposta) => {
            this.endereco = resposta.data[0];
            return this.endereco;
        });
    },

    methods: {
        atualizar() {
            Endereco.atualizar(this.endereco)
                .then(() => {
                    alert('Endereco editado com sucesso!');
                    this.errors = [];

                    this.$router.push({ name: 'UsuarioView' });
                })
                .catch((e) => {
                    alert('Todos os campos do endereco devem ser preenchidos!');
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

.endereco {
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
    margin-left: 18px;
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
    padding: 10px;
    margin-left: 0;
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
  