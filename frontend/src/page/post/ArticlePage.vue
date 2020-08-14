<template>
  <div id="main" class="main">
    <div class="article-header">
      <div class="container">
        <div class="row">
          <div class="col-4 text-left">
            <h5>2020.07.15</h5>
          </div>
          <div class="col-4">
            <h5>Day 1</h5>
          </div>
          <div class="col-4 text-right">
            <h5>Score: 4.8</h5>
          </div>
        </div>
      </div>
    </div>
    <div class="article-content">
      <h2>서울의 밤은 밝다</h2>
      <h5>By 서울야경</h5>
      <div class="article-map">
        <p v-html="text"></p>
      </div>
    </div>
    <div class="article-footer">
      <star-rating :increment="0.01"
        :max-rating="5"
        active-color="#007bff"
        :star-size="25">
      </star-rating>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      bookno: this.$route.params.bookno,   //책 번호 url에서 받아서 bookno에 저장
          writedate:'',
          title:'',
          day:'',
          traveldate:'',
          text: '',
          articleno:this.$route.params.articleno
    }
  },
  mounted(){
    this.$axios.get(`/api/article/${this.articleno}`, {
      headers: {'Content-Type': 'application/json'}
    }).then(res => {
      this.writedate = res.data.writedate
      this.title = res.data.title
      this.day = res.data.day
      this.text = res.data.text
    })
  }
}
</script>

<style>
  .article-header {
    background-color: rgb(245, 245, 245);
    padding: 10px;
    margin-bottom: 30px;
  }
</style>