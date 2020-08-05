<template>
  <div id="main" class="main container">
    <div class="book-header row">
      <div class="col-4 book-header-img">
        <img class="header-card-img" src="@/assets/img/13.jpg">
      </div>
      <div class="col-1">
      </div>
      <div class="col-7 book-header-content">
        <h3>서울의 밤은 밝다</h3>
        <div class="book-content-inf">
          <p>by 서울 야경</p>
          <star-rating :increment="1"
             :max-rating="5"
             :rating="4"
             :show-rating="false"
             inactive-color="#fff"
             active-color="#007bff"
             read-only
             :star-size="25">
          </star-rating>
        </div>
        <div class="book-content">
          <p>넉넉히 여행시간이 있다면 구석구석 둘러 보자 서울! 며칠을 할애해도 모자른 넓은 서울, 천천히 둘러보자.</p>
        </div>
      </div>
    </div>
    <hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
    <div class="book-article">
      <div v-for="(article, index) in articles" :key="index" :ref="`article_${index}`" class="book-article-content">
        <div class="col-2 p-0">
          <img class="article-img" :src="article.img">
        </div>
        <div class="col-5">
          <h5>{{ article.day }}일차: {{ article.title }}</h5>
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
    } 
  },
  watch: {
    'this.$route.params.bookno': function(bookno) {
      this.getArticleList()
    }
  },
  mounted() {
    this.getArticleList()
  },
  methods: {
    getArticleList: function() {
      let today = new Date();
      articles = []
      this.$axios.get(`/api/article/all/${this.$route.params.bookno}`, {
        headers: {'Content-Type': 'application/json'}
      }).then(response => {
        for (var i = 0; i < response.data.length; i++) {
          articles.push({title: response.data[i].title, day: response.data[i].day, writedate: response.data[i].writedate, 
                          score: response.data[i].score, img: 'https://placeimg.com/640/480/arch'})
        }
      })
      this.articles = articles
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