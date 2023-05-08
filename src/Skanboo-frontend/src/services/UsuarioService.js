import { http } from './config';

export default {
  criar: (usuario) => {
    return http.post('usuario', usuario);
  },

  atualizar: (usuario) => {
    return http.put('usuario', usuario);
  },

  logar: (usuario) => {
    return http.post('login', usuario);
  }
};
