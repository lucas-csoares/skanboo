<template>
  <header>
    <nav>
      <header class="header">
        <div class="menu">
          <div class="menu-logo">
            <a href="/"><img src="../assets/logo.png" alt="sKanboo" /></a>
          </div>

          <nav class="menu-nav">
            <div class="dropdown">
              <button class="dropbtn"><b>Menu</b></button>
              <div class="dropdown-content">
                <a href="/postagemusuarioview">Meus produtos</a>
                <a href="/ofertasrecebidasview">Ofertas recebidas</a>
                <a href="/ofertasfeitasview">Ofertas feitas</a>
                <a href="/paginatrocasview">Trocas</a>
                <a href="/parceiroscadastradosview">Parceiros</a>
                <a href="/IndicadoresView">Indicadores</a>
              </div>
            </div>
            <div class="icone-usuario">
              <a href="/perfilusuarioview"
                ><img src="../assets/user-icon.svg" alt="Usuário" class="user"
              /></a>
            </div>
          </nav>
        </div>
      </header>
    </nav>
  </header>
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
  margin: 0;
  padding: 0;
}

img {
  display: block;
  max-width: 100%;
}

ul {
  list-style: none;
}

a {
  color: #919191;
  font-weight: bold;
  transition: all 300ms;
}

a:hover {
  color: black;
}

body {
  font-size: 1rem;
  background: #fff;
  color: #222;
  -webkit-font-smoothing: antialiased;
  font-family: "Inter", sans-serif;
  color: #000;
}

.header {
  background-color: #fff;
  height: 60px;
}

.menu {
  width: 100%;
  margin: 0 auto;
  padding: 0;
  display: flex;
  position: relative;
  justify-content: space-between;
}

nav {
  border-bottom: 1px solid #e2e2e2;
}

a {
  transition: 0.3s;
  padding-bottom: 40px;
}

a:hover {
  padding-bottom: 40px;
  border-bottom: 4px solid #f9dc5c;
}

.menu-logo {
  height: 80px;
}

.menu-logo img {
  width: 170px;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  margin-left: 50px;
  margin-top: -10px;
}

.menu-nav {
  border-bottom: none;
}

.menu-nav ul {
  display: flex;
}

.menu-nav ul li {
  margin-right: 30px;
}

.icone-usuario img {
  position: absolute;
  width: 30px;
  margin-top: -60px;
  margin-left: 200px;
  transition: all 200ms;
}

.dropbtn {
  background-color: #f9dc5c;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  width: 150px;
  height: 50px;
}

.dropdown {
  position: relative;
  display: inline-block;
  margin-top: -50px;
  margin-right: 60px;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {
  background-color: #ddd;
  border-bottom: 0px;
}

.dropdown:hover .dropdown-content {
  display: block;
}

.dropdown:hover .dropbtn {
  background-color: #ffe677;
}

.icone-usuario img:hover {
  filter: invert(98%) sepia(91%) saturate(1210%) hue-rotate(321deg)
    brightness(106%) contrast(95%);
}

@media (max-width: 600px) {
  .menu-logo {
    width: 100px;
    height: 100px;
    margin-top: 5px;
  }

  .header {
    background-color: #fff;
    border-bottom: 0;
    height: 50px;
    max-width: 100%;
    margin: 0 !important;
  }
}

@media (min-width: 601px) and (max-width: 960px) {
  .menu-logo {
    width: 150px;
  }
}
</style>
