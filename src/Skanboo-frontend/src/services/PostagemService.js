import { http } from './config';

export default {
  criar: (postagem) => {
    return http.post('postagem', postagem);
  },

  exibirInfo: () => {
    return http.get('postagem/usuario', {
      headers: {
        Authorization: localStorage.getItem('token')
      }
    });
  },

  atualizar: (postagem) => {
    return http.put('postagem', postagem);
  },
};
