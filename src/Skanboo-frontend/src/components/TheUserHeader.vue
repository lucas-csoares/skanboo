<template>
  <header>
    <nav>
      <header class="menu-bg">
        <div class="menu">
          <div class="menu-logo">
            <a href="/"><img src="../assets/logo.png" alt="sKanboo" /></a>
          </div>

          <nav class="menu-nav">
            <ul>
              <li>
                <a href="/">Home |</a>
              </li>
              <li>
                <a href="/postsdousuarioview">Meus produtos | </a>
              </li>
              <li>
                <a href="/ofertasrecebidasview">Ofertas recebidas | </a>
              </li>
              <li>
                <a href="/ofertaview">Ofertas feitas | </a>
              </li>
              <li>
                <a href="/">Trocas </a>
              </li>
              <li></li>
              <li>
                <a href="/usuarioview"
                  ><img
                    src="../assets/engrenagem.svg"
                    alt="Usuário"
                    class="user"
                /></a>
              </li>
            </ul>
          </nav>
        </div>
      </header>
    </nav>
  </header>
  <!--
  <a href="/UsuarioView">Tela do usuário | </a>
  <a href="/EditarUsuarioView">Tela editar usuário | </a>
  <a href="/PostarProdutoView">Tela postar produto | </a>
  <a href="/EditarProdutoView">Tela editar produto | </a>
  <a href="/PaginaAdmView">Tela admin </a> -->
</template>

<script>
export default {
  name: "TheHeader",
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}
body,
h1,
h2,
h3,
ul,
li,
p,
dd,
dt,
dl {
  margin: 0px !important;
  padding: 0px;
}

img {
  display: block;
  max-width: 100%;
}

ul {
  list-style: none;
}

a {
  font-weight: 400;
  text-decoration: none;
  letter-spacing: 2px;
  font-size: 12px;
  transition: 0.3s;
  transition-timing-function: linear;
  padding-left: 5px;
}

a:hover {
  font-weight: 400;
  text-decoration: none;
  letter-spacing: 1px;
  font-size: 12px;
}

body {
  font-size: 1rem;
  background: #fff;
  color: #222;
  -webkit-font-smoothing: antialiased;
  font-family: "Inter", sans-serif;
  color: #000;
}

.menu-bg {
  background-color: transparent;
  border-bottom: 1px solid #e2e2e2;
  height: 80px;
  max-width: 100%;
  margin: 0px !important;
}

nav {
  margin: 0px !important;
  background-color: transparent;
}

.menu {
  max-width: 1165px;
  margin: 0 auto;
  padding: 0px 0;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
}

.menu a {
  color: #000;
}

.menu-logo {
  margin-top: -2rem;
}

.menu-logo a {
  margin: 0 10px;
}

.search-bar {
  display: flex;
}

.menu-nav {
  margin-top: -1rem;
}

.menu-nav ul {
  display: flex;
}

.search-bar input {
  width: 680px;
  height: 40px;
  background-color: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  color: #9798b0;
  font-size: 16px;
  letter-spacing: -0.006em;
  padding: 20px;
  margin-top: -5px;
}

.search-bar input:hover {
  width: 680px;
  height: 40px;
  background-color: white;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  color: #9798b0;
  font-size: 16px;
  letter-spacing: -0.006em;
  padding: 20px;
}

.config {
  font-weight: 600;
}

::placeholder {
  color: #9798b0;
}

.menu-logo img {
  width: 170px;
}

.cart {
  width: 32px;
}

.user {
  width: 28px !important;
  padding-left: 6px;
}

.cart,
.user {
  margin-top: -20px;
}

button {
  transition-duration: 0.4s;
}

input {
  transition-duration: 0.4s;
}

.search-button {
  background: transparent;
  border: none;
  outline: none;
  margin-left: -40px;

  margin-top: -3px;
}

.search-button img {
  width: 20px;
  height: 20px;
  object-fit: cover;
}

@media (max-width: 600px) {
  .menu-logo {
    width: 100px;
    height: 100px;
    margin-top: 5px;
  }
  .search-bar input {
    width: 200px;
    padding: 5px;
    height: 30px;
    margin-left: 8px;
    margin-top: -10px;
    border: none;
    border-radius: 0;
    border-bottom: 1px solid black;
    font-size: 14px;
    text-align: center;
  }

  .search-bar input:hover {
    width: 220px;
    padding: 5px;
    height: 30px;
    margin-left: 5px;
    margin-top: -10px;
    border: none;
    border-radius: 0;
    border-bottom: 1px solid black;
    font-size: 14px;
    text-align: center;
  }

  .menu-bg {
    background-color: #fff;
    border-bottom: 0px solid #e2e2e2;
    height: 50px;
    max-width: 100%;
    margin: 0px !important;
  }

  .search-button img {
    display: none;
  }

  .cart {
    width: 17px;
  }
  .user {
    width: 16px !important;
    margin-right: 20px;
  }
}
@media (min-width: 601px) and (max-width: 960px) {
  .menu-logo {
    width: 150px;
  }
  .search-bar input {
    width: 300px;
    height: 40px;
    background-color: #fcfcfc;
    border: 1px solid #e2e2e2;
    border-radius: 4px;
    color: #9798b0;
    font-size: 16px;
    letter-spacing: -0.006em;
    padding: 20px;
    margin-top: -5px;
  }
}
</style>
