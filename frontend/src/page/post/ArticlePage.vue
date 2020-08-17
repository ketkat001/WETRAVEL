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
            <h5>Day 1</h5>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="article-content">
        <div class="article-map">
          <p v-html="text"></p>
        </div>
        <div class="article-btn" style="float: right;">
          <b-button class="m-3" variant="primary" @click="modifyAction">수정</b-button>
          <b-button class="m-3" variant="primary" @click="deleteAction">삭제</b-button>
        </div>
      </div>
      <div class="article-footer">
        <star-rating :increment="0.5"
          :max-rating="5"
          active-color="#007bff"
          :star-size="25">
        </star-rating>
        <div class="article-comment">
          <div class="comments-header">
            <div class="comments-stats">
              <span class="m-3"><font-awesome-icon icon="thumbs-up" size="lg" class="mx-3"></font-awesome-icon>1</span>
              <span class="m-3"><font-awesome-icon icon="comment" size="lg" class="mx-3"></font-awesome-icon>2</span>
            </div>
          </div>
          <hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
          <div class="input-wrap">
            <input class="input-comment" type="text" maxlength="200" required placeholder="댓글을 입력해주세요"/>
            <div class="input-btn">
             <b-button variant="primary" style="border-radius: 10px;">댓글 입력</b-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { faThumbsUp as thumbsUp_SolidIcon, faComment } from "@fortawesome/free-solid-svg-icons";

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
  },
  methods : {
    deleteAction(){
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
          //this.moveList();
        });
    },
    modifyAction(){
      this.$router.push({name : 'articlemodify'})
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
  .article-content .article-map {
    min-height: 300px;
  }
  .article-footer {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin : 30px;
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
  .input-wrap {
    width: 100%;
    min-height: 60px;
    display: flex;
    justify-content: center;
    background-color:rgb(245, 245, 245);
    border-radius: 30px;

  }
  .input-comment {
    width: 70%;
    margin-right: 10px;
    background-color:rgb(245, 245, 245);
  }
  .input-btn {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
</style>