import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './vuex/store'
import axios from 'axios'

Vue.prototype.$axios = axios
Vue.config.productionType = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router, 
  store,
  beforeCreate() {
    this.$store.dispatch("checkLogin")
  },
  components: { App }, 
  template: '<App/>'
})
 