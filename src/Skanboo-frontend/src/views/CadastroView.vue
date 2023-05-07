<template>
  <section class="cadastro-final">
    <h1>Cadastrar usuário</h1>

    <ul>
      <li v-for="(error, index) of errors" :key="index">
        campo <b>{{ error.field }}</b> - {{ error.defaultMessage }}
      </li>
    </ul>

    <form @submit.prevent="criar">
      <label>Nome</label>
      <input type="text" name="nome" id="nome" v-model="usuario.nome" />

      <label>Email</label>
      <input type="text" name="email" id="email" v-model="usuario.email" />

      <label>Senha</label>
      <input type="password" name="senha" id="senha" v-model="usuario.senha" />

      <label>Data de nascimento</label>
      <input type="date" name="nascimento" id="nascimento" v-model="usuario.dataNascimento" />

      <label>CPF</label>
      <input type="text" name="cpf" id="cpf" placeholder="xxx.xxx.xxx-xx" v-model="usuario.cpf" />

      <!-- <label for="foto">Selecione uma foto de perfil</label>
      <input type="file" id="foto" name="foto" accept="image/*" /> -->

      <label>Telefone</label><input type="text" id="telefone" name="telefone" placeholder="(xx)xxxxx-xxxx" v-model="usuario.telefone" />

      <butto*n class="btn">Criar a sua conta</button>
    </form>
  </section>
</template>

<script>
import Usuario from '../services/UsuarioService';

export default {
  data() {
    return {
      usuario: {
        nome: '',
        cpf: '',
        email: '',
        senha: '',
        dataNascimento: '',
        telefone: '',
      },
      errors: [],
    };
  },

  methods: {
    criar() {
      Usuario.criar(this.usuario)
        .then((/*resposta*/) => {
          alert('Usuario salvo com sucesso');
          this.errors = [];
        })
        .catch((e) => {
          this.errors = e.response.data.errors;
          console.log(this.errors);
        });
    },

    atualizar(usuario) {
      this.usuario = usuario;
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
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #e2e2e2;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;
  margin-right: auto;
  margin-left: auto;
  margin-top: 20px;
}

label {
  margin-right: auto;
  margin-left: 80px;
  padding: 10px 10px 10px 0;
}
</style>
