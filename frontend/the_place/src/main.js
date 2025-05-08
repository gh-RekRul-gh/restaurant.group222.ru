import Vue from 'vue'
import vuelidate from 'vuelidate'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import vuetify from './plugins/vuetify'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  axios,
  vuetify,
  vuelidate,
  render: h => h(App)
}).$mount('#app')
