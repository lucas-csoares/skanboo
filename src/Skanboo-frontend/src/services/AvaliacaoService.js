import { http } from './config';

export default {
  criar: (id_troca, avaliacao) => {
    return http.post(`avaliacao/${id_troca}`, avaliacao, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirAvaliacao: (id) => {
    return http.get(`avaliacao/${id}`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirAvaliacoes: () => {
    return http.get(`avaliacao/lista/me`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  excluirAvaliacao(id) {
    return http.delete(`avaliacao/${id}`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },
};
