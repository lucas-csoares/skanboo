import { http } from './config';

export default {
  criar: (parceiro) => {
    return http.post('parceiro', parceiro, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirInfo: (id) => {
    return http.get(`parceiro/${id}`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirTodosParceiros() {
    return http.get('parceiro/lista');
  },

  atualizar: (parceiro) => {
    return http.put('parceiro', parceiro, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  excluir(id) {
    return http.delete(`parceiro/${id}`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  }
};
