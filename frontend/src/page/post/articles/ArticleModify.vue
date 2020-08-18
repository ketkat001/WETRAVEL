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
      
      <vue-editor id="editor"
      use-custom-image-handler
      :editor-options="editorSettings"
      @image-added="handleImageAdded" @image-removed="handleImageRemoved" v-model="form.text" ref="myQuillEditor">
    </vue-editor>
        <b-button class="m-3" variant="primary" @click="createAction">수정완료</b-button>
      </b-form>
    </div>
  </div>  
</template>

<script>
import AWS from 'aws-sdk'
import { VueEditor, Quill } from 'vue2-editor'
import axios from 'axios';
  export default {
    name : 'vueeditor2',
    components : {
      VueEditor
    },
    data () {
      return {
        albumBucketName : 'article-album',
        bucketRegion : 'us-east-1',
        IdentityPoolId : 'us-east-1:c2eab5aa-fd1e-4281-841a-cab3a77056e5',
        file : null,
        photoKey : null,
        form: {
          bookno: this.$route.params.bookno,   //책 번호 url에서 받아서 bookno에 저장
          writedate:'',
          title:'',
          day:2,
          traveldate:'',
          text: '',
          articleno:this.$route.params.articleno
        },
        editorSettings: {
        }
      }
    },
    mounted(){
        this.$axios.get(`/api/article/${this.form.articleno}`, {
        headers: {'Content-Type': 'application/json'}
        }).then(res => {
        this.form.traveldate = res.data.traveldate
        this.form.writedate = res.data.writedate
        this.form.title = res.data.title
        this.form.day = res.data.day
        this.form.text = res.data.text
    })
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
        this.createHandler();
      },
      createHandler() {
      axios
        .put(`/api/article/${this.form.articleno}`, {
           articleno:this.form.articleno,
           bookno:this.form.bookno,
           writedate:this.form.writedate,
           title:this.form.title,
           day:this.form.day,
           traveldate:this.form.traveldate,
           text:this.form.text
        },{headers : {'Content-Type': 'application/json'}})
        .then(({ data }) => {
          let msg = '등록 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '수정이 완료되었습니다.';
          }
          alert(msg);
          //this.moveList();
        });
    },
    handleImageAdded(file, Editor, cursorLocation) {
      this.file = file
 
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      })

    let photoKey = this.file.name

    const s3 = new AWS.S3({
      apiVersion: "2006-03-01",
      params: { 
        Bucket: this.albumBucketName+'/1/1' // 하위 폴더 경로
      }
    })

    // Use S3 ManagedUpload class as it supports multipart uploads
      s3.upload({
        Key: photoKey,
        Body: this.file,
        ACL: "public-read",
        CacheControl: "no-cache",
        Expires: new Date()
        },(err) => {
          if(err){
            console.log(err)
            
          }
          var href = "https://s3.amazonaws.com/"; // 기본 주소
          var bucketUrl = href + this.albumBucketName + "/"; // 기본 주소 + 버킷이름
          var photoloc = "1/1/" + file.name;
          var photoUrl = bucketUrl + photoloc; // 최종 이미지 경로
          Editor.insertEmbed(cursorLocation,'image',photoUrl)
        });
      },

      handleImageRemoved(file, Editor, cursorLocation){
        
         AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      })

      let photoKey = this.file.name

      const s3 = new AWS.S3({
      apiVersion: "2006-03-01",
      params: { 
        Bucket: this.albumBucketName+'/1/1'
      }
    })

      s3.deleteObject({ Key: photoKey }, (err) => {
      if (err) {
    
      }
      
    });
      }
  }
}
</script>

<style scoped>
  .article-create {
    margin-top: 90px;
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