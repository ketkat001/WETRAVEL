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
      </div>
      <div class="article-footer">
        <div class="article-btn">
          <b-button class="m-3" variant="primary" @click="modifyAction">수정</b-button>
          <b-button class="m-3" variant="primary" @click="deleteAction">삭제</b-button>
        </div>
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

export default {
  components: {
    comments
  },
  data(){
    return {
      bookno: this.$route.params.bookno,   //책 번호 url에서 받아서 bookno에 저장
      writedate:'',
      title:'',
      day:'',
      traveldate:'',
      text: '',
      articleno:this.$route.params.articleno,
      comments: []
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
    submitComment: function(reply) {
      this.comments.push({
        id: this.comments.length + 1,
				user: '',
        text: reply
      })
    },
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
  
</style>