import { createRouter, createWebHistory } from "vue-router";
import CadastroView from "../views/CadastroView";
import LoginView from "../views/LoginView";
import HomeView from "../views/HomeView";
import PerfilUsuarioView from "../views/PerfilUsuarioView";
import EditarUsuarioView from "../views/EditarUsuarioView";
import PostarProdutoView from "../views/PostarProdutoView";
import EditarProdutoView from "../views/EditarProdutoView";
import PaginaProdutoView from "../views/PaginaProdutoView";
import PostagemUsuarioView from "../views/PostagemUsuarioView";
import PaginaAdmView from "../views/PaginaAdmView";
import ParceirosCadastradosView from "../views/ParceirosCadastradosView";
import CadastroParceiroComponent from "../components/CadastroParceiroComponent"
import PaginaProdutoUsuarioView from "../views/PaginaProdutoUsuarioView";
import EditarEnderecoView from "../views/EditarEnderecoView";
import PreviewTrocaView from "../views/PreviewTrocaView";
import PaginaTrocasView from "../views/PaginaTrocasView";
import ThePreviewTroca from "../components/ThePreviewTroca";
import TheProductPage from "../components/TheProductPage";
import TheEditProduct from "../components/TheEditProduct";
import ParceirosCadastradosComponent from "../components/ParceirosCadastradosComponent";
import TheEditPartner from "../components/TheEditPartner";
import TheOfertaPreview from "../components/TheOfertaPreview";
import OfertasFeitasView from "../views/OfertasFeitasView";
import ThePaginaTrocas from "../components/ThePaginaTrocas";
import OfertasRecebidasView from "../views/OfertasRecebidasView";
import CadastroEnderecoView from "../views/CadastroEnderecoView";
import OfertaView from "../views/OfertaView";
import EscolherProdutoView from "../views/EscolherProdutoView";
import PaginaAvaliacaoView from "../views/PaginaAvaliacaoView";
import IndicadoresView from "../views/IndicadoresView";

const routes = [
  {
    path: "/cadastroView",
    name: "cadastroView",
    component: CadastroView,
  },
  {
    path: "/loginView",
    name: "loginView",
    component: LoginView,
  },
  {
    path: "/",
    name: "products",
    component: HomeView,
  },
  {
    path: "/PerfilUsuarioView",
    name: "PerfilUsuarioView",
    component: PerfilUsuarioView,
  },
  {
    path: "/editarUsuarioView",
    name: "editarUsuarioView",
    component: EditarUsuarioView,
  },
  {
    path: "/postarProdutoView",
    name: "postarProdutoView",
    component: PostarProdutoView,
  },
  {
    path: "/editarProdutoView",
    name: "editarProdutoView",
    component: EditarProdutoView,
  },
  {
    path: "/paginaProdutoView",
    name: "paginaProdutoView",
    component: PaginaProdutoView,
  },
  {
    path: "/PostagemUsuarioView",
    name: "PostagemUsuarioView",
    component: PostagemUsuarioView,
  },
  {
    path: "/paginaAdmView",
    name: "PaginsAdmView",
    component: PaginaAdmView,
  },
  {
    path: "/ParceirosCadastradosView",
    name: "ParceirosCadastradosView",
    component: ParceirosCadastradosView,
  },
  {
    path: "/cadastroparceirocomponent",
    name: "CadastroParceiroComponent",
    component: CadastroParceiroComponent,
  },
  {
    path: "/paginaProdutoUsuarioView",
    name: "PaginaProdutoUsuarioView",
    component: PaginaProdutoUsuarioView,
  },
  {
    path: "/CadastroEnderecoView",
    name: "CadastroEnderecoView",
    component: CadastroEnderecoView,
  },
  {
    path: "/EditarEnderecoView",
    name: "EditarEnderecoView",
    component: EditarEnderecoView,
  },
  {
    path: "/PreviewTrocaView",
    name: "PreviewTrocaView",
    component: PreviewTrocaView,
    props: true,
  },
  {
    path: "/TheOfertaPreview",
    name: "TheOfertaPreview",
    component: TheOfertaPreview,
  },
  {
    path: "/OfertasFeitasView",
    name: "OfertasFeitasView",
    component: OfertasFeitasView,
  },
  {
    path: "/OfertasRecebidasView",
    name: "OfertasRecebidasView",
    component: OfertasRecebidasView,
  },
  {
    path: "/OfertaView",
    name: "OfertaView",
    component: OfertaView,
  },
  {
    path: "/EscolherProdutoView",
    name: "EscolherProdutoView",
    component: EscolherProdutoView,
    props: true,
  },
  {
    path: "/PaginaTrocasView",
    name: "PaginaTrocasView",
    component: PaginaTrocasView,
    props: true,
  },
  {
    path: "/postagem/:id",
    name: "TheProductPage",
    component: TheProductPage,
    props: true,
  },
  {
    path: "/postagem/:id",
    name: "TheEditProduct",
    component: TheEditProduct,
    props: true,
  },
  {
    path: "/ParceirosCadastradosComponent",
    name: "ParceirosCadastradosComponent",
    component: ParceirosCadastradosComponent,
  },
  {
    path: "/parceiro/:id",
    name: "TheEditPartner",
    component: TheEditPartner,
    props: true,
  },
  {
    path: "/oferta/:idTroca",
    name: "ThePreviewTroca",
    component: ThePreviewTroca,
    props: true,
  },
  {
    path: "/oferta/:idTroca",
    name: "ThePaginaTrocas",
    component: ThePaginaTrocas,
    props: true,
  },
  {
    path: "/troca/:idTroca",
    name: "paginaAvaliacaoView",
    component: PaginaAvaliacaoView,
    props: true,
  },
  {
    path: "/indicadoresView",
    name: "IndicadoresView",
    component: IndicadoresView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
