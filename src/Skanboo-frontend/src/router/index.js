import { createRouter, createWebHistory } from "vue-router";
import CadastroView from "../views/CadastroView";
import LoginView from "../views/LoginView";
import ProductsView from "../views/ProductsView";
import UsuarioView from "../views/UsuarioView";
import EditarUsuarioView from "../views/EditarUsuarioView";
import PostarProdutoView from "../views/PostarProdutoView";
import EditarProdutoView from "../views/EditarProdutoView";
import PaginaProdutoView from "../views/PaginaProdutoView";
import PostsDoUsuarioView from "../views/PostsDoUsuarioView";
import PaginaAdmView from "../views/PaginaAdmView";
import CooptarParceirosView from "../views/CooptarParceirosView";
import PaginaProdutoUsuarioView from "../views/PaginaProdutoUsuarioView";
import EditarEnderecoView from "../views/EditarEnderecoView";
import PreviewTrocaView from "../views/PreviewTrocaView";
import ThePreviewTroca from "../components/ThePreviewTroca";
import TheProductPage from "../components/TheProductPage";
import TheEditProduct from "../components/TheEditProduct";
import TheParceiros from "../components/TheParceiros";
import TheEditPartner from "../components/TheEditPartner";
import TheOfertaPreview from "../components/TheOfertaPreview";
import ProdutosOferecidosView from "../views/ProdutosOferecidosView";
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
    component: ProductsView,
  },
  {
    path: "/UsuarioView",
    name: "UsuarioView",
    component: UsuarioView,
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
    path: "/postsDoUsuarioView",
    name: "PostsDoUsuarioView",
    component: PostsDoUsuarioView,
  },
  {
    path: "/paginaAdmView",
    name: "PaginsAdmView",
    component: PaginaAdmView,
  },
  {
    path: "/cooptarParceirosView",
    name: "CooptarParceirosView",
    component: CooptarParceirosView,
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
    path: "/ProdutosOferecidosView",
    name: "ProdutosOferecidosView",
    component: ProdutosOferecidosView,
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
    path: "/exibirParceirosView",
    name: "ExibirParceirosView",
    component: TheParceiros,
  },
  {
    path: "/parceiro/:id",
    name: "TheEditPartner",
    component: TheEditPartner,
    props: true,
  },
  {
    path: "/oferta/:idOferta",
    name: "ThePreviewTroca",
    component: ThePreviewTroca,
    props: true,
  },
  {
    path: "/paginaAvaliacaoView",
    name: "paginaAvaliacaoView",
    component: PaginaAvaliacaoView,
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
