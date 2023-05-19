<template>
  <header>
    <nav>
      <header class="menu-bg">
        <div class="menu">
          <div class="menu-logo">
            <a href="/"><img src="../assets/logo.png" alt="sKanboo" /></a>
          </div>

          <div class="search-bar">
            <input
              type="text"
              name="search"
              id="search"
              placeholder="O que você está procurando?"
            />
            <button type="submit" class="search-button">
              <img src="../assets/lupa.png" />
            </button>
          </div>
          <nav class="menu-nav">
            <ul>
              <li>
                <a href="/loginView"
                  ><img src="../assets/user.png" alt="Usuário" class="user"
                /></a>
              </li>

              <li>
                <a href="carrinho.html"
                  ><img src="../assets/cart.png" alt="Carrinho" class="cart"
                /></a>
              </li>
            </ul>
          </nav>
        </div>
      </header>
    </nav>
  </header>
  <a href="/UsuarioView">Tela do usuário | </a>
  <a href="/EditarUsuarioView">Tela editar usuário | </a>
  <a href="/PostarProdutoView">Tela postar produto | </a>
  <a href="/EditarProdutoView">Tela editar produto | </a>
  <a href="/PaginaAdmView">Tela admin </a>
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
  text-decoration: none;
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
  background-color: #fff;
  border-bottom: 1px solid #e2e2e2;
  height: 80px;
  max-width: 100%;
  margin: 0px !important;
}

nav {
  margin: 0px !important;
  background-color: #fff;
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

::placeholder {
  color: #9798b0;
}

.menu-logo img {
  width: 170px;
}

.cart {
  width: 30px;
}

.user {
  width: 26px !important;
  margin-right: 20px;
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
</style>
