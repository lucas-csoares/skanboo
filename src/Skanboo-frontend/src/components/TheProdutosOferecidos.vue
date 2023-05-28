<template>
    <section class="products">
        <h1>Produtos oferecidos</h1>
        <div class="container">
            <div class="card">
                <h2>Meu produto</h2>
                <div class="card-img">
                    <img src="" alt="Foto do produto" class="card-img-produto" />
                </div>
            </div>
            <img src="../assets/flecha.png" class="flecha-img">
            <div class="card">
                <h2>Produto desejado</h2>
                <div class="card-img">
                    <img src="" alt="Foto do produto" class="card-img-produto" />
                </div>
                <div class="div-botao">
                    <button @click="criarOferta"><router-link
                            :to="{ name: 'EscolherProdutoView' }">Negociar</router-link></button>
                </div>
            </div>
        </div>
    </section>
</template>


  
<script>
import Postagem from "../services/PostagemService";
import Oferta from '../services/OfertaService';

export default {
    data() {
        return {
            postagem: {
                id: "",
                titulo: "",
                descricao: "",
                categoriaProduto: "",
                categoriaProdutoDesejado: "",
                status: "",
            },
            postagens: [],
        };
    },

    mounted() {

        Postagem.exibirPostagensUsuarioLogado()
            .then((resposta) => {
                const postagens = resposta.data;
                this.postagens = postagens;
            })
            .catch((e) => console.log(e.message));
    },

    methods: {
        carregarPostagens() {
            Postagem.exibirPostagensUsuarioLogado()
                .then((resposta) => {
                    const postagens = resposta.data;
                    this.postagens = postagens;
                })
                .catch((e) => console.log(e.message));
        },
        criarOferta() {

            const idOfertada = sessionStorage.getItem('idOfertada');
            const idOrigem = sessionStorage.getItem('idOrigem');

            console.log('Origem Id:', idOfertada);
            console.log('Origem Id:', idOrigem);

            Oferta.criar(idOfertada, idOrigem)
                .then(() => {
                    alert('Oferta realizada com sucesso!');
                    this.errors = [];
                })
                .catch((e) => console.log(e.message));
        },
    },
};
</script>
  
<style scoped>
* {
    box-sizing: border-box;
}

body,
h1,
h2,
h3,
ul,
li,
p,
dd,
dt,
dl {
    margin: 0px;
    padding: 0px;
}

a {
    text-decoration: none;
    color: #515864;
}

img {
    display: block;
    max-width: 100%;
}

.flecha-img {
    width: 100px;
    height: 50px;
    margin-top: 150px;
    margin-left: 10px;
    margin-right: 10px;
}

.container {
    width: 720px;
    display: flex;
    justify-content: left;
    margin-top: 20px;
    gap: 20px;
    flex-wrap: wrap;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 30px;
    border: 1px solid #e5e9eb;
    border-radius: 4px;
}

.products {
    margin-left: 200px;
    margin-right: 200px;
}

.card {
    display: block;
    width: 274px;
    height: 370px;
    background: #ffffff;
    flex: none;
}

.card img {
    margin-top: 10px;
}

.card h2 {
    padding: 8px;
    box-sizing: border-box;
    margin-left: 5px;
    margin-top: 5px;
}

.card-img-produto {
    height: 250px;
    width: 250px;
    object-fit: cover;
    display: block;
    margin-left: auto;
    margin-right: auto;
}

.categoria-produto {
    box-sizing: border-box;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding: 2px 6px 2px 8px;
    gap: 4px;
    width: 240px;
    height: 32px;
    background: #fdfcfc;
    border: 1px solid #36435a;
    border-radius: 4px;
    font-weight: 600;
    color: #36435a;
    margin-left: 15px;
}

h2 {
    font-size: 18px;
    color: rgb(12, 23, 29);
    kerning: -0.6%;
    font-weight: 400;
    text-align: left;
}

h1 {
    text-align: left;
}
</style>
  