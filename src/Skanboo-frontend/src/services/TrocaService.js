import { http } from './config';

export default {
  criar: (id_oferta) => {
    return http.post(`troca/${id_oferta}`, null, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirTroca: (id) => {
    return http.get(`troca/${id}`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirTrocas: () => {
    return http.get(`troca/lista`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  atualizar: (id, troca) => {
    return http.put(`troca/${id}`, troca, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  excluirTroca(id) {
    return http.delete(`troca/${id}`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },
};
