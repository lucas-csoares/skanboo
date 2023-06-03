import { http } from './config';

export default {

    quantidadeUsuariosCriadosMes: () => {
        return http.get("/indicador/usuarios-criados-mes", {
            headers: {
                Authorization: localStorage.getItem('token')
            }
        })
    },

    quantidadePostagensCriadosMes: () => {
        return http.get("/indicador/postagens-criadas-mes", {
            headers: {
                Authorization: localStorage.getItem('token')
            }
        })
    },

    categoriasMaisDesejadas: () => {
        return http.get("/indicador/categorias-mais-desejadas", {
            headers: {
                Authorization: localStorage.getItem('token')
            }
        })
    },

    categoriasMaisOfertadas: () => {
        return http.get("/indicador/categorias-mais-ofertadas", {
            headers: {
                Authorization: localStorage.getItem('token')
            }
        })
    }
}