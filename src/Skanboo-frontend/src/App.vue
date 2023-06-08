<template>
  <div id="app">
    <HeaderUsuarioLogadoComponent v-if="usuarioLogado === 'USER'" :atualizarPagina="atualizarPagina" />
    <HeaderAdmLogadoComponent v-else-if="usuarioLogado === 'ADMIN'" :atualizarPagina="atualizarPagina" />
    <HeaderComponent v-else :atualizarPagina="atualizarPagina" />
    <router-view />
    <TheFooter />
  </div>
</template>

<script>
import HeaderComponent from "@/components/HeaderComponent.vue";
import HeaderUsuarioLogadoComponent from "@/components/HeaderUsuarioLogadoComponent.vue";
import HeaderAdmLogadoComponent from "@/components/HeaderAdmLogadoComponent.vue";
import TheFooter from "@/components/TheFooter.vue";
import Usuario from "./services/UsuarioService";

export default {
  data() {
    return {
      usuario: {},
    };
  },

  components: {
    HeaderComponent,
    HeaderUsuarioLogadoComponent,
    HeaderAdmLogadoComponent,
    TheFooter,
  },

  computed: {
    usuarioLogado() {
      if (this.usuario && this.usuario.perfil && this.usuario.perfil.length > 0) {
        const perfil = this.usuario.perfil[0];
        console.log(perfil);
        return perfil;
      } else {
        console.log("perfil is undefined or empty");
        return false;
      }
    },
  },

  mounted() {
    Usuario.exibirInfo()
      .then((resposta) => {
        const usuario = resposta.data;
        this.usuario = usuario;
        console.log(this.usuario);
      })
      .catch((e) => console.log(e.message));
  },
};
</script>

<style>
html {
  height: 100%;
}

body {
  min-height: 100vh;
}

section {
  min-height: 100vh;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: -0px !important;
}

nav {
  padding: 30px;
  margin: 0px !important;
}

nav a.router-link-exact-active {
  color: #42b983;
}

button {
  background: transparent;
  padding: 10px;
  margin: 20px;
  border-radius: 24px;
  border: 1px solid grey;
}

input::placeholder {
  color: #97a5cb;
}

label {
  color: #515864;
}

select {
  background-color: transparent;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  height: 40px;
  padding: 10px;
  width: 340px;
  margin-left: 80px;
}
</style>
