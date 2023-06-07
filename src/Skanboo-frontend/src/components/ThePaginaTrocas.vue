<template>
    <section class="products">
        <h1>Minhas Trocas</h1>
        <div v-for="oferta in ofertas" :key="oferta[0].id" class="container">

            <div class="card">
                <h2>{{ oferta[0].postagemOrigem.titulo }}</h2>
                <div class="card-img">
                    <img :src="oferta[0].postagemOrigem.foto" class="card-img-produto" />
                </div>
                <div class="div-botao">
                    <button><router-link :to="{ name: 'TheProductPage', params: { id: oferta[0].postagemOrigem.id } }">Ver
                            produto</router-link></button>
                </div>
            </div>
            <div class="div-flecha">
                <img src="../assets/setaDireita.png" class="flecha-img">
                <img src="../assets/setaEsquerda.png" class="flecha-img">
            </div>

            <div class="card">
                <h2>{{ oferta[0].postagemOfertada.titulo }}</h2>
                <div class="card-img">
                    <img :src="oferta[0].postagemOfertada.foto" class="card-img-produto" />
                </div>
                <div class="div-botao">
                    <button><router-link :to="{ name: 'TheProductPage', params: { id: oferta[0].postagemOfertada.id } }">Ver
                            produto</router-link></button>
                </div>
            </div>

            <div class="div-botao">
                <button id="detalhes">Detalhes</button>
            </div>
            <!-- <div class="div-botao">
                <button id="aceitar"><router-link
                        :to="{ name: 'ThePreviewTroca', params: { id: oferta[0].id } }">Aceitar</router-link></button>
            </div> -->
        </div>
    </section>
</template>

<script>
import Oferta from '../services/OfertaService';
import Troca from '../services/TrocaService';

export default {
    data() {
        return {
            ofertas: [],
            ofertasAceitas: [],
            troca: [],
            oferta: null
        };
    },

    mounted() {
        const exibirOfertasRecebidas = Oferta.exibirOfertasRecebidas();
        const exibirOfertasFeitas = Oferta.exibirOfertasFeitas();

        Promise.all([exibirOfertasRecebidas, exibirOfertasFeitas])
            .then((respostas) => {
                const ofertasRecebidas = respostas[0].data;
                const ofertasFeitas = respostas[1].data;

                const mergedOfertas = [...ofertasRecebidas, ...ofertasFeitas];
                this.ofertas = mergedOfertas.filter((oferta) => oferta[0].status === 'ACEITA');

                console.log("Ofertas aceitas: ", this.ofertas);
            })
            .catch((e) => console.log(e.message));
    },

    methods: {
        async criarTroca(id_oferta, aceitarOferta) {
            this.oferta = { status: aceitarOferta };
            try {
                await Oferta.atualizar(id_oferta, this.oferta);
                console.log(id_oferta);
                alert('Oferta aceita!');
                this.errors = [];
                await Troca.criar(id_oferta);
                console.log(id_oferta);
                alert('Troca criada com sucesso!');
                this.$router.push({ name: 'ThePreviewTroca', params: { idOferta: id_oferta } });
                this.errors = [];
            } catch (e) {
                console.log(e.message);
            }
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
    border-radius: 6px;
}

.flecha-img {
    width: 60px;
    height: 60px;
}

.div-flecha {
    margin: 10px;
}

.div-botao {
    margin: 0;
    margin-bottom: 20px;
}

#detalhes {
    margin: 0;
}

.container {
    width: 720px;
    display: flex;
    justify-content: center;
    margin-top: 20px;
    gap: 20px;
    flex-wrap: wrap;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 30px;
    border: 1px solid #e5e9eb;
    border-radius: 4px;
    align-items: center;
}

.products {
    justify-content: center;
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
    text-align: center;
}
</style>
  