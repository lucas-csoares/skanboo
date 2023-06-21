<template>
  <header>
    <nav>
      <header class="header">
        <div class="menu">
          <div class="menu-logo">
            <a href="/"><img src="../assets/logo.png" alt="sKanboo" /></a>
          </div>

          <nav class="menu-nav">
            <ul>
              <li><a href="/postagemusuarioview">Meus produtos</a></li>
              <li><a href="/ofertasrecebidasview">Ofertas recebidas</a></li>
              <li><a href="/ofertasfeitasView">Ofertas feitas</a></li>
              <li><a href="/paginatrocasview">Trocas</a></li>
              <li class="pag-adm"><a href="/parceiroscadastradosview">Parceiros</a></li>
              <li class="pag-adm"><a href="/IndicadoresView">Indicadores</a></li>
              <li class="icone-usuario">
                <a href="/perfilusuarioview"><img src="../assets/user-icon.svg" alt="Usuário" class="user" /></a>
              </li>
            </ul>
          </nav>
        </div>
      </header>
    </nav>
  </header>
</template>

<script>
export default {
  name: 'TheHeader',
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
  font-family: 'Inter', sans-serif;
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
  bottom: 28px;
  transition: all 200ms;
}
.pag-adm {
  background: #e2e2e2;
  padding: 2px;
  border-radius: 2px;
}

.icone-usuario img:hover {
  filter: invert(98%) sepia(91%) saturate(1210%) hue-rotate(321deg) brightness(106%) contrast(95%);
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
