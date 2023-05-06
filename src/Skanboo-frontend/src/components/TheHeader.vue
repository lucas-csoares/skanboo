<template>
  <header>
    <nav>
      <router-link to="/login" class="logo"><button>Login</button></router-link>

      <router-link to="/cadastroinicial" class="logo">
      <button>Cadastro Inicial</button>
      </router-link>

      <router-link to="/cadastrofinal" class="logo"><button>Cadastro Final</button></router-link>
    </nav>
  </header>
</template>

<script>
export default {
  name: 'TheHeader',
};
</script>

<style></style>
