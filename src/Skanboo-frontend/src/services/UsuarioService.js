import { http } from "./config";

export default {
  criar: (usuario) => {
    return http.post("usuario", usuario);
  },

  exibirInfo: () => {
    return http.get("usuario", {
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    });
  },

  atualizar: (usuario) => {
    return http.put("usuario", usuario);
  },

  logar: (usuario) => {
    return http.post("login", usuario);
  },
};
