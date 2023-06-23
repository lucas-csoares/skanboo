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
              <li><a href="/loginView">Login</a></li>
              <li><a href="/cadastroview" class="criar-conta">Quero trocar</a></li>
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

.menu-nav ul {
  display: flex;
}

.menu-nav {
  border-bottom: none;
}

.menu-nav ul li {
  margin-right: 30px;
}

.menu-nav ul li:nth-of-type(2) {
  background: #f26a63;
  padding: 10px;
  margin-top: -10px;
  border-radius: 16px;
}

.menu-nav ul li:nth-of-type(2) a {
  color: white;
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
