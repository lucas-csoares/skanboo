import { createRouter, createWebHistory } from "vue-router";
import CadastroView from "../views/CadastroView";
import LoginView from "../views/LoginView";
import ProductsView from "../views/ProductsView";

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
  // {
  //   path: '/about',
  //   name: 'about',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
