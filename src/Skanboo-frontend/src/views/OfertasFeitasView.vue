<template>
  <section class="products">
    <h1>Ofertas feitas</h1>

    <div class="div-filtros">
      <h2>Filtros aplicados:</h2>
      <button
        class="btn-filtro"
        :class="{ 'btn-filtro-ativo': filtrarRecusada }"
        @click="filtrarOfertas('RECUSADA')"
      >
        Recusada
      </button>
      <button
        class="btn-filtro"
        :class="{ 'btn-filtro-ativo': filtrarEmAndamento }"
        @click="filtrarOfertas('EM_ANDAMENTO')"
      >
        Em andamento
      </button>
    </div>

    <!-- -------------------------------------------------- -->
    <!-- Postagem do usuario ativo -->
    <!-- -------------------------------------------------- -->
    <div class="grid">
      <div v-for="oferta in ofertas" :key="oferta[0].id" class="grid-card">
        <h2 class="status-oferta">
          Status: {{ oferta[0].status.toLowerCase() }}
        </h2>
        <div class="card postagem-ofertada">
          <h2 class="titulo-postagem">
            {{ oferta[0].postagemOfertada.titulo }}
          </h2>

          <div class="card-img">
            <img
              :src="oferta[0].postagemOfertada.foto"
              class="card-img-produto"
            />
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
        <div class="card postagem-origem">
          <h2 class="titulo-postagem">{{ oferta[0].postagemOrigem.titulo }}</h2>

          <div class="card-img">
            <img
              :src="oferta[0].postagemOrigem.foto"
              class="card-img-produto"
            />
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
          <button @click="cancelarOferta(oferta[0].id)" class="cancelar">
            Cancelar oferta
          </button>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Oferta from "../services/OfertaService";

export default {
  data() {
    return {
      ofertas: [],
      oferta: {
        id: "",
        status: "",
      },
      filtrarRecusada: false,
      filtrarEmAndamento: false,
    };
  },

  mounted() {
    this.carregarOfertas();
  },

  methods: {
    carregarOfertas() {
      Oferta.exibirOfertasFeitas()
        .then((resposta) => {
          this.ofertas = resposta.data.filter((oferta) => {
            if (this.filtrarRecusada && oferta[0].status === "RECUSADA") {
              return false;
            }
            if (
              this.filtrarEmAndamento &&
              oferta[0].status === "EM_ANDAMENTO"
            ) {
              return false;
            }
            return true;
          });
        })
        .catch((e) => console.log(e.message));
    },

    filtrarOfertas(filtro) {
      if (filtro === "RECUSADA") {
        this.filtrarRecusada = !this.filtrarRecusada;
      } else if (filtro === "EM_ANDAMENTO") {
        this.filtrarEmAndamento = !this.filtrarEmAndamento;
      }

      this.carregarOfertas();
    },

    criarOferta() {
      const idOfertada = sessionStorage.getItem("idOfertada");
      const idOrigem = sessionStorage.getItem("idOrigem");

      Oferta.criar(idOfertada, idOrigem)
        .then(() => {
          alert("Oferta realizada com sucesso!");
          this.errors = [];
        })
        .catch((e) => console.log(e.message));
    },

    cancelarOferta(id) {
      this.oferta.status = "RECUSADA";

      Oferta.atualizar(id, this.oferta)
        .then(() => {
          alert("Oferta recusada com sucesso!");
          this.carregarOfertas();
        })
        .catch((e) => console.log(e));
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

.card img {
  width: 150px;
  height: 150px;
  object-fit: cover;
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
  border: 0px solid #e5e9eb;
  border-radius: 4px;
  transition: all 300ms;
}

.card:hover {
  transform: scale(1.02);
}

.card img {
  width: 150px;
  height: 150px;
  border-radius: 100%;
}

.status-oferta {
  grid-column: 1 / span 3;
  grid-row: 1;
}

.titulo-postagem {
  font-weight: bold;
  text-align: center;
  padding: 5px;
  font-size: 1.2em;
}

.postagem-ofertada {
  grid-column: 1/2;
  grid-row: 2;
  margin-left: 10px;
}

.postagem-origem {
  grid-column: 3/4;
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
  grid-column: 1 / span 3;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 30px;
  margin: 0 10px;
}

.grid button,
.btn-filtro {
  cursor: pointer;
  background: #fcfcfc;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-weight: bold;
  width: 130px;
  transition: 0.3s;
}

.grid button:hover,
.btn-filtro-ativo {
  background: #f9dc5c;
  border: 1px solid #f9dc5c;
}

.div-filtros {
  display: flex;
  justify-content: center;
  height: 80px;
}

.div-filtros h2 {
  line-height: 80px;
}

button {
  border-radius: 60px !important;
}
</style>
