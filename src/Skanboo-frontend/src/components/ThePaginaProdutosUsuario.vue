<template>
  <section class="all">
    <section class="header">
      <div class="foto">
        <img src="images/rosto.avif" alt="" />
      </div>
      <h1 class="nome">Paulo Almeida</h1>
      <div class="local">
        <span><b>Localidade</b>: </span>
        <span class="localidade">Belo Horizonte - MG</span>
      </div>
    </section>

    <section class="products">
      <h1>Anúncios</h1>
      <div class="container">
        <div class="card">
          <!-- <button class="like">
            <a href="http://"><img src="../assets/like.png" alt="" /></a>
          </button> -->
          <div class="card-img-produto">
            <img src="../assets/ex1.jpg" alt="" class="card-img" />
          </div>
          <h2>Air Fryer</h2>
          <button class="negociar">Negociar</button>
        </div>

        <div class="card">
          <!-- <button class="like"><img src="../assets/like.png" alt="" /></button> -->
          <div class="card-img-produto">
            <img src="../assets/ex2.jpg" alt="" class="card-img" />
          </div>
          <h2>Título</h2>
          <button class="negociar">Negociar</button>
        </div>

        <div class="card">
          <!-- <button class="like"><img src="../assets/like.png" alt="" /></button> -->
          <div class="card-img-produto">
            <img src="../assets/ex3.jpg" alt="" class="card-img" />
          </div>
          <h2>Título</h2>
          <button class="negociar">Negociar</button>
        </div>

        <div class="card">
          <!-- <button class="like"><img src="../assets/like.png" alt="" /></button> -->
          <div class="card-img-produto">
            <img src="../assets/ex11.jpg" alt="" class="card-img" />
          </div>
          <h2>Título</h2>
          <button class="negociar">Negociar</button>
        </div>
      </div>
    </section>
  </section>
</template>

<script>
export default {};
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
  margin: 0px;
  padding: 0px;
}

.header {
  display: flex;
  align-items: center;
  max-width: 1150px;
  margin: 0 auto;
  border-bottom: 1px solid #e2e2e2;
  height: 130px;
  margin-bottom: 20px;
}

.header img {
  width: 100px;
  height: 100px;
  border-radius: 100%;
  background-color: grey;
  object-fit: cover;
}

img {
  display: block;
  max-width: 100%;
}

h1 {
  display: inline-block;
  margin-left: 30px;
}

.local {
  margin-left: 50px;
}

.container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
  gap: 20px;
}

.products {
  margin-left: 200px;
  margin-right: 200px;
}

.card {
  width: 274.5px;
  height: 370px;
  background: #ffffff;
  border: 1px solid #e5e9eb;
  border-radius: 4px;
  flex: none;
  order: 0;
  flex-grow: 1;
  margin-bottom: 30px;
}

.card img {
  margin-top: -20px;
}

.card h2 {
  padding: 10px;
  box-sizing: border-box;
  margin-left: 5px;
  margin-top: 20px;
}

.card-img-produto img {
  max-height: 200px;
  margin-top: 20px;
}

.editar {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 240px;
  height: 32px;
  background: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
}

.editar:hover {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 240px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
}

.excluir {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 240px;
  height: 32px;
  background: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
}

.excluir:hover {
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 240px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  margin-left: 15px;
}

h2 {
  font-size: 14px;
  color: #252c32;
  kerning: -0.6%;
  font-weight: 400;
  text-align: left;
}

.products h1 {
  margin-left: -20px;
  display: block;
  text-align: left !important;
  font-size: 24px;
}

.negociar {
  width: 220px;
  transition: 0.3s;
}

.negociar:hover {
  width: 220px;
  background: #f9dc5c;
}
</style>
