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
import TheProductPage from "../components/TheProductPage";
import TheEditProduct from "../components/TheEditProduct";
import TheParceiros from "../components/TheParceiros";
import TheEditPartner from "../components/TheEditPartner";
import CadastroEnderecoView from "../views/CadastroEnderecoView"

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
    path: "/usuarioView",
    name: "usuarioView",
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
    path: "/postagem/:id",
    name: "TheProductPage",
    component: TheProductPage,
    props: true
  },
  {
    path: "/postagem/:id",
    name: "TheEditProduct",
    component: TheEditProduct,
    props: true
  },
  {
    path: "/exibirParceirosView",
    name: "ExibirParceirosView",
    component: TheParceiros
  },
  {
    path: "/parceiro/:id",
    name: "TheEditPartner",
    component: TheEditPartner,
    props: true
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
