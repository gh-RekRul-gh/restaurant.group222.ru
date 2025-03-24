import Vue from 'vue'
import VueRouter from 'vue-router'
// import HomeView from '../views/HomeView.vue'
import Main from '../views/Main.vue'
// import App from '../App.vue'
import Profile from '../views/Profile.vue'
import ShoppingCart from '../views/ShoppingCart.vue'
import OrderFormation from '../views/OrderFormation.vue'
import PlacedOrder from '../views/PlacedOrder.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Main
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile
  },
  {
    path: '/cart',
    name: 'cart',
    component: ShoppingCart,
  },
  {
    path: '/formation',
    name: 'formation',
    component: OrderFormation
    },
    {
    path: '/placed',
    name: 'placed',
    component: PlacedOrder
  },

  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
