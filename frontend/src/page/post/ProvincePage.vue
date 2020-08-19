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
                  <option v-for="i in (1, 12)" :key="i" :value="i">{{ i }}월</option>
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
      <h2>{{ $route.params.province }}의 여행지 {{ cities.length + cities2.length }}곳</h2>
      <h4>원하는 여행지를 골라보세요!</h4>
      <hr style="border: 1px solid rgb(196, 195, 208); margin:60px;">
      <div class="map-tab" v-if="backImage3.backgroundImage !== ''">  <!--만약 도가 2개로 나눠진다면 (남, 북) tab을 생성해 나눔-->
        <b-tabs content-class="mt-3" fill>
          <b-tab>
            <template v-slot:title>
              <h3>{{ $route.params.province }} 북도</h3>
            </template>
            <div class="map-set" :style="backImage">
              <div class="hover-bg" :style="backImage2">
              </div>
              <img class="trans_map" src="@/assets/img/map_img/map_transparent.png" usemap="#imgMap">
              <map name="imgMap">
                <area 
                  v-for="city in cities" 
                  :key="city.id" 
                  class="map_poly"
                  shape="poly"
                  onfocus="this.blur();"
                  :coords="city.coords"
                  v-on:mouseover="changebg($route.params.province, city.name)"
                  v-on:mouseout="originbg"
                  v-on:click="selectcity(city.name)"
                  >  
              </map>
            </div>
          </b-tab>
          <b-tab>
            <template v-slot:title>
              <h3>{{ $route.params.province }} 남도</h3>
            </template>
            <div class="map-set" :style="backImage3">
              <div class="hover-bg" :style="backImage4">
              </div>
              <img class="trans_map" src="@/assets/img/map_img/map_transparent.png" usemap="#imgMap2">
              <map name="imgMap2">
                <area 
                  v-for="city in cities2" 
                  :key="city.id" 
                  class="map_poly"
                  shape="poly"
                  onfocus="this.blur();"
                  :coords="city.coords"
                  v-on:mouseover="changebg2($route.params.province, city.name)"
                  v-on:mouseout="originbg"
                  v-on:click="selectcity(city.name)"
                  >  
              </map>
            </div>
          </b-tab>
        </b-tabs>
      </div>
      <div class="map-set" v-if="backImage3.backgroundImage === ''" :style="backImage"> <!--도가 1개라면 그대로 출력-->
        <div class="hover-bg" :style="backImage2">
        </div>
        <img class="trans_map" src="@/assets/img/map_img/map_transparent.png" usemap="#imgMap">
        <map name="imgMap">
          <area 
            v-for="city in cities" 
            :key="city.id" 
            class="map_poly"
            shape="poly"
            onfocus="this.blur();"
            :coords="city.coords"
            v-on:mouseover="changebg($route.params.province, city.name)"
            v-on:mouseout="originbg"
            v-on:click="selectcity(city.name)"
            >  
        </map>
      </div>
    </div>
    
    
  </div>
</template>

<script>
import { gyeonggi_coords, gangwon_coords, chungcheongnorth_coords, chungcheongsouth_coords, jeollanorth_coords, jeollasouth_coords, gyeongsangnorth_coords, gyeongsangsouth_coords } from '../../assets/js/coords_data.js'

var cities = []
var cities2 = []

export default {
  name: "ProvincePage",
  data: function() {
    return {
      backImage: {
        backgroundImage: '',
      },
      backImage2: {
        backgroundImage: ''
      },
      backImage3: {
        backgroundImage: '',
      },
      backImage4: {
        backgroundImage: '',
      },
      searchProvince: '',
      searchCity: '',
      searchMonth: 0,
      cities: cities,
      cities2: cities2,
    }
  },
  computed: {
    replaceCityList() {
      return this.$store.getters.getCityList
    },
  },
  mounted() {
    this.getMap(this.$route.params.province)
    this.searchProvince = this.$route.params.province ? this.$route.params.province : ''
    this.cityList()
    this.searchCity = this.$route.params.city ? this.$route.params.city : ''
    this.cityChange()
    this.searchMonth = this.$route.params.month ? this.$route.params.month : 0
  },
  methods: {
    getMap: function(province) {
      if (province === '경기') {
        this.cities = gyeonggi_coords
        this.backImage.backgroundImage = `url(${require("@/assets/img/map_img/Gyeonggi_map/Gyeonggido.png")})`
      }else if (province === '강원') {
        this.cities = gangwon_coords
        this.backImage.backgroundImage = `url(${require("@/assets/img/map_img/Gangwon_map/Gangwondo.png")})`
      }else if (province === '충청') {
        this.cities = chungcheongnorth_coords
        this.cities2 = chungcheongsouth_coords
        this.backImage.backgroundImage = `url(${require("@/assets/img/map_img/Chungcheong_map/North/ChungcheongNorth.png")})`
        this.backImage3.backgroundImage = `url(${require("@/assets/img/map_img/Chungcheong_map/South/ChungcheongSouth.png")})`
      }else if (province === '전라') {
        this.cities = jeollanorth_coords
        this.cities2 = jeollasouth_coords
        this.backImage.backgroundImage = `url(${require("@/assets/img/map_img/Jeolla_map/North/JeollaNorth.png")})`
        this.backImage3.backgroundImage = `url(${require("@/assets/img/map_img/Jeolla_map/South/JeollaSouth.png")})`
      }else if (province === '경상') {
        this.cities = gyeongsangnorth_coords
        this.cities2 = gyeongsangsouth_coords
        this.backImage.backgroundImage = `url(${require("@/assets/img/map_img/Gyeongsang_map/North/GyeongsangNorth.png")})`
        this.backImage3.backgroundImage = `url(${require("@/assets/img/map_img/Gyeongsang_map/South/GyeongsangSouth.png")})`
      }
    },
    changebg: function(province, title) {
      if (province === '경기') {
        this.backImage2.backgroundImage = `url(${require("@/assets/img/map_img/Gyeonggi_map/" + title + ".png")})`
      }else if (province === '강원') {
        this.backImage2.backgroundImage = `url(${require("@/assets/img/map_img/Gangwon_map/" + title + ".png")})`
      }else if (province === '충청') {
        this.backImage2.backgroundImage = `url(${require("@/assets/img/map_img/Chungcheong_map/North/" + title + ".png")})`
      }else if (province === '전라') {
        this.backImage2.backgroundImage = `url(${require("@/assets/img/map_img/Jeolla_map/North/" + title + ".png")})`
      }else if (province === '경상') {
        this.backImage2.backgroundImage = `url(${require("@/assets/img/map_img/Gyeongsang_map/North/" + title + ".png")})`
      }
    },
    changebg2: function(province, title) {
      if (province === '충청') {
        this.backImage4.backgroundImage = `url(${require("@/assets/img/map_img/Chungcheong_map/South/" + title + ".png")})`
      }else if (province === '전라') {
        this.backImage4.backgroundImage = `url(${require("@/assets/img/map_img/Jeolla_map/South/" + title + ".png")})`
      }else if (province === '경상') {
        this.backImage4.backgroundImage = `url(${require("@/assets/img/map_img/Gyeongsang_map/South/" + title + ".png")})`
      }
    },
    originbg: function() {
      this.backImage2.backgroundImage = ''
      this.backImage4.backgroundImage = ''
    },
    selectcity: function(cityName) {
      if (cityName != null && cityName != '') {
        this.$router.push({name: 'citypage', params: {province: this.$route.params.province, city: cityName, month: 0}})
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
  .map-set {
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