// import { http } from './config';

export default {
  criar: (usuario) => {
    // eslint-disable-next-line no-unused-vars
    return new Promise((resolve, _reject) => {
      // não comitar só pra teste
      resolve({
        data: `CRIADO ${usuario.nome}`,
      });
    });
    // return { // não comitar só pra teste
    //   data: "ok"
    // }
    // return http.post('usuario', usuario);
  },

  exibirInfo: () => {
    // eslint-disable-next-line no-unused-vars
    return new Promise((resolve, _reject) => {
      resolve({
        // não comitar só pra teste
        data: {
          nome: "Fulano Teste",
          nascimento: "1999-01-01",
          email: "teste@dofulano.com",
          telefone: "999999999",
          cpf: "99999999999",
        },
      });
    });
    // return http.get('usuario', {
    //   headers: {
    //     Authorization: localStorage.getItem('token')
    //   }
    // });
  },

  atualizar: (usuario) => {
    // eslint-disable-next-line no-unused-vars
    return new Promise((resolve, _reject) => {
      // não comitar só pra teste
      resolve({
        data: `ATUALIZADO ${usuario.nome}`,
      });
    });
    // return http.put('usuario', usuario);
  },

  logar: (usuario) => {
    // eslint-disable-next-line no-unused-vars
    return new Promise((resolve, _reject) => {
      // não comitar só pra teste
      resolve({
        data: `LOGADO ${usuario.email}`,
        headers: {
          getAuthorization: () => {
            return "tokenSuperSecreto";
          },
        },
      });
    });

    // return { // não comitar só pra teste
    //   headers: {
    //     getAuthorization: "tokenSuperSecreto"
    //   }
    // }
    // return http.post('login', usuario);
  },
};
