<template>
  <div id="app">
    <TheHeader />
    <main id="main">
    </main>
    <router-view />
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader.vue";

export default {
  components: {
    TheHeader,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
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
