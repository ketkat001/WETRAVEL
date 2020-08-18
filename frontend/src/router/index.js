import Vue from 'vue'
import Router from 'vue-router'
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'
import VeeValidate, { Validator } from 'vee-validate'
import ko from 'vee-validate/dist/locale/ko.js'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import store from '../vuex/store'


// fontawesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { faFacebook, faTwitter, faInstagram, faPinterest } from '@fortawesome/free-brands-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

// vue-star-rating
import StarRating from 'vue-star-rating'


// vue-quill-editor
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'



// 유저
import join from '../page/user/Join.vue'
import forgotpassword from '../page/user/ForgotPassword.vue'
import profile from '../page/user/Profile.vue'
import userdelete from '../page/user/UserDelete.vue'
import userupdate from '../page/user/UserUpdate.vue'

// 포스트
import mainpage from '../page/post/MainPage.vue'
import provincepage from '../page/post/ProvincePage.vue'
import citypage from '../page/post/CityPage.vue'
import imagetest from '../page/post/ImageTest.vue'
import aws from '../page/post/Aws.vue'

// Book
import bookpage from '../page/post/books/BookPage.vue'
import bookcreate from '../page/post/books/BookCreate.vue'

// Article
import articlecreate from '../page/post/articles/ArticleCreate.vue'
import articlepage from '../page/post/articles/ArticlePage.vue'
import articlemodify from '../page/post/articles/ArticleModify.vue'



library.add(fas)
library.add(faFacebook)
library.add(faInstagram)
library.add(faTwitter)
library.add(faPinterest)


Vue.use(Router) 
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(VueQuillEditor)

Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.component('star-rating', StarRating)


// Vee-Validate 사용

const config = {
  locale: 'ko',
  dictionary: {
    ko
  }
}

Vue.use(VeeValidate, config)


VeeValidate.Validator.extend('verify_password', {
  getMessage: field => `비밀번호는 최소 하나의 대문자와 소문자, 숫자를 포함하고 있어야 합니다`,
        validate: value => {
            var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])");
            return strongRegex.test(value);
          }
})


const requireAuth = () => (to, from, next) => {
  if (sessionStorage.getItem('jwt-auth-token')) {
    return next();
  }
  alert('로그인 후 이용 가능합니다')
  next('/');
}


export default new Router({
  mode: 'history',
  routes: [
    // 포스트
    { 
      path: '/',
      name: 'mainpage',
      component: mainpage,
    },

    {
      path:'/posts/:province',
      name: 'provincepage',
      component: provincepage,
    },

    {
      path: '/posts/:province/:city', 
      name: 'citypage',
      component: citypage,
    },
    // BookPage
    {
      path:'/bookcreate',
      name: 'bookcreate',
      component: bookcreate,
      //beforeEnter: requireAuth()
    },

    {
      path: '/posts/:province/:city/bookno/:bookno',
      name: 'bookpage',
      component: bookpage,
    },

    // Article Page
    {
      path:'/bookno/:bookno/articlecreate',
      name: 'articlecreate',
      component: articlecreate,
    },
    
    {
      path: '/posts/:province/:city/bookno/:bookno/articleno/:articleno',
      name: 'articlepage',
      component: articlepage,
    },

    {
      path: '/posts/:province/:city/bookno/:bookno/articleno/:articleno/articlemodify',
      name: 'articlemodify',
      component: articlemodify,
    },

    // 가입
    {
      path: '/join',
      name: 'join',
      component: join,
    },
    {
      path: '/forgotpassword',
      name: 'forgotpassword',
      component: forgotpassword,
    },
    {
      path: '/profile',
      name: 'profile',
      component: profile,
      //beforeEnter: requireAuth()
    },
    {
      path: '/userupdate',
      name: 'userupdate',
      component: userupdate,
      //beforeEnter: requireAuth()
    },
    {
      path: '/userdelete',
      name: 'userdelete',
      component: userdelete,
      // beforeEnter: requireAuth()
    },
    {
      path: '/aws',
      name: 'aws',
      component: aws,
      //beforeEnter: requireAuth()
    },
    {
      path: '/posts/:province/:city/bookno/:bookno/articleno/:articleno/articlemodify',
      name: 'articlemodify',
      component: articlemodify,
    },
    {
      path: '/imagetest',
      name: 'imagetest',
      component: imagetest
    }
  ],

  // 페이지 이동 시 저장된 위치가 있다면 저장된 위치로 아니면 최상단으로
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      
      return { x:0, y:0 }
    }
  }
})
