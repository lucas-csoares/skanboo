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
import Usuario from "../services/UsuarioService";

export default {
  data() {
    return {
      usuario: {
        email: "",
        senha: "",
      },
    };
  },

  mounted() {
    if (!window.location.hash) {
      window.location = window.location + "#loaded";
      window.location.reload();
    }
  },

  methods: {
    logar() {
      Usuario.logar(this.usuario)
        .then((resposta) => {
          const token = resposta.headers.getAuthorization();
          localStorage.setItem("token", token);
          alert("Usuario logado com sucesso");
          this.$router.push({ name: "PerfilUsuarioView" });
        })
        .catch((e) => {
          this.errors = e.response.data.errors;
          console.log(this.errors);
        });
    },
  },

  atualizarPagina() {},
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
  background: #569e15;
  border: 1px solid #569e15;
  color: white;
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
