import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './vuex/store'
import axios from 'axios'


// 스크롤 감지 이벤트
import AOS from 'aos'
import 'aos/dist/aos.css'


Vue.prototype.$axios = axios
Vue.config.productionType = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router, 
  store,
  created () {
    AOS.init()
  },
  beforeCreate() {
    this.$store.dispatch("checkLogin")
  },
  components: { App }, 
  template: '<App/>'
})
 