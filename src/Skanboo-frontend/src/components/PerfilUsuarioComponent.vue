<template>
  <section class="container">
    <h1>Informações da conta</h1>
    <div class="usuario">
      <div class="foto-usuario">
        <img :src="usuario.foto" alt="" />
      </div>

      <div class="informacoes">
        <div class="dados-usuario">
          <p class="nomeUsuario">Nome</p>
          <p class="nascimentoUsuario">Nascimento</p>
          <p class="emailUsuario">Email</p>
          <p class="telefoneUsuario">Telefone</p>
          <p class="senhaUsuario">Senha</p>
          <p class="CPFUsuario">CPF</p>
          <p class="cep">Endereço</p>
        </div>

        <div class="info-usuario">
          <p class="nome">{{ usuario.nome }}</p>
          <p class="nascimento">
            {{
              usuario.dataNascimento
                ? `${usuario.dataNascimento[2]}/${usuario.dataNascimento[1]}/${usuario.dataNascimento[0]}`
                : ''
            }}
          </p>
          <p class="email">{{ usuario.email }}</p>
          <p class="telefone">{{ usuario.telefone }}</p>
          <p class="senha">************</p>
          <p class="CPF">{{ usuario.cpf }}</p>
          <p class="cepRuaNumero">{{ endereco.cidade }}, Rua {{ endereco.rua }}, {{ endereco.numero }}</p>
        </div>
      </div>
      <button class="editar-endereco"><a href="/editarEnderecoView">Editar endereço</a></button>

      <button class="editar"><a href="/editarUsuarioView">Editar</a></button>
      <button class="sair" @click="logout">Sair</button>
    </div>
  </section>
</template>

<script>
import Usuario from '../services/UsuarioService';
import Endereco from '../services/EnderecoService';

export default {
  data() {
    return {
      usuario: {
        nome: '',
        nascimento: '',
        email: '',
        telefone: '',
        senha: '',
        cpf: '',
        foto: null,
      },
      endereco: {
        uf: '',
        cep: '',
        cidade: '',
        bairro: '',
        rua: '',
        numero: null,
        complemento: '',
      },
      loaded: false,
    };
  },

  mounted() {
    this.carregarInformacoesUsuario();
  },

  methods: {
    carregarInformacoesUsuario() {
      Usuario.exibirInfo()
        .then((resposta) => {
          this.usuario = resposta.data;
        })
        .catch((error) => {
          console.error(error);
        });

      Endereco.exibirInfo()
        .then((resposta) => {
          this.endereco = resposta.data[0];
        })
        .catch((error) => {
          console.error(error);
        });
    },

    logout() {
      localStorage.removeItem('token');
      this.$router.push({ name: 'loginView' });
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 500px;
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

.informacoes-endereco {
  display: flex;
  justify-content: center;
  border: 2px solid #c0c2c7;
  border-radius: 10px;
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
  margin-left: 180px;
}

img {
  border-radius: 50% 50% 50% 50%;
  width: 120px;
  height: 120px;
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

.editar-endereco {
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 150px;
  height: 28px;
  background: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;
  margin-left: 30px;
  margin-top: 0px;
}

p {
  font-size: 1rem;
  text-align: left !important;
  padding: 10px;
}

.informacoes-endereco p {
  font-size: 1rem;
  text-align: left !important;
  padding: 0px;
}

.dados-usuario {
  margin-left: 30px;
}

.info-usuario p {
  color: #c0c2c7;
  margin-left: 50px;
}

.info-endereco {
  color: #c0c2c7;
  margin-left: 50px;
}

h1,
h2 {
  padding: 10px;
  padding-left: 0;
}

.sair {
  cursor: pointer;
  box-sizing: border-box;
  padding: 2px 6px 2px 8px;
  gap: 4px;
  width: 300px;
  height: 32px;
  background: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-weight: 600;
  color: #515864;
  transition: 0.3s;
  margin-left: 30px;
  margin-top: 0px;
}

.gerenciar-produtos a {
  line-height: 2em;
  text-decoration: none;
  text-align: left !important;
}
</style>
