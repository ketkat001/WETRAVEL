<template>
  <div id="main" class="main">
    <div class="article-create container">
      <h2>글 작성</h2>
      <hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
      <input type="bookno" class="form-control" id="bookno" v-model="bookno" ref="bookno" placeholder="bookno">
    <div class="form-group">
      <label for="title">제목</label>
        <input type="text" class="form-control" id="title" v-model="title" ref="title" placeholder="제목을 입력하세요">
    </div>
    <input type="day" class="form-control" id="day" v-model="day" ref="day" placeholder="day">
    <div class="form-group">
      <label for="title">여행날짜</label>
        <input type="date" name="traveldate" id="traveldate" v-model="traveldate">
    </div>
    <div class="form-group">
      <label for="weather">날씨</label>
        <select name="weather" class="form-control" id="weather" ref="weather" v-model="weather">
          <option value=""></option>
          <option value="맑음">맑음</option>
          <option value="흐림">흐림</option>
          <option value="눈">눈</option>
          <option value="비">비</option>
        </select>
    </div>
      <quill-editor v-model="text" ref="myQuillEditor" :options="editorOption">
      </quill-editor>
      <b-button @click="createAction">작성완료</b-button>
    </div>
  </div>  
</template>

<script>
import axios from 'axios';
  export default {
    data () {
      return {
        bookno:'',
        writedate:'',
        title:'',
        day:'',
        traveldate:'',
        weather:'',
        text: '<h2>I am Example</h2>',
        editorOption: {
        }
      }
    },
    methods: {
      createAction() {
        var content2 = this.$refs.myQuillEditor.$options.propsData.value
        alert(content2);
        this.createHandler();
      },
      createHandler() {
      axios
        .post('http://localhost:8999/travel/api/article/article', {
           bookno:this.bookno,
           writedate:this.writedate,
           title:this.title,
           day:this.day,
           traveldate:this.traveldate,
           weather:this.weather,
           text:this.text
        })
        .then(({ data }) => {
          let msg = '등록 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '등록이 완료되었습니다.';
          }
          alert(msg);
          //this.moveList();
        });
    }
  }
}
</script>

<style>
  .article-create {
    margin-top: 120px;
  }
</style>