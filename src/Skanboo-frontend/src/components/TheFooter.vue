<template>
  <footer>
    <div class="newsletter">
      <div class="input-news">
        <input
          type="text"
          name="newsletter"
          id="newsletter"
          placeholder="Digite o seu email"
        /><br />
        <button class="btn-newsletter">RECEBA NOVIDADES</button>
      </div>

      <div class="info">
        <ul>
          <li>SOBRE</li>
          <li>FAQ</li>
          <li>POLÍTCA DE PRIVACIDADE</li>
          <li>TERMOS DE USO</li>
          <li>TROCAS E DEVOLUÇÕES</li>
        </ul>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "TheFooter",
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
  margin: 0px;
  padding: 0px;
}
footer {
  background-color: black;
  margin: 0px !important;
  padding: 0px;
}
.newsletter {
  background-color: black;
  height: 300px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  max-width: 1340px;
}

.newsletter input {
  background-color: transparent;
  border: none;
  border-bottom: 1px solid white;
  border-radius: 0;
  width: 390px;
  margin-top: 20px;
  margin-left: 185px;
}

.newsletter button {
  font-weight: 400;
  margin-top: 20px;
  margin-left: 185px;
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  position: relative;
  width: 390px;
  height: 32px;
  color: white;
  background: #000000;
  border: 1px solid #ffffff;
  border-radius: 16px;
}

.newsletter button:hover {
  margin-top: 20px;
  margin-left: 185px;
  box-sizing: border-box;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  position: relative;
  width: 390px;
  height: 32px;
  color: black;
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
  border-radius: 16px;
}

.newsletter ::placeholder {
  font-weight: 400;
  font-size: 10px;
  line-height: 18px;
  text-align: start;
  letter-spacing: 0.175em;
  color: #ffffff;
}

ul {
  list-style: none;
}

.info {
  color: white;
  font-weight: 700;
}

.info li {
  line-height: 30px;
  font-size: 16px;
  font-weight: 400;
  text-align: left;
}
</style>
