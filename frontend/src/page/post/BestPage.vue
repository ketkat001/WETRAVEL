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
    <div class="best-content container">
      <h3>{{ $route.params.city }} BEST 5</h3>
      <hr style="border: 1px solid #C4C3D0l; margin:30px;">
      <h4 style="text-align:left; margin-left: 25px;">가장 인기 많은 기행문을 확인해보세요</h4>
      <div class="content-card row">
        <div v-for="(card, index) in cards" :key="index" :ref="`card_${index}`" class="card-wrap col-lg-3 col-sm-6">
          <div class="card travel-card">
            <img class="travel-card-image" :src="card.image">
            <div class="travel-card-footer">
              <p class="travel-card-text">{{ $route.params.city }}</p>
              <h3 class="travel-card-title">{{ card.title }}</h3>
              <p class="travel-card-text">by <span class="travel-card-author">{{ card.author }}</span></p>
            </div>
          </div>
        </div>
        <div class="best-card">
          <b-card
            overlay
            :img-src="bestcard.image"
            img-alt="Card Image"
            text-variant="white"
            title="서울의 밤은 밝다"
            sub-title="by 서울 야경"
          >
            <b-card-text>
              바야흐로 집에 있는 게 미덕인 시대다. 집에만 있어 시간 가는 걸 몰랐지, 더위는 나라님도 못 속인다더니 어느덧 뺨에 닿은 바람에서 더운 내가 났다. 집에 앉아 있는다고 가실만한 더위도, 갈증도 분명 아니었다. 벌써부터 이리 덥다니, 올해 여름 나기를 상상만 해도 아찔했다. 그래서 찾아 본 백신 같은 여행지 몇 군데. 모아보니 그곳은 강원도였다.
            </b-card-text>
            <div class="card-info">
               
            </div>
          </b-card>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
const best_card = {title: '서울의 밤은 밝다', author: '서울 야경', image: 'https://placeimg.com/640/480/nature'}
const cards = [
  {title: '부산의 야경을 보다', author: '전국 여행', image: 'https://placeimg.com/640/480/animals'},
  {title: '제주도를 가다', author: '한국 여행', image: 'https://placeimg.com/640/480/arch'},
  {title: '서울의 밤은 밝다', author: '서울 야경', image: 'https://placeimg.com/640/480/nature'},
  {title: '부산의 야경을 보다', author: '전국 여행', image: 'https://placeimg.com/640/480/animals'},
]
export default {
  name: "BestPage",
  data: function () {
    return {
      cards : cards,
      bestcard : best_card
    }
  }
}
</script>

<style scoped>
  .best-card {
    width: 100%;
    margin: 15px;
    margin-bottom: 30px;
  }
  .best-card .card-title{
    font-size: 30px;
    margin-bottom: 30px;
  }
  .best-card .card-subtitle {
    font-size: 20px;
    color : #BAB096;
    margin-bottom: 30px !important;
  }
  .best-card .card-text {
    line-height: 2em;
    margin-bottom: 30px;
  }
  .best-card img.card-img {
    height: 400px;
  }
</style>