import { http } from './config';

export default {
  criar: (postagem) => {
    return http.post('postagem', postagem, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirPostagensUsuarioLogado: () => {
    return http.get('postagem/usuario', {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirInfoPostagem: (id) => {
    return http.get(`postagem/${id}`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  atualizar: (id, postagem) => {
    return http.put(`postagem/${id}`, postagem, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  excluirPostagem(id) {
    return http.delete(`postagem/${id}`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirTodasPostagens() {
    return http.get('postagem/lista');
  },

  exibirUsuarioPostagem(id) {
    return http.get(`postagem/usuario/${id}`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  }
};
