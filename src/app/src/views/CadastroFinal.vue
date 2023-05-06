<template>
  <section class="cadastro-final">
    <h1>Cadastro de usuário</h1>
    <form>
      <label for="user">Usuário</label>
      <input
        type="text"
        name="usuario"
        id="usuario"
        v-model="cadastroFinal.usuario"
      />

      <label for="email">Senha</label>
      <input type="password" name="senha" id="senha" />
      <label for="email">Confirmar a senha</label>
      <input
        type="password"
        name="senha"
        id="senha"
        v-model="cadastroFinal.senha"
      />

      <label for="nascimento">Data de nascimento</label>
      <input type="date" name="nascimento" id="nascimento" />

      <label for="cpf">CPF</label>
      <input type="number" name="cpf" id="cpf" placeholder="xxx.xxx.xxx-xx" />

      <label for="foto">Selecione uma foto de perfil</label>
      <input type="file" id="foto" name="foto" accept="image/*" />

      <label for="telefone">Telefone</label>
      <input
        type="tel"
        id="telefone"
        name="telefone"
        placeholder="(xx) xxxxx-xxxx"
      />

      <router-link to="/login" class="logo">
        <button class="btn">Continuar</button>
      </router-link>
    </form>
  </section>
</template>

<script>
export default {
  data() {
    return {
      cadastroFinal: {
        usuario: "",
        senha: "",
        nascimento: "",
        cpf: "",
        foto: "",
        telefone: "",
      },
    };
  },
};
</script>

<style scoped>
.cadastro-final {
  max-width: 500px;
  margin: 0 auto;
  margin-top: -20px;
}
form {
  display: grid;
}

input {
  width: 300px;
  height: 0px;
  background-color: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  color: #9798b0;
  font-size: 16px;
  letter-spacing: -0.006em;
  padding: 20px;
  margin-right: auto;
  margin-left: auto;
}

.btn {
  width: 300px;
  height: 32px;
  background: #f9dc5c;
  border: 1px solid #e2e2e2;
  border-radius: 16px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;
  margin-right: auto;
  margin-left: auto;
  margin-top: 20px;
}

label {
  margin-right: auto;
  margin-left: 80px;
  padding: 10px 10px 10px 0;
}
</style>
