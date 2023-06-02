import { http } from './config';

export default {

    quantidadeUsuariosCriadosMesAtual: () => {
        return http.get("/indicador/usuarios-criados-mes-atual", {
            headers: {
                Authorization: localStorage.getItem('token')
            }
        })
    },

    quantidadeUsuariosCriadosMes: () => {
        return http.get("/indicador/usuarios-criados-mes", {
            headers: {
                Authorization: localStorage.getItem('token')
            }
        })
    }
}