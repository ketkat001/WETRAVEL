<template>
  <div id="main" class="main">
    <div class="article-header">
      <div class="container">
        <div class="row">
          <div class="col-4 text-left">
            <h5>{{ this. writedate }}</h5>
          </div>
          <div class="col-4">
            <h5>{{ this.title }}</h5>
          </div>
          <div class="col-4 text-right">
            <h5>{{ day }}화</h5>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="article-content">
        <div id="map" style="width:600px;height:350px;"></div>
        <div class="article-map">
          <p style="width:100%" v-html="text"></p>
        </div>
      </div>
      <div class="article-footer">
        <div class="article-btn" v-show="isAuthor">
          <b-button class="m-3" variant="primary" @click="modifyAction">수정</b-button>
          <b-button class="m-3" variant="danger" @click="deleteAction">삭제</b-button>
        </div>
        <star-rating :increment="0.5"
          :max-rating="5"
          active-color="#007bff"
          :star-size="25" @rating-selected="setRating">
        </star-rating>
        <div class="article-comment">
          <div class="comments-header">
            <div class="comments-stats">
              <span class="m-3"><font-awesome-icon icon="thumbs-up" size="lg" class="mx-3"></font-awesome-icon>1</span>
              <span class="m-3"><font-awesome-icon icon="comment" size="lg" class="mx-3"></font-awesome-icon>2</span>
            </div>
          </div>
          <hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
          <comments
            :comments_wrapper_classes="['custom-scrollbar', 'comments-wrapper']"
            :comments="comments"
            @submit-comment="submitComment">
          </comments>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { faThumbsUp as thumbsUp_SolidIcon, faComment } from "@fortawesome/free-solid-svg-icons";
import comments from '../comments/Comments.vue'
let lats = ''
let longs = ''
export default {
  components: {
    comments
  },
  data(){
    return {
      bookno: this.$route.params.bookno,   //책 번호 url에서 받아서 bookno에 저장
      writedate:'',
      writer:'',
      title:'',
      day:'',
      traveldate:'',
      text: '',
      articleno:this.$route.params.articleno,
      comments: [],
      isAuthor: false
    }
  },
  async mounted(){
    await this.$axios.get(`/api/article/${this.articleno}`, {
      headers: {'Content-Type': 'application/json'}
    }).then(res => {
      this.writedate = res.data.writedate
      this.writer = res.data.writer
      this.title = res.data.title
      this.day = res.data.day
      this.text = res.data.text
      lats = res.data.exiflat
      longs = res.data.exiflong
    })
    this.authorCheck()
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  methods : {
    setRating: function(rating) {
      alert("!!")
    },
    initMap() { 
      var container = document.getElementById('map');
      var latArr = lats.split(" ")
      var longArr = longs.split(" ")
      var latCen = ''
      var longCen = ''
      for(var i in latArr){
         latCen = Number(latCen) + Number(latArr[i])
         longCen = Number(longCen) + Number(longArr[i])
      }

      var options = { 
        center: new kakao.maps.LatLng(Number(latCen/(latArr.length-1)), Number(longCen/(longArr.length-1))), 
        level: 15
      }; 
      var map = new kakao.maps.Map(container, options); //마커추가하려면 객체를 아래와 같이 하나 만든다. 
      
      var linePath = [];

      for(var i=0; i<latArr.length-1; i++){
        linePath.push(new kakao.maps.LatLng(latArr[i],longArr[i]))
      }

      var polyline = new kakao.maps.Polyline({
        path: linePath, // 선을 구성하는 좌표배열 입니다
        strokeWeight: 5, // 선의 두께 입니다
        strokeColor: '#FFAE00', // 선의 색깔입니다
        strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'solid' // 선의 스타일입니다
      });

      polyline.setMap(map);  

      for(var i in latArr){
          var marker = new kakao.maps.Marker({ 
            position: new kakao.maps.LatLng(latArr[i],longArr[i]),
          }); 
          marker.setMap(map) 
          }
      },

      
      
      addScript() { 
        const script = document.createElement('script'); 
        /* global kakao */ 
        script.onload = () => kakao.maps.load(this.initMap); 
        script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=06361ba6891dd71194432873935299c9'; 
        document.head.appendChild(script); 
        },
    submitComment: function(reply) {
      this.comments.push({
        id: this.comments.length + 1,
				user: '',
        text: reply
      })
    },
    deleteAction(){
      let res = confirm("정말로 삭제하시겠습니까?")
      if (res == true) {
        axios.
          delete(`/api/article/${this.articleno}`, {
          articleno : this.articleno
          }, {headers : {'Content-Type': 'application/json'}})
          .then(({ data }) => {
            let msg = '삭제 처리시 문제가 발생했습니다.';
            if (data === 'success') {
              msg = '삭제가 완료되었습니다.';
            }
            alert(msg);
            this.$router.push({name: 'bookpage', params: {province: this.$route.params.province, city: this.$route.params.city, bookno: this.$route.params.bookno}})
          });
      }
    },
    modifyAction(){
      this.$router.push({name : 'articlemodify'})
    },
    authorCheck: async function() {
      if (sessionStorage.getItem('jwt-auth-token')) {
        var nickname
        await this.$store.dispatch('checkLogin').then(res => {
          this.$store.dispatch('getBookInfo', this.$route.params.bookno).then(response => {
            if (res.nickname == response.writer)
              this.isAuthor = true
            else
              this.isAuthor = false
          })
        })

      }
    }
  }
}
</script>

<style scoped>
  .article-header {
    background-color: rgb(245, 245, 245);
    padding: 10px;
    margin-bottom: 30px;
  }
  .article-content {
    min-height: 400px;
  }
  .article-footer {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin : 30px;
  }
  .article-btn {
    display: flex;
    justify-content: flex-end;
  }
  .vue-star-rating {
    justify-content: center;
    margin-bottom: 20px;
    margin-top: 20px;
  }
  .comments-stats { 
    width: 100%;
    min-height: 50px;
    border-radius: 10px;
    display: flex;
    justify-content: flex-end;  
  }

  .article-map > p > p > img {
    width: 100%;
    height: auto;
  }
  
</style>