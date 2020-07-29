<template>
  <div id="main-wrapper" class="main-wrapper">
    <main id="main" class="main">
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
      <div class="main-content container">
        <h2>{{ $route.params.city }}의 새로운 기행문을 확인해보세요</h2>
        <div class="content-card row">
          
          <div v-for="(card, index) in cards" :key="index" :ref="`card_${index}`" class="card-wrap col-lg-3 col-sm-6">
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
    </main>
  </div>
</template>

<script>
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
  name: "CityPage",
  data: function() {
    return {
      cards: cards,
    } 
  },
  created() {
    const city = this.$route.params.city;
    const cities = ['서울', '부산']
    let re = false
    for (let i=0; i<cities.length; i++) {
      if (cities[i] === city) {
        re = true
      }
    }
    if (re === false) {
      this.$router.go(-1);
    }
  },
  method: {
    
  }

}
</script>

<style scoped>
  
  .container span {
    display: inline;
  }

  @media (min-width: 992px){
      .search-sec{
          position: relative;
          top: -114px;
          background: rgba(26, 70, 104, 0.51);
      }
  }

  @media (max-width: 992px){
      .search-sec{
          background: #1A4668;
      }
  }

</style>