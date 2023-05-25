import { http } from "./config";

export default {
  criar: (endereco) => {
    return http.post("endereco", endereco, {
        headers: {
          Authorization: localStorage.getItem('token')
        }
      });
  },

  exibirInfo: () => {
    return http.get("endereco/me", {
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    });
  },

  atualizar: (endereco) => {
    return http.put("endereco/me", endereco, {
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    });
  },
};