<template>
  <section class="products">
    <h1>Ofertas feitas</h1>

    <div class="div-filtros">
      <div class="dropdown-filtros">
        <span>Filtrar ({{quantidadeOfertas}})</span>
        <div class="dropdown-conteudo">
          <p class="btn-filtro" :class="{ 'btn-filtro-ativo': filtrarRecusada }" @click="filtrarOfertas('RECUSADA')">
            Recusada
          </p>
          <p
            class="btn-filtro"
            :class="{ 'btn-filtro-ativo': filtrarEmAndamento }"
            @click="filtrarOfertas('EM_ANDAMENTO')"
          >
            Em andamento
          </p>
        </div>
      </div>
    </div>

    <!-- -------------------------------------------------- -->
    <!-- Postagem do usuario ativo -->
    <!-- -------------------------------------------------- -->
    <div class="grid">
      <div v-for="oferta in ofertas" :key="oferta[0].id" class="grid-card">
        <h3 class="status-oferta">Status: {{ oferta[0].status.toLowerCase().replace('_', ' ') }}</h3>
        <div class="card postagem-origem">
          <h2 class="titulo-postagem">{{ oferta[0].postagemOfertada.titulo }}</h2>

          <div class="card-img">
            <img :src="oferta[0].postagemOfertada.foto" class="card-img-produto" />
          </div>

          <div>
            <button>
              <router-link
                :to="{
                  name: 'TheProductPage',
                  params: { id: oferta[0].postagemOfertada.id },
                }"
                >Ver produto</router-link
              >
            </button>
          </div>
        </div>

        <div class="grid-seta">
          <img src="../assets/setaDireita.png" class="flecha-img" />
        </div>

        <!-- -------------------------------------------------- -->
        <!-- Postagem do outro usuario -->
        <!-- -------------------------------------------------- -->
        <div class="card postagem-ofertada">
          <h2 class="titulo-postagem">
            {{ oferta[0].postagemOrigem.titulo }}
          </h2>

          <div class="card-img">
            <img :src="oferta[0].postagemOrigem.foto" class="card-img-produto" />
          </div>

          <div>
            <button>
              <router-link
                :to="{
                  name: 'TheProductPage',
                  params: { id: oferta[0].postagemOrigem.id },
                }"
                >Ver produto</router-link
              >
            </button>
          </div>
        </div>

        <div class="grid-botao">
          <button @click="cancelarOferta(oferta[0].id)">Cancelar oferta</button>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Oferta from '../services/OfertaService';

export default {
  data() {
    return {
      ofertas: [],
      oferta: {
        id: '',
        status: '',
      },
      filtrarRecusada: false,
      filtrarEmAndamento: false,
      quantidadeOfertas: 0,
    };
  },

  mounted() {
    this.carregarOfertas();
  },

  methods: {
    carregarOfertas() {
      Oferta.exibirOfertasFeitas()
        .then((resposta) => {
          this.ofertas = resposta.data
            .flatMap((objetoArray) => {
              return objetoArray.map((objeto) => [objeto]);
            })
            .filter((oferta) => {
              if (this.filtrarRecusada && oferta[0].status === 'RECUSADA') {
                return false;
              }
              if (this.filtrarEmAndamento && oferta[0].status === 'EM_ANDAMENTO') {
                return false;
              }
              if (oferta[0].status === 'ACEITA') {
                return false;
              }
              return true;
            });
          this.quantidadeOfertas = this.ofertas.length;
        })
        .catch((e) => {
          let grid = document.querySelector('.grid');
          grid.innerHTML = 'O usuário não fez nenhuma oferta ainda!';
          console.log(e.message);
        });
    },

    cancelarOferta(id) {
      this.oferta.status = 'RECUSADA';

      Oferta.atualizar(id, this.oferta)
        .then(() => {
          alert('Oferta recusada com sucesso!');
          this.carregarOfertas();
        })
        .catch((e) => console.log(e));
    },

    filtrarOfertas(filtro) {
      if (filtro === 'RECUSADA') {
        this.filtrarRecusada = !this.filtrarRecusada;
      } else if (filtro === 'EM_ANDAMENTO') {
        this.filtrarEmAndamento = !this.filtrarEmAndamento;
      }

      this.carregarOfertas();
    },
  },
};
</script>

<style scoped>
* {
  box-sizing: border-box;
  text-decoration: none;
}

a:visited {
  text-decoration: none;
  color: #515864;
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

h1 {
  display: inline-block;
  font-size: 1.5em;
  margin-bottom: 20px;
}

h2 {
  font-size: 14px;
  color: #252c32;
  kerning: -0.6%;
  font-weight: 400;
  text-align: left;
}

.grid {
  width: 100%;
  display: grid;
  grid-template-rows: repeat(2, 1fr);
  gap: 10px;
  padding: 15px;
}

.grid-card {
  width: calc(100vw / 2.3);
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  align-items: center;
  gap: 10px;
  padding: 15px;
  margin: 0 auto;
  border: 1px solid #e5e9eb;
  border-radius: 4px;
}

.card {
  width: 200px;
  height: 250px;
  background: #ffffff;
  border: 1px solid #e5e9eb;
  border-radius: 4px;
  transition: opacity 300ms;
}

.card button {
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
}

.card button:hover {
  background: #ffe677;
  border: 1px solid #f9dc5c;
  color: white;
}

.card h2 {
  padding: 8px;
  font-size: 15px;
  color: #252c32;
}

.card:hover .card-img img {
  opacity: 1;
}

.card img {
  width: 180px;
  height: 150px;
  border-radius: 4px;
  opacity: 0.8;
  transition: 300ms;
}

.status-oferta {
  grid-column: 1 / span 1;
  grid-row: 3;
}

.titulo-postagem {
  font-weight: bold;
  text-align: center;
  padding: 5px;
  font-size: 1.2em;
}

.postagem-ofertada {
  grid-column: 3/4;
  grid-row: 2;
  margin-left: 10px;
}

.postagem-origem {
  grid-column: 1/2;
  grid-row: 2;
  margin-right: 10px;
}

.grid-seta {
  grid-column: 2/3;
  grid-row: 2;
  align-content: center;
}

.grid-seta img {
  width: 100px;
}

.grid-botao {
  grid-column: 2 / span 3;
  grid-row: 3;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 30px;
  margin: 0 10px;
}

.grid-botao .btn-recusar {
  background: #e28c89;
  border: 1px solid #e5e9eb;
  transition: all 300ms;
}

.grid-botao .btn-recusar:hover {
  background: #da5752;
}

.grid-botao .btn-aceitar {
  background: #a9ddb8;
  border: 1px solid #e5e9eb;
  transition: all 300ms;
}

.grid-botao .btn-aceitar:hover {
  background: #7ed697;
}

.grid button,
.btn-filtro {
  cursor: pointer;
  border-radius: 4px;
  font-weight: bold;
  width: 130px;
  color: #252c32;
  background: #f9dc5c;
}

.btn-filtro-ativo {
  background: #f9f9f9;
  border: 1px solid #f9f9f9;
}

.div-filtros {
  display: flex;
  justify-content: center;
  position: relative;
  left: 240px;
}

.div-filtros span {
  font-weight: bold;
}

.div-filtros h2 {
  line-height: 80px;
}

.dropdown-filtros {
  position: relative;
  display: inline-block;
  padding: 5px;
  width: 90px;
  border: 1px solid #e5e9eb;
  border-radius: 4px;
}

.dropdown-filtros:hover {
  background: #e5e9eb;
}

.dropdown-conteudo {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  padding: 12px 16px;
  z-index: 1;
}

.dropdown-conteudo p {
  margin-top: 10px;
}

.dropdown-filtros:hover .dropdown-conteudo {
  display: block;
}

button {
  border-radius: 16px !important;
}
</style>
