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
    <div class="main-content container">
      <hr style="border: 1px solid rgb(196, 195, 208); margin:60px;">
      <h2>{{ $route.params.province }}의 여행지 19곳</h2>
      <p>"활력이 넘치는 도시"</p>
      <h4>원하는 여행지를 골라보세요!</h4>
    </div>
    
    <hr style="border: 1px solid rgb(196, 195, 208); margin:60px;">
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>