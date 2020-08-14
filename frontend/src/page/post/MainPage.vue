<template>
  <div id="main-wrapper" class="main-wrapper">
    <main id="main" class="main">
      <div class="home-article">
        <header class="home-header">
          <div class="home-cover">
            <b-carousel
              id="carousel-fade"
              style="text-shadow: 0px 0px 2px #000"
              fade
              indicators
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
            <div class="home-header-text">
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
          <div class="container m-0 p-0">
            <form method="post" novalidate="novalidate">
              <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                  <select v-model="searchProvince" class="form-control search-slt" id="provinceComboBox" @change="cityList">
                    <option selected value="">지역 선택</option> 
                    <option v-for="(province, index) in this.$store.getters.getProvinceList" :key="index" :value="province">{{ province }}</option>
                  </select>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                  <select v-model="searchCity" class="form-control search-slt" id="cityComboBox" @change="cityChange" :disabled="searchProvince == ''">
                    <option selected value="">도시 선택</option> 
                    <option v-for="(city, index) in replaceCityList" :key="index" :value="city">{{ city }}</option>
                  </select>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                  <select v-model="searchMonth" class="form-control search-slt" id="monthComboBox" :disabled="searchCity == ''">
                    <option selected value="0">전체</option> 
                    <option v-for="i in (1, 12)" :key="i" :value="i">{{ i }}</option>
                  </select>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                  <button type="button" class="btn wrn-btn" @click="goCityPage">Search</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="home-content container">
        <div class="korea-city" data-aos="zoom-in" data-aos-duration='1000'>
          <h2 style="margin-bottom: 40px; color: primary;">좋아하는 여행지를 골라보세요!</h2>
          <div class="row city-box">
            <div v-for="(city, index) in city_thumb" :key="index">
              <div class="korea-img" :style="{ backgroundImage: 'url(' + city.image + ')'}">
                <h4>{{ city.title }}</h4>
              </div>
            </div>
          </div>
          <div class="row province-box">
            
          </div>
        </div>
        
        <div class="best-article" data-aos="zoom-in" data-aos-duration='1000'>
          <h2 style="margin-bottom:40px;">믿고 보는 베스트 여행기!</h2>
          <div class="content-card row">
            <div v-for="(card, index) in cards" :key="index" :ref="`card_${index}`" class="card-wrap col-lg-3 col-6">
              <b-link to="/posts/$route.params.province/$route.params.city/1/">
                <div  class="card travel-card">
                  <img class="travel-card-image" :src="card.image">
                  <div class="travel-card-footer">
                    <p class="travel-card-text">{{ $route.params.city }}</p>
                    <h3 class="travel-card-title">{{ card.title }}</h3>
                    <p class="travel-card-text">by <span class="travel-card-author">{{ card.author }}</span></p>
                  </div>
                </div>
              </b-link>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>
 
<script>

const city_thumb = [
  {title: '서울', image: require('@/assets/img/city/resize/Seoul.jpg')},
  {title: '부산', image: require('@/assets/img/city/resize/Busan.jpg')},
  {title: '제주', image: require('@/assets/img/city/resize/Jeju.jpg')},
]
const cards = [
  {title: '서울의 밤은 밝다', author: '서울 야경', image: 'https://placeimg.com/640/480/nature'},
  {title: '부산의 야경을 보다', author: '전국 여행', image: 'https://placeimg.com/640/480/animals'},
  {title: '제주도를 가다', author: '한국 여행', image: 'https://placeimg.com/640/480/arch'},
  {title: '서울의 밤은 밝다', author: '서울 야경', image: 'https://placeimg.com/640/480/nature'},
  {title: '부산의 야경을 보다', author: '전국 여행', image: 'https://placeimg.com/640/480/animals'},
  {title: '제주도를 가다', author: '한국 여행', image: 'https://placeimg.com/640/480/arch'},
  {title: '부산의 야경을 보다', author: '전국 여행', image: 'https://placeimg.com/640/480/animals'},
  {title: '제주도를 가다', author: '한국 여행', image: 'https://placeimg.com/640/480/arch'},
]
export default {
  name: "Post",
  components: {},
  computed: {
    replaceCityList() {
      return this.$store.getters.getCityList
    }
  },
  watch: {},
  created() {
  },
  methods: {
    cityList: async function() {
      if (this.searchProvince == '') {
        this.searchCity = ''
        this.searchMonth = 0
      }
      else {
        await this.$store.dispatch('getCityList', this.searchProvince)
      }
    },
    cityChange: function() {
      if (this.searchCity == '')
        this.searchMonth = 0
    },
    goCityPage: function() {
      if (this.searchProvince == '') {
        alert('지역을 선택해주세요')
        return
      }
      if (this.searchCity == '') {
        alert('도시를 선택해주세요')
        return
      }
      this.$router.push({name: 'citypage', params: {province: this.searchProvince, city: this.searchCity, month: this.searchMonth}})
    }
  },
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
      cards: cards,
      searchProvince: '',
      searchCity: '',
      searchMonth: 0,
      city_thumb: city_thumb
    };
  },
};
</script>

<style>
  #main-wrapper {
    padding-bottom: 80px;
  }
  .row {
    margin: 0px;
  }
  .home-cover {
    margin-top: 60px;
    max-width: 100%;
    height: 720px;
    z-index: 0;
  }
  .home-cover-pic {
    max-width: 100%;
    height: auto;
    transition: 1s;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 5;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
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
      background-color : #007bff;
      color:#fff
  }
  .home-article {
    height: 720px;
  }

  .home-cover .carousel-caption {
    position: static;
    height: 720px;
  }
  .home-header-text {
    z-index: 10;
    margin-top: 60px;
    text-align: center;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
  }
  .home-text {
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
  .korea-img {
    width: 200px;
    height: 200px;
    border-radius: 50%;
    background-position: center;
    display:flex;
    justify-content: center;
    align-items: center;
    color: white;
    margin: 10px;
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
  @media screen and (max-width: 1024px) {
    .home-header-text {
      transform: translate(-50%, -150%);
    }
  }
  @media screen and (max-width: 768px) {
    .home-header-text {
      transform: translate(-35%, -100%);
    }
  }
  @media screen and (max-width: 500px) {
    .home-header-text {
      transform: translate(-15%, -50%);
    }
  }
</style>