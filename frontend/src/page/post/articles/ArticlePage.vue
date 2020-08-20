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
        <div class="article-map">
          <div id="map" style="width:100%;height:350px;">
            <ul id="category">
        <li id="BK9" data-order="0"> 
            <span class="category_bg bank"></span>
            은행
        </li>       
        <li id="MT1" data-order="1"> 
            <span class="category_bg mart"></span>
            마트
        </li>  
        <li id="PM9" data-order="2"> 
            <span class="category_bg pharmacy"></span>
            약국
        </li>  
        <li id="OL7" data-order="3"> 
            <span class="category_bg oil"></span>
            주유소
        </li>  
        <li id="CE7" data-order="4"> 
            <span class="category_bg cafe"></span>
            카페
        </li>  
        <li id="CS2" data-order="5"> 
            <span class="category_bg store"></span>
            편의점
        </li>      
    </ul>
          </div>
          <p style="width:100%" v-html="text"></p>
        </div>
      </div>
      <div class="article-footer">
        <div class="article-btn">
          <b-button class="m-3" variant="primary"><router-link style="text-decoration:none; color:white;" :to="{ name: 'bookpage', params: { province: this.$route.params.province, city: this.$route.params.city, bookno: this.$route.params.bookno }}">글 목록으로</router-link></b-button>
          <div v-show="isAuthor">
            <b-button class="m-3" variant="primary" @click="modifyAction">수정</b-button>
            <b-button class="m-3" variant="danger" @click="deleteAction">삭제</b-button>
          </div>
        </div>
        <div class="article-comment">
          <div class="comments-header">
            <div class="comments-stats">
              <span class="m-3"><font-awesome-icon icon="comment" size="lg" class="mx-3"></font-awesome-icon>{{numOfComments}}</span>
            </div>
          </div>
          <hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
          <comments
            :comments_wrapper_classes="['custom-scrollbar', 'comments-wrapper']"
            :comments="comments"
            @commentnum="commentNum">
          </comments>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { faComment } from "@fortawesome/free-solid-svg-icons";
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
      score: 0,
      articleno:this.$route.params.articleno,
      numOfComments: 0,
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
    initMap() { 
      var container = document.getElementById('map');
      
      if(lats.length != 0){
      var latArr = lats.split(" ")
      var longArr = longs.split(" ")
      var latCen = ''
      var longCen = ''
      for(var i in latArr){
         latCen = Number(latCen) + Number(latArr[i])
         longCen = Number(longCen) + Number(longArr[i])
      }

      var placeOverlay = new kakao.maps.CustomOverlay({zIndex:1}), 
          contentNode = document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다 
          markers = [], // 마커를 담을 배열입니다
          currCategory = ''; // 현재 선택된 카테고리를 가지고 있을 변수입니다

      var options = { 
        center: new kakao.maps.LatLng(Number(latCen/(latArr.length-1)), Number(longCen/(longArr.length-1))), 
        level: 5
      }; 
      var map = new kakao.maps.Map(container, options); //마커추가하려면 객체를 아래와 같이 하나 만든다. 
      
      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places(map); 

      // 지도에 idle 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'idle', searchPlaces);

// 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다 
contentNode.className = 'placeinfo_wrap';

// 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
// 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다 
addEventHandle(contentNode, 'mousedown', kakao.maps.event.preventMap);
addEventHandle(contentNode, 'touchstart', kakao.maps.event.preventMap);

// 커스텀 오버레이 컨텐츠를 설정합니다
placeOverlay.setContent(contentNode);  

// 각 카테고리에 클릭 이벤트를 등록합니다
addCategoryClickEvent();

// 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
function addEventHandle(target, type, callback) {
    if (target.addEventListener) {
        target.addEventListener(type, callback);
    } else {
        target.attachEvent('on' + type, callback);
    }
}

// 카테고리 검색을 요청하는 함수입니다
function searchPlaces() {
    if (!currCategory) {
        return;
    }
    
    // 커스텀 오버레이를 숨깁니다 
    placeOverlay.setMap(null);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    ps.categorySearch(currCategory, placesSearchCB, {useMapBounds:true}); 
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        displayPlaces(data);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

    } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        
    }
}

// 지도에 마커를 표출하는 함수입니다
function displayPlaces(places) {

    // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
    // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
    var order = document.getElementById(currCategory).getAttribute('data-order');

    

    for ( var i=0; i<places.length; i++ ) {

            // 마커를 생성하고 지도에 표시합니다
            var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

            // 마커와 검색결과 항목을 클릭 했을 때
            // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
            (function(marker, place) {
                kakao.maps.event.addListener(marker, 'click', function() {
                    displayPlaceInfo(place);
                });
            })(marker, places[i]);
    }
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, order) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(27, 28),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(46, (order*36)), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(11, 28) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

// 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
function displayPlaceInfo (place) {
    var content = '<div class="placeinfo">' +
                    '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a>';   

    if (place.road_address_name) {
        content += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span>' +
                    '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span>';
    }  else {
        content += '    <span title="' + place.address_name + '">' + place.address_name + '</span>';
    }                
   
    content += '    <span class="tel">' + place.phone + '</span>' + 
                '</div>' + 
                '<div class="after"></div>';

    contentNode.innerHTML = content;
    placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
    placeOverlay.setMap(map);  
}


// 각 카테고리에 클릭 이벤트를 등록합니다
function addCategoryClickEvent() {
    var category = document.getElementById('category'),
        children = category.children;

    for (var i=0; i<children.length; i++) {
        children[i].onclick = onClickCategory;
    }
}

// 카테고리를 클릭했을 때 호출되는 함수입니다
function onClickCategory() {
    var id = this.id,
        className = this.className;

    placeOverlay.setMap(null);

    if (className === 'on') {
        currCategory = '';
        changeCategoryClass();
        removeMarker();
    } else {
        currCategory = id;
        changeCategoryClass(this);
        searchPlaces();
    }
}

// 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
function changeCategoryClass(el) {
    var category = document.getElementById('category'),
        children = category.children,
        i;

    for ( i=0; i<children.length; i++ ) {
        children[i].className = '';
    }

    if (el) {
        el.className = 'on';
    } 
    }

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
        }
      },

      addScript() { 
        const script = document.createElement('script'); 
        /* global kakao */ 
        script.onload = () => kakao.maps.load(this.initMap); 
        script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=06361ba6891dd71194432873935299c9&libraries=services'; 
        document.head.appendChild(script); 
        },

    commentNum: function(num) {
      this.numOfComments = num
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
      console.log(this.score)
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
  
  img {
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 5px;
  width: 10px;
}
</style>