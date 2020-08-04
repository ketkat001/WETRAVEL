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
    <hr style="border: 1px solid #C4C3D0l; margin-bottom: 30px;">
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
/* const articles = [
  {title: '광화문 탐방', day: '1', writedate: '2020/03/11', score: 4.12, img: 'https://placeimg.com/640/480/animals'},
  {title: '서촌 맛집 탐방', day: '2', writedate: '2020/03/15', score: 4.53, img: 'https://placeimg.com/640/480/arch'},
  {title: '동대문 패션 타운', day: '3', writedate: '2020/03/16', score: 4.91, img: 'https://placeimg.com/640/480/nature'},
  {title: '인사동 방문 후기', day: '4', writedate: '2020/03/18', score: 4.03, img: 'https://placeimg.com/640/480/animals'},
  {title: '북촌 한옥마을에서 서울 내려보기', day: '5', writedate: '2020/03/19', score: 5.0, img: 'https://placeimg.com/640/480/nature'},
  {title: '명동에서 쇼핑하기', day: '6', writedate: '2020/03/21', score: 4.51, img: 'https://placeimg.com/640/480/arch'},
  {title: '이태원에서 즐기기', day: '7', writedate: '2020/03.22', score: 3.21, img: 'https://placeimg.com/640/480/animals'},
] */
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
  mounted() {
    let today = new Date();
    if (articles.length > 0) {
      return
    }
    this.$axios.get(`/api/article/all/${this.$route.params.bookno}`, {
      headers: {'Content-Type': 'application/json'}
    }).then(response => {
      for (var i = 0; i < response.data.length; i++) {
        articles.push({title: response.data[i].title, day: response.data[i].day, writedate: response.data[i].writedate, 
                        score: response.data[i].score, img: 'https://placeimg.com/640/480/arch'})
      }
    })
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