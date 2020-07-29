<template>
  <div id="header" v-if="isHeader">
    <b-navbar toggleable="lg" type="dark" variant="primary" class="p-0">
      <h1>
        <router-link to="/">We Travel</router-link>
      </h1>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-button v-b-modal.loginModal v-if="this.$store.getters.getEmail == ''" @click="handleClickButton" class="text-white mx-3">로그인</b-button>
          <div v-else>
            <b-button class="text-white mx-3"><router-link to="/profile/${this.$store.getters.email}">프로필</router-link></b-button>
            <b-button class="text-white mx-3" @click="userLogout()">로그아웃</b-button>
          </div>

          <b-button class="text-white mx-3"><router-link to="/join">회원가입</router-link></b-button>
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
  props: ["isHeader"],
  computed: {
  },
  watch: {
  },
  created() {},
  methods: {
    handleClickButton() {
      this.visible = !this.visible;
    },
    userLogout() {
      this.$store.commit('logout');
    }
  },
  data: function () {
    return {
      login: false,
      visible: false,
      constants,
      keyword: "",
    };
  },
};
</script>

<style>
a:visited {
  color: white;
}
button.btn {
  background-color: rgba(255, 255, 255, 0);
  border-color: rgba(255, 255, 255, 0);
}
#header {
  border-bottom: 0px;
  background-color: #007bff;
}
</style>