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
      <div class="main-content container">
        <h2>{{ $route.params.city }}의 새로운 기행문을 확인해보세요</h2>
        <div class="content-card row">
          
          <div v-for="(card, index) in cards" :key="index" :ref="`card_${index}`" class="card-wrap col-lg-3 col-sm-6">
            <b-link :to="{name: 'bookpage', params: { province: $route.params.province, city: $route.params.city, bookno: card.bookno }}">
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
/* const cards = [
  {title: '서울의 밤은 밝다', author: '서울 야경', image: 'https://placeimg.com/640/480/nature'},
  {title: '부산의 야경을 보다', author: '전국 여행', image: 'https://placeimg.com/640/480/animals'},
  {title: '제주도를 가다', author: '한국 여행', image: 'https://placeimg.com/640/480/arch'},
  {title: '서울의 밤은 밝다', author: '서울 야경', image: 'https://placeimg.com/640/480/nature'},
  {title: '부산의 야경을 보다', author: '전국 여행', image: 'https://placeimg.com/640/480/animals'},
  {title: '제주도를 가다', author: '한국 여행', image: 'https://placeimg.com/640/480/arch'},
  {title: '부산의 야경을 보다', author: '전국 여행', image: 'https://placeimg.com/640/480/animals'},
  {title: '제주도를 가다', author: '한국 여행', image: 'https://placeimg.com/640/480/arch'},
] */
var cards = []
export default {
  name: "CityPage",
  data: function() {
    return {
      cards: cards,
      searchProvince: '',
      searchCity: '',
      searchMonth: 0
    } 
  },
  computed: {
    replaceCityList() {
      return this.$store.getters.getCityList
    }
  },
  created() {
  },
  watch: {
    '$route.params.city': function(city) {
      this.getBookList()
    }
  },
  mounted() {
    this.getBookList()
  },
  methods: {
    getBookList: function() {
      let today = new Date();
      while (cards.length > 0) {
        cards.pop()
      }
      this.$axios.get(`/api/book/all/date`, {
        params: {
          province: this.$route.params.province,
          city: this.$route.params.city,
          month: this.$route.params.month ? this.$route.params.month : 0
        },
        headers: {'Content-Type': 'application/json'}
      }).then(response => {
        for (var i = 0; i < response.data.length; i++) {
          cards.push({bookno: response.data[i].bookno, title: response.data[i].title, author: response.data[i].writer, image: 'https://placeimg.com/640/480/arch'})
        }
      })
    },
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