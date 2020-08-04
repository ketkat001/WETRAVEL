<template>
  <div id="main-wrapper" class="main-wrapper">
    <main id="main" class="main">
      <div class="home-article">
        <header class="home-header" style="height=720px;">
          <div class="home-cover">
            <b-carousel
              id="carousel-fade"
              style="text-shadow: 0px 0px 2px #000"
              fade
              indicators
              img-width="1280"
              img-height="720"
            >
              <b-carousel-slide>
                <span class="home-cover-pic" :style="backImage1"></span>
              </b-carousel-slide>
              <b-carousel-slide>
                <span class="home-cover-pic" :style="backImage2"></span>
              </b-carousel-slide>
              <b-carousel-slide>
                <span class="home-cover-pic" :style="backImage3"></span>
              </b-carousel-slide>
            </b-carousel>
            <div class="home-header-text container">
              <span class="home-text text1">We Travel에 오신걸 환영합니다!</span>
              <span class="home-text text2">당신의 여행을 공유 해보세요!</span>
            </div>
          </div>
        </header>
      </div>
      <div class="search-box">
        <div class="search-content">
          <h3>여행지 검색 - 다른 기행문도 찾아보세요!</h3>
          <p>도심의 야경부터 시원한 바다까지</p>
          <div class="container m-0">
            <form method="post" novalidate="novalidate">
              <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                  <select class="form-control search-slt" id="example">
                    <option>서울</option>
                    <option>경기</option>
                    <option>강원</option>
                    <option>충청</option>
                    <option>전라</option>
                    <option>경상</option>
                    <option>제주</option>
                    <option>부산</option>
                  </select>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                  <select class="form-control search-slt" id="example">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                  </select>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                  <select class="form-control search-slt" id="example">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                  </select>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                  <button type="button" class="btn wrn-btn">Search</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="home-content container">
        <div class="korea-city" data-aos="zoom-in" data-aos-duration='1000'>
          <h2 style="margin-bottom: 40px; color: primary;">좋아하는 여행지를 골라보세요!</h2>
          <div class="row">
            <div class="col-3">
              <b-link to="/posts/서울/서울">
                <b-card
                  img-src="https://picsum.photos/600/300/"
                  style="max-width: 20rem;"
                  class="mb-2"
                >
                  <b-card-text>서울, 대한민국의 수도</b-card-text>
                </b-card>
              </b-link>
            </div>
            <div class="col-3">
              <b-link to="/posts/경기">
                <b-card
                  img-src="https://picsum.photos/600/300/"
                  style="max-width: 20rem;"
                  class="mb-2"
                >
                  <b-card-text>경기, 활력 넘치는 도시</b-card-text>
                </b-card>
              </b-link>
            </div>
            <div class="col-3">
              <b-link to="/posts/강원">
                <b-card
                  img-src="https://picsum.photos/600/300/"
                  style="max-width: 20rem;"
                  class="mb-2"
                >
                  <b-card-text>강원, 자연과 힐링</b-card-text>
                </b-card>
              </b-link>
            </div>
            <div class="col-3">
              <b-link to="/posts/충청">
                <b-card
                  img-src="https://picsum.photos/600/300/"
                  style="max-width: 20rem;"
                  class="mb-2"
                >
                  <b-card-text>충청, 교통의 중심</b-card-text>
                </b-card>
              </b-link>
            </div>
            <div class="col-3">
              <b-link to="/posts/전라">
                <b-card
                  img-src="https://picsum.photos/600/300/"
                  style="max-width: 20rem;"
                  class="mb-2"
                >
                  <b-card-text>전라, 맛의 중심</b-card-text>
                </b-card>
              </b-link>
            </div>
            <div class="col-3">
              <b-link to="/posts/경상">
                <b-card
                  img-src="https://picsum.photos/600/300/"
                  style="max-width: 20rem;"
                  class="mb-2"
                >
                  <b-card-text>경상, 바다의 도시</b-card-text>
                </b-card>
              </b-link>
            </div>
            <div class="col-3">
              <b-link to="posts/제주">
                <b-card
                  img-src="https://picsum.photos/600/300/"
                  style="max-width: 20rem;"
                  class="mb-2"
                >
                  <b-card-text>제주, 추억과 행복</b-card-text>
                </b-card>
              </b-link>
            </div>
            <div class="col-3">
              <b-link to="/posts/부산/부산">
                <b-card
                  img-src="https://picsum.photos/600/300/"
                  style="max-width: 20rem;"
                  class="mb-2"
                >
                  <b-card-text>부산, 야경의 도시</b-card-text>
                </b-card>
              </b-link>
            </div>
          </div>
        </div>
        <div class="best-city-article" data-aos="zoom-in" data-aos-duration='1000'>
          <h2 style="margin-bottom:40px;">지역별 베스트 여행기를 보세요!</h2>
          <div class="row" style="justify-content: center;">
            <div class="col-10">
              <carousel-3d>
                <slide :index="0">
                  <img src="@/assets/img/12.jpg">
                  <div class="slide-text">
                    <h3>서울</h3>
                  </div>
                </slide>
                <slide :index="1">너무!!!!!!</slide>
                <slide :index="2">힘들다!!!!!</slide>
                <slide :index="3">힘들다!!!!!</slide>
                <slide :index="4">힘들다!!!!!</slide>
                <slide :index="5">힘들다!!!!!</slide>
                <slide :index="6">힘들다!!!!!</slide>
                <slide :index="7">힘들다!!!!!</slide>
              </carousel-3d>
            </div>
          </div>
        </div>
        <div class="best-article" data-aos="zoom-in" data-aos-duration='1000'>
          <h2 style="margin-bottom:40px;">믿고 보는 베스트 여행기!</h2>
          <div class="row" style="justify-content:center;">
            <div class="col-3">
              <b-card
                img-src="https://picsum.photos/600/300/"
                style="max-width: 20rem;"
                class="mb-2"
              >
                <b-card-text>서울, 대한민국의 수도</b-card-text>
              </b-card>
            </div>
            <div class="col-3">
              <b-card
                img-src="https://picsum.photos/600/300/"
                style="max-width: 20rem;"
                class="mb-2"
              >
                <b-card-text>서울, 대한민국의 수도</b-card-text>
              </b-card>
            </div>
            <div class="col-3">
              <b-card
                img-src="https://picsum.photos/600/300/"
                style="max-width: 20rem;"
                class="mb-2"
              >
                <b-card-text>서울, 대한민국의 수도</b-card-text>
              </b-card>
            </div>
            <div class="col-3">
              <b-card
                img-src="https://picsum.photos/600/300/"
                style="max-width: 20rem;"
                class="mb-2"
              >
                <b-card-text>서울, 대한민국의 수도</b-card-text>
              </b-card>
            </div>
            <div class="col-3">
              <b-card
                img-src="https://picsum.photos/600/300/"
                style="max-width: 20rem;"
                class="mb-2"
              >
                <b-card-text>서울, 대한민국의 수도</b-card-text>
              </b-card>
            </div>
            <div class="col-3">
              <b-card
                img-src="https://picsum.photos/600/300/"
                style="max-width: 20rem;"
                class="mb-2"
              >
                <b-card-text>서울, 대한민국의 수도</b-card-text>
              </b-card>
            </div>
            <div class="col-3">
              <b-card
                img-src="https://picsum.photos/600/300/"
                style="max-width: 20rem;"
                class="mb-2"
              >
                <b-card-text>서울, 대한민국의 수도</b-card-text>
              </b-card>
            </div>
            <div class="col-3">
              <b-card
                img-src="https://picsum.photos/600/300/"
                style="max-width: 20rem;"
                class="mb-2"
              >
                <b-card-text>서울, 대한민국의 수도</b-card-text>
              </b-card>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>
 
<script>
export default {
  name: "Post",
  components: {},
  watch: {},
  created() {},
  methods: {},
  data: () => {
    return {
      backImage1: {
        backgroundImage: `url(${require("@/assets/img/12.jpg")})`,
      },
      backImage2: {
        backgroundImage: `url(${require("@/assets/img/13.jpg")})`,
      },
      backImage3: {
        backgroundImage: `url(${require("@/assets/img/14.jpg")})`,
      },
    };
  },
};
</script>

<style>
  #main-wrapper {
    padding-bottom: 80px;
  }
  .home-cover {
    margin-top: 60px;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 0;
  }
  .home-cover-pic {
    transition: 1s;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 5;
    height: 720px;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
  }
  .home-header-text {
    width: 100%;
    height: 720px;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    
  }
  .search-box {
    background-color: rgb(245, 245, 245);
    margin-bottom: 60px;
  }
  .search-content {
    max-width: 1100px;
    text-align: left;
    padding: 40px;
    margin: 0 auto;
  }
  .search-sec{
    padding: 2rem;
  }
  .search-slt{
      display: block;
      width: 100%;
      font-size: 0.875rem;
      line-height: 1.5;
      color: #55595c;
      background-color: #fff;
      background-image: none;
      border: 1px solid #ccc;
      height: calc(3rem + 2px) !important;
      border-radius:0;
  }
  .wrn-btn{
      width: 100%;
      font-size: 16px;
      font-weight: 400;
      text-transform: capitalize;
      height: calc(3rem + 2px) !important;
      border-radius:0;
  }
  button.btn {
    background-color : #007bff;
    color: #fff;
  }
  .home-article {
    height: 720px;
  }

  .home-cover .carousel-caption {
    position: static;
    height: 720px;
  }
  .home-header .container {
    z-index: 10;
    margin-top: 60px;
    text-align: center;
    position: absolute;
    top: 60%;
    left: 50%;
    height: 500px;
    transform: translate(-50%, -50%);
    overflow: hidden;
  }
  .container .home-text {
    color: white;
    display: block;
    font-size: 60px;
    font-weight: 700;
    letter-spacing: 3px;
    margin-bottom: 50px;
    animation: text 3s 1;
  }
  .korea-city{
    margin-bottom: 40px;
  }
  .korea-city a:visited{
    color:black;
  }
  .korea-city a:link {
    text-decoration: none;
  }
  .best-city-article {
    margin-bottom: 40px;
  }
  .carousel-3d-slide img {
    height: 100%;
    opacity: 0.8;
  }
  .slide-text {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    color:#ccc
  }
  .content-card {
    text-align: left;
    justify-content: center;
    align-content: center;
    width: 100%;
    margin: 0;
  }
  .card-wrap {
    display: flex;
    justify-content: center;
    margin-top: 30px;
    margin-bottom: 30px;
  }
  .travel-card {
    background-color: #FFFFFF;
    height: 370px;
    width: 240px;
    margin: 10px;
    overflow: hidden;
    box-shadow: 0px 2px 4px 0px rgba(0,0,0,0.5);
  }
  .travel-card-image {
    position: absolute;
    left: -9999px;
    right: -9999px;
    margin: auto;
    height: 220px;
    min-width: 100%;
    transition: height 0.3s, opacity 0.3s;
  }
  .travel-card-footer {
    position: absolute;
    bottom: 0;
    height: 150px;
    width: 100%;
    padding: 10px 10px;
    font-family: Helvetica;
  }
  .travel-card-text {
    font-size: 16px;
    color: rgba(0, 0, 0, 0.7);
  }
  .travel-card-title {
    font-size: 24px;
    font-family: Serif;
    width: 200px;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    color: rgba(0, 0, 0);
    font-weight: 400;

  }
  .travel-card-author {
    font-size: 14px;
    color: #BAB096;
  }
  @keyframes text {
    0% {
      color: rgba(255, 255, 255, 0);
    }
    30% {
      letter-spacing: 12px;
    }
    95% {
      letter-spacing: 3px;
    }
  }
</style>