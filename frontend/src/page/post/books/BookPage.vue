<template>
  <div id="main" class="main container">
    <div class="book-header row">
      <div class="col-4 book-header-img">
        <img v-if="book_info.img != null" class="header-card-img" :src="'data:image/jpg;base64,' + book_info.img">
        <img v-else class="article-img" src="@/assets/img/logo_wetravel.png">
      </div>
      <div class="col-1">
      </div>
      <div class="col-7 book-header-content">
        <div class="row">
          <div class="col-4 p-0">
            <h3>{{ book_info.title }}</h3>
          </div>
          <div class="col-8 p-0" style="text-align:right" v-show="isAuthor">
            <b-button class="mx-1" variant="primary"><router-link :to="{ name: 'bookmodify', params: {bookno: this.$route.params.bookno}}">수정</router-link></b-button>
            <b-button class="ml-1" variant="danger" @click="deleteaction">삭제</b-button>
          </div>
        </div>
        <div class="book-content-inf">
          <p>by {{ book_info.writer }}</p>
          <star-rating :increment="1"
             :max-rating="5"
             :rating="4"
             :show-rating="false"
             inactive-color="#fff"
             active-color="#007bff"
             read-only
             :star-size="25">
          </star-rating>
          <b-button variant="primary" v-show="isAuthor"><b-link style="text-decoration:none;" :to="{name:'articlecreate', params:{ bookno: this.$route.params.bookno }}">글 작성</b-link></b-button>
        </div>
        <div class="book-content">
          <p>{{ book_info.description }}</p>
        </div>
      </div>
    </div>
    <hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
    <div class="book-article">
      <div v-for="(article, index) in articles" :key="index" :ref="`article_${index}`" class="book-article-content" @click="$router.push({name: 'articlepage', params: {province: $route.params.province, city: $route.params.city, bookno: $route.params.bookno, articleno: article.articleno}})">
        <div class="col-2 p-0">
          <img v-if="article.img != null" class="article-img" :src="'data:image/jpg;base64,' + article.img">
          <img v-else class="article-img" src="@/assets/img/logo_wetravel.png">
        </div>
        <div class="col-5">
          <h5>{{ article.day }}화: {{ article.title }}</h5>
        </div>
        <div class="col-3" style="display: flex;">
          <h5>평점</h5>
          <star-rating :increment="0.01"
             :max-rating="5"
             :rating="article.score"
             :show-rating="false"
             inactive-color="#fff"
             active-color="#007bff"
             read-only
             :star-size="20">
          </star-rating>
          <h5>{{ article.score }}</h5>
        </div>
        <div class="col-2">
          <h5>{{ article.writedate }}</h5>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

var articles = []
var book_info = {}
export default {
  name: "BookPage",
  data: function() {
    return {
      book_info: book_info,
      articles: articles,
      isAuthor: false,
    } 
  },
  watch: {
    'this.$route.params.bookno': function(bookno) {
      this.getArticleList()
      this.getBookInfo()
    }
  },
  mounted() {
    this.getBookInfo()
    this.getArticleList()
    this.authorCheck()
  },
  methods: {
    async getBookInfo() {
      await this.$store.dispatch('getBookInfo', this.$route.params.bookno).then(res => {
        this.book_info = res;
      })
    },
    getArticleList: function() {
      let today = new Date();
      articles = []
      this.$axios.get(`/api/article/all/${this.$route.params.bookno}`, {
        headers: {'Content-Type': 'application/json'}
      }).then(response => {
        for (var i = 0; i < response.data.length; i++) {
          articles.push({articleno: response.data[i].articleno, title: response.data[i].title, day: response.data[i].day, writedate: response.data[i].writedate, 
                          score: response.data[i].score, img: response.data[i].img})
        }
      })
      this.articles = articles
    },
    deleteaction: function() {
      let res = confirm("정말로 삭제하시겠습니까?")
      if (res == true) {
        this.$axios.
          delete(`/api/book/${this.$route.params.bookno}`, {
          articleno : this.$route.params.bookno
          }, {headers : {'Content-Type': 'application/json'}})
          .then(({ data }) => {
            let msg = '삭제 처리시 문제가 발생했습니다.';
            if (data === 'success') {
              msg = '삭제가 완료되었습니다.';
            }
            alert(msg);
            this.$router.push({name: 'citypage', params: {'province': this.$route.params.province, 'city': this.$route.params.city}})
          });
      }
    },
    goToArticlePage: function() {
      this.$router.push({name: 'articlepage', params: {province: this.$route.params.province, city: this.$route.params.city, bookno: this.$route.params.bookno, articleno: article.articleno}})
    },
    authorCheck: async function() {
      if (sessionStorage.getItem('jwt-auth-token')) {
        await this.$store.dispatch('checkLogin').then(res => {
          if (res.nickname == this.book_info.writer)
            this.isAuthor = true
          else
            this.isAuthor = false
        })
      }
    }
  }
}
</script>

<style>
  .book-header {
    margin-top: 90px;
    margin-bottom: 30px;
  }
  .header-card-img {
    max-width: 100%;
    max-height: 100%;
  }
  .book-header-content{
    text-align: left;
  }

  .book-content-inf p {
    font-size : 18px;
    color: #BAB096;
    margin: 0;
  }
  .book-content-inf {
    display: flex;
    justify-content: space-between;
  }
  .book-content-inf .vue-star-rating{
    margin-left: 20px;
    padding-bottom: 10px;
  }
  .book-article-content {
    margin-bottom: 20px;
    box-shadow: 0px 2px 4px 0px rgba(0,0,0,0.5);
    display: flex;
    align-items: center;
    text-align: left;
  }
  .article-img {
    max-width : 100%;
    max-height: 100%;
  }
  .book-article h5 {
    margin: 0px;
    margin-right: 10px;
  }
</style>