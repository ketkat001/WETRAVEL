<template>
  <div id="main" class="main">
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
      <hr style="border: 1px solid rgb(196, 195, 208); margin:60px;">
      <h2>{{ $route.params.province }}의 여행지 19곳</h2>
      <p>"활력이 넘치는 도시"</p>
      <h4>원하는 여행지를 골라보세요!</h4>
      <hr style="border: 1px solid rgb(196, 195, 208); margin:60px;">
      <div class="map_set" :style="backImage">
        <div class="hover-bg" :style="backImage2">
        
        </div>
        <img class="trans_map" src="@/assets/img/map_img/map_transparent.png" usemap="#imgMap">
        <map name="imgMap">
          <!--경기도-->
          <area 
            v-for="city in gyeonggi_coords" 
            :key="city.id" 
            class="map_poly"
            shape="poly"
            onfocus="this.blur();"
            :coords="city.coords"
            v-on:mouseover="changebg(city.name)"
            v-on:mouseout="originbg"
            v-on:click="selectcity(city.name)"
            >  
          <!--//경기도-->
        </map>
      </div>
    </div>
    
    
  </div>
</template>

<script>
import { gyeonggi_coords } from '../../assets/js/coords_data.js'

export default {
  name: "ProvincePage",
  data: () => {
    return {
      backImage: {
        backgroundImage: `url(${require("@/assets/img/map_img/Gyeonggi_map/Gyeonggido.png")})`,
      },
      backImage2: {
        backgroundImage: ''
      },
      gyeonggi_coords: gyeonggi_coords,
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
  methods: {
    changebg: function(title) {
      this.backImage2.backgroundImage = `url(${require("@/assets/img/map_img/Gyeonggi_map/" + title + ".png")})`
    },
    originbg: function() {
      this.backImage2.backgroundImage = ''
    },
    selectcity: function(cityName) {
      if (cityName != null && cityName != '') {
        this.$router.push({name: 'citypage', params: {province: '경기', city: cityName}})
      }
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
  },
}
</script>

<style scoped>
  .map_set {
    position: relative; 
    margin: 0 auto;
    display:flex;
    justify-content: center;
    height: 637px;
    width: 629px;
  }
  .hover-bg {
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: 9;
  }
  .trans_map {
    position: absolute;
    top: 0;
    left: 0;
    z-index: 10;
  }
</style>