<template>
  <section class="container">
    <h1>Alterar informações da conta</h1>
    <div class="usuario">
      <!--<div class="foto-usuario">
               
            </div>-->

      <div class="informacoes">
        <div class="dados-usuario">
          <div class="dados-conta">
            <form action="">
              <h2>Dados da conta</h2>
              <label for="email">Editar email</label>
              <input type="text" id="email" />

              <label for="email">Trocar senha</label>
              <input type="text" id="senha" placeholder="Senha atual" />
              <input type="text" id="nova-senha" placeholder="Nova senha" />
              <input
                type="text"
                id="nova-senha"
                placeholder="Confirmar senha"
              />
              <br />
              <h2>Dados pessoais</h2>
              <label for="nome">Editar nome</label>
              <input type="text" id="nome" />

              <label for="nome">Editar nascimento</label>
              <input type="date" id="nascimento" />

              <label for="telefone">Editar telefone</label>
              <input
                type="text"
                id="telefone"
                name="telefone"
                class="form-control cel-sp-mask"
              />

              <label for="cpf">Editar CPF</label>
              <input
                type="text"
                id="cpf"
                name="cpf"
                class="form-control cpf-mask"
              />
            </form>
          </div>
        </div>
      </div>
      <button class="salvar">Salvar alterações</button>
      <button class="cancelar">Cancelar</button>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      usuario: {
        email: "",
        senha: "",
      },
    };
  },
};
</script>

<style scoped>
.container {
  max-width: 720px;
  margin: 0 auto;
  text-align: left;
  margin-bottom: 30px;
}

.usuario {
  border: 1px solid #eae9e9;
  border-radius: 4px;
  padding: 20px;
}

.informacoes {
  display: flex;

  justify-content: flex-start;
}

li {
  list-style: none;
}

.foto-usuario {
  width: 120px;
  height: 120px;
  border-radius: 100%;
  background-color: lightgray;
  align-items: center;
  margin-left: 150px;
}

button {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 300px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-weight: 600;
  font-size: 14px;
  color: #515864;
  transition: 0.3s;
  margin-top: 10px;
  margin-left: 0;
}

.info-usuario p {
  color: #c0c2c7;
}

input {
  display: block;
  box-sizing: border-box;
  width: 680px;
  height: 40px;
  background-color: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  color: #9798b0;
  font-size: 16px;
  letter-spacing: -0.006em;
  padding: 20px;
  margin-bottom: 15px;
}

input::placeholder {
  color: #9798b0;
}

label {
  display: inline-block;
  color: #515864;
  margin-bottom: 5px;
  text-align: left !important;
}
h1,
h2 {
  padding: 10px;
  padding-left: 0;
}

.cancelar {
  width: 300px;
  height: 30px;
  background-color: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  color: #9798b0;
  font-size: 14px;
  letter-spacing: -0.006em;
}
</style>
