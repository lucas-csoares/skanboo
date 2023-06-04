<template>
  <footer>
    <p>Todos os direitos reservados.</p>
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
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 100px;
}

p {
  margin-top: 40px !important;
  color: white;
  display: inline-block;
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

@media (max-width: 600px) {
  .info {
    margin: 0 auto;
  }
  .info li {
    line-height: 30px;
    font-size: 12px;
    font-weight: 400;
    text-align: left;
  }
  footer {
    background-color: black;
    margin: 0px !important;
    padding: 0px;
    width: 100%;
  }
  .newsletter {
    background-color: black;
    height: 300px;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    align-items: center;
    margin-top: 100px;
  }

  .newsletter input {
    background-color: transparent;
    border: none;
    border-bottom: 1px solid white;
    border-radius: 0;
    width: 200px;
    margin-top: 20px;
    margin-left: 185px;
  }

  .newsletter button {
    font-weight: 400;
    margin-top: 20px;
    box-sizing: border-box;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding: 2px 6px 2px 8px;
    gap: 4px;
    position: relative;
    width: 200px;
    height: 32px;
    color: white;
    background: #000000;
    border: 1px solid #ffffff;
    border-radius: 16px;
  }

  .newsletter button:hover {
    margin-top: 20px;
    box-sizing: border-box;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding: 2px 6px 2px 8px;
    gap: 4px;
    position: relative;
    width: 200px;
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
}
</style>
