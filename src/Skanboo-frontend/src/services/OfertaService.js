import { http } from './config';

export default {
  criar: (id_postagem_origem, id_postagem_ofertada, oferta) => {
    return http.post(`oferta/${id_postagem_origem}/${id_postagem_ofertada}`, oferta, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirOferta: (id) => {
    return http.get(`oferta/${id}`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirOfertasFeitas: () => {
    return http.get(`oferta/listar-feitas/me`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  exibirOfertasRecebidas: () => {
    return http.get(`oferta/listar-recebidas/me`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  atualizar: (id, oferta) => {
    return http.put(`oferta/${id}`, oferta, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  excluirOferta(id) {
    return http.delete(`oferta/${id}`, {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },
};
