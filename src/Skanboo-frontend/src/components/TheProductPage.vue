<template>
  <section class="container">
    <div class="imagem">
      <img src="../assets/imagem-produto.avif" alt="Foto do Produto" />
    </div>
    <div class="informacoes-produto">
      <h1>Título produto</h1>
      <p>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Sunt provident
        architecto minus voluptatem dolorum eaque at in. Laborum minima, cum
        soluta sint odit, veniam eos eius sit quibusdam suscipit ducimus!
      </p>
      <button>Negociar</button>
    </div>
  </section>
</template>

<script>
export default {};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  max-width: 800px;
  padding: 10px;
  text-align: left;
  margin: 0 auto;
}

.imagem {
  width: 400px;
  height: 500px;
  background-color: grey;
}

img {
  max-width: 400px;
  max-height: 500px;
  object-fit: cover;
}

.informacoes-produto {
  margin-left: 50px;
}

button {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 400px;
  height: 32px;
  background: transparent;
  border: 1px solid #000;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;

  margin-top: 10px;
}

button:hover {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 400px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;

  margin-top: 10px;
}

p {
  line-height: 1.5em;
  padding: 20px 0px;
  font-size: 16px;
  text-align: justify;
}

h1 {
  font-size: 2em;
}
</style>
