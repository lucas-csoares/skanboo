<template>
  <section class="login">
    <h1>Login</h1>
    <form @submit.prevent="logar">
      <label for="email">Email</label>
      <input type="email" name="email" id="email" v-model="usuario.email" />

      <label for="email">Senha</label>
      <input type="password" name="senha" id="senha" v-model="usuario.senha" />

      <button class="btn">Entrar</button>
      <p>Não tem uma conta? <a href="/cadastroView">Criar conta.</a></p>
    </form>
  </section>
</template>

<script>
import Usuario from '../services/UsuarioService';

export default {
  data() {
    return {
      usuario: {
        email: '',
        senha: '',
      },
    };
  },

  //redireciona o usuario para a tela do usuario caso esteja logado, caso contrario, permanece na pagina de login
  beforeRouteEnter(to, from, next) {
    const token = localStorage.getItem('token');

    if (token) next({ name: 'usuarioView' });
    else next();
  },

  methods: {
    logar() {
      Usuario.logar(this.usuario)
        .then((resposta) => {
          alert('Usuario logado com sucesso');

          console.log(resposta);
          const token = resposta.headers.getAuthorization();

          if (!token) throw new Error('Ocorreu um erro ao tentar logar usuário!');
          localStorage.setItem('token', token);
          //aqui redireciona pagina
          return this.$router.push({ name: 'usuarioView' });
          // this.errors = [];
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
.login {
  max-width: 500px;
  margin: 0 auto;
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

a {
  text-decoration: none;
  font-weight: bold;
}
</style>
