<template>
  <div id="main" class="main">
    <div class="article-create container">
      <b-form>
        <div class="article-info">
          <b-form-group
            id="input-group-0"
            label="제목"
            label-for="input-0"
            description="제목을 입력해주세요!">
            <b-form-input
              id="input-0"
              v-model="form.title"  
              required
              placeholder="제목 입력">
            </b-form-input>
          </b-form-group>
          <b-form-group
            id="input-group-1"
            label="썸네일 사진 등록"
            label-for="input-1"
            description="대표 사진을 등록해주세요!">
            <b-form-file 
              multiple :file-name-formatter="formatNames"
              placeholder="파일 없음"></b-form-file>
          </b-form-group>
          <b-form-group
            id="input-group-3"
            label="날짜 선택"
            label-for="input-3"
            description="날짜를 선택해주세요!">
            <b-form-datepicker id="example-datepicker" v-model="form.traveldate" class="mb-2"></b-form-datepicker>
          </b-form-group>
        </div>
        <hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
      
        <quill-editor v-model="form.text" ref="myQuillEditor" :options="editorOption">
        </quill-editor>
        <b-button class="m-3" variant="primary" @click="createAction">작성완료</b-button>
      </b-form>
    </div>
  </div>  
</template>

<script>
import axios from 'axios';
  export default {
    data () {
      return {
        form: {
          bookno: this.$route.params.bookno,   //책 번호 url에서 받아서 bookno에 저장
          writedate:'',
          title:'',
          day:'',
          traveldate:'',
          weather:'',
          text: '',
         
        },
        editorOption: {
        }
      }
    },
    methods: {
      formatNames(files) {
        if (files.length === 1) {
          return files[0].name
        } else {
          return `${files.length} files selected`
        }
      },
      createAction() {
        var content2 = this.$refs.myQuillEditor.$options.propsData.value
        alert(content2);
        console.log(this.form.bookno)
        this.createHandler();
      },
      createHandler() {
      axios
        .post('http://localhost:8999/travel/api/article/article', {
           bookno:this.form.bookno,
           writedate:this.form.writedate,
           title:this.form.title,
           day:this.form.day,
           traveldate:this.form.traveldate,
           weather:this.form.weather,
           text:this.form.text
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
    margin-top: 60px;
    margin-bottom: 60px;
  }
  .article-info {
    width: 600px;
    margin: auto;
    text-align: left;
    margin-bottom: 30px;
    box-shadow: 0px 14px 80px rgba(34, 35, 58, 0.2);
    padding: 40px 40px 40px 40px;
    border-radius: 15px;
    transition: all .3s;
  }
</style>