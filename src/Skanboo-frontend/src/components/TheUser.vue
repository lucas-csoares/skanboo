<template>
  <section class="container">
    <h1>Informações da conta</h1>
    <div class="usuario">
      <div class="foto-usuario">
        <img src="" alt="" />
      </div>

      <div class="informacoes">
        <div class="dados-usuario">
          <ul>
            <li>
              <p class="nome">Nome</p>
              <p class="nascimento">Nascimento</p>
              <p class="email">Email</p>
              <p class="telefone">Telefone</p>
              <p class="senha">Senha</p>
              <p class="CPF">CPF</p>
            </li>
          </ul>
        </div>

        <div class="info-usuario">
          <p class="nome">{{usuario.nome}}</p>
          <p class="nascimento">*****</p>
          <p class="email">{{usuario.email}}</p>
          <p class="telefone">{{usuario.telefone}}</p>
          <p class="senha">*****</p>
          <p class="CPF">{{usuario.cpf}}</p>
        </div>
      </div>
      <button class="editar"><a href="/editarUsuarioView">Editar</a></button>
    </div>

    <h1>Gerenciar produtos</h1>
  </section>
</template>

<script>

import Usuario from '../services/UsuarioService'

export default {
  data() {
    return {
      usuario: {
        nome: "",
        nascimento: "",
        email: "",
        senha: "",
      },
    };
  },

  mounted() {
    Usuario.exibirInfo().then(resposta => {
      console.log(resposta.data);
      this.usuario = resposta.data ;
    })
  }
};
</script>

<style scoped>
.container {
  max-width: 420px;
  margin: 0 auto;
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
  color: #515864;
  transition: 0.3s;
  margin-left: 30px;
  margin-top: 30px;
}
p {
  font-size: 1rem;
  text-align: left !important;
  padding: 10px;
}
.dados-usuario {
  margin-left: 30px;
}
.info-usuario p {
  color: #c0c2c7;
  margin-left: 50px;
}

h1,
h2 {
  padding: 10px;
  padding-left: 0;
}
</style>
