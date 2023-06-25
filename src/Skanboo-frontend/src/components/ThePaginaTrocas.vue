<template>
  <section class="products">
    <h1>Minhas trocas</h1>

    <div class="div-filtros">
      <div class="dropdown-filtros">
        <span>Filtrar ({{ quantidadeTrocas }})</span>
        <div class="dropdown-conteudo">
          <p class="btn-filtro" :class="{ 'btn-filtro-ativo': filtrarRecusada }" @click="filtrasTrocas('RECUSADA')">
            Recusada
          </p>
          <p
            class="btn-filtro"
            :class="{ 'btn-filtro-ativo': filtrarEmAndamento }"
            @click="filtrasTrocas('EM_ANDAMENTO')"
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
      <div v-for="troca in trocas" :key="troca.id" class="grid-card">
        <h3 class="status-oferta">Status: {{ troca.status.toLowerCase().replace('_', ' ') }}</h3>
        <div class="card postagem-origem">
          <h2 class="titulo-postagem">{{ formatarTitulo(troca.oferta.postagemOrigem.titulo) }}</h2>

          <div class="card-img">
            <img :src="troca.oferta.postagemOrigem.foto" class="card-img-produto" />
          </div>

          <div>
            <button>
              <router-link
                :to="{
                  name: 'TheProductPage',
                  params: { id: troca.oferta.postagemOrigem.id },
                }"
                >Ver produto</router-link
              >
            </button>
          </div>
        </div>

        <div class="grid-seta">
          <img src="../assets/setaDireita.png" class="flecha-img" />
          <img src="../assets/setaEsquerda.png" class="flecha-img" />
        </div>

        <!-- -------------------------------------------------- -->
        <!-- Postagem do outro usuario -->
        <!-- -------------------------------------------------- -->
        <div class="card postagem-ofertada">
          <h2 class="titulo-postagem">
            {{ formatarTitulo(troca.oferta.postagemOfertada.titulo) }}
          </h2>

          <div class="card-img">
            <img :src="troca.oferta.postagemOfertada.foto" class="card-img-produto" />
          </div>

          <div>
            <button>
              <router-link
                :to="{
                  name: 'TheProductPage',
                  params: { id: troca.oferta.postagemOfertada.id },
                }"
                >Ver produto</router-link
              >
            </button>
          </div>
        </div>

        <div class="grid-botao">
          <button class="btn-detalhes" @click="paginaDetalhes(troca.id)">Ver detalhes</button>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Troca from '../services/TrocaService';

export default {
  data() {
    return {
      ofertas: [],
      ofertasAceitas: [],
      trocas: [],
      oferta: null,
      quantidadeTrocas: 0,
      filtrarRecusada: false,
      filtrarEmAndamento: false,
    };
  },

  mounted() {
    this.carregarTrocas();
  },

  methods: {
    carregarTrocas() {
      Troca.exibirTrocas()
        .then((resposta) => {
          this.trocas = resposta.data.filter((troca) => {
            if (this.filtrarRecusada && troca.status === 'RECUSADA') {
              return false;
            }
            if (this.filtrarEmAndamento && troca.status === 'EM_ANDAMENTO') {
              return false;
            }
            if (troca.status === 'FINALIZADA') {
              return false;
            }
            return true;
          });
          this.quantidadeTrocas = this.trocas.length;
        })
        .catch((e) => {
          let grid = document.querySelector('.grid');
          grid.innerHTML = 'O usuário não fez nenhuma oferta ainda!';
          console.log(e.message);
        });
    },

    paginaDetalhes(id_troca) {
      this.$router.push({
        name: 'ThePreviewTroca',
        params: { idTroca: id_troca },
      });
    },

    filtrasTrocas(filtro) {
      if (filtro === 'RECUSADA') {
        this.filtrarRecusada = !this.filtrarRecusada;
      } else if (filtro === 'EM_ANDAMENTO') {
        this.filtrarEmAndamento = !this.filtrarEmAndamento;
      }

      this.carregarTrocas();
    },

    formatarTitulo(titulo) {
      const maxLength = 22;
      if (titulo.length > maxLength) {
        return titulo.substring(0, maxLength - 3) + '(...)';
      }
      return titulo;
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
  width: 600px;
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
  grid-column: 1 / span 3;
  grid-row: 4;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 30px;
  margin: 0 10px;
}

.grid-botao .btn-detalhes {
  background: #ffffff;
  border: 1px solid #e5e9eb;
  transition: all 300ms;
}

.grid-botao .btn-detalhes:hover {
  background: #f9dc5c;
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
