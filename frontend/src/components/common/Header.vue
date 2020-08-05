<template>
  <div id="header" :class="{ 'navbar--hidden': !showNavbar }">
    <b-navbar toggleable="lg" type="dark" variant="primary" class="p-0">
      <h1>
        <router-link style="text-decoration:none" to="/">We Travel</router-link>
      </h1>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-button variant="primary" v-b-modal.loginModal v-if="this.$store.getters.getIsAuth == ''" @click="handleClickButton" class="text-white mx-3">로그인</b-button>
          <div v-else>
            <b-button variant="primary" class="text-white mx-3"><router-link style="text-decoration:none" :to="{ name: 'profile', params: { nickname: this.$store.getters.getNickname }}">프로필</router-link></b-button>
            <b-button variant="primary" class="text-white mx-3" @click="userLogout()">로그아웃</b-button>
          </div>

          <b-button variant="primary" class="text-white mx-3"><router-link style="text-decoration:none" to="/join">회원가입</router-link></b-button>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <app-login-modal :visible.sync="visible"></app-login-modal>
  </div>
</template>   

<script>
import constants from "../../lib/constants";
import loginmodal from "../../page/user/Login.vue";

export default {
  name: "Header",
  components: {
    appLoginModal: loginmodal,
  },
  computed: {
  },
  watch: {
  },
  mounted() {
    this.lastScrollPosition = window.pageYOffset
    window.addEventListener('scroll', this.onScroll)
  },
  beforeDestroy () {
    window.removeEventListener('scroll', this.onScroll)
  },
  created() {},
  methods: {
    handleClickButton() {
      this.visible = !this.visible;
    },
    
    userLogout() {
      this.$store.commit('logout');
      this.$router.push('/').catch(()=>{});
    },
    onScroll() {
      const currentScrollPosition = window.pageYOffset || document.documentElement.scrollTop
      if (currentScrollPosition < 0) {
        return
      }
      if (Math.abs(currentScrollPosition - this.lastScrollPosition) < 60) {
        return
      }
      this.showNavbar = currentScrollPosition < this.lastScrollPosition
      this.lastScrollPosition = currentScrollPosition
    },
  },
  data: function () {
    return {
      login: false,
      visible: false,
      constants,
      keyword: "",
      showNavbar: true,
    };
  },
};
</script>

<style>
a:visited {
  color: white;
}
button.btn {
  border-color: rgba(255, 255, 255, 0);
}
#header {
  border-bottom: 0px;
  background-color: #007bff;
  transition: 0.1s all ease-out;
}

.navbar--hidden {
  transform: translate3d(0, -100%, 0);
}
</style>