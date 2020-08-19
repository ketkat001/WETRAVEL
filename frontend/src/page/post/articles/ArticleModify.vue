<template>
  <div id="main" class="main">
    <div class="article-create container">
      <b-form>
        <div class="article-info">
          <div class="d-flex row">
            <b-form-group
              class="col-7 p-0"
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
              class="col-5 pr-0"
              id="input-group-4"
              label="회차"
              label-for="select-1"
              description="여행기의 회차를 선택해주세요!">
              <b-form-select
                id="select-1"
                v-model="form.day"
                :options="dayList"
                required>
              </b-form-select>
            </b-form-group>
          </div>
          <b-form-group
            id="input-group-1"
            label="썸네일 사진 등록"
            label-for="input-1"
            description="대표 사진을 등록해주세요!">
            <b-form-file 
              multiple :file-name-formatter="formatNames"
              v-model="thumbnail"
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
      @image-added="handleImageAdded" @image-removed="handleImageRemoved" v-model="editorContent" ref="myQuillEditor">
    </vue-editor>
        <b-button class="m-3" variant="primary" @click="createAction">작성완료</b-button>
      </b-form>
    </div>
  </div>  
</template>

<script>
import AWS from 'aws-sdk'
import EXIF from 'exif-js'
import { VueEditor, Quill } from 'vue2-editor'
import axios from 'axios';
let lat = new Set()
let long = new Set()
let lats = ''
let longs = ''
  export default {
    name : 'vueeditor2',
    components : {
      VueEditor
    },
    data () {
      return {
        editorContent: '',
        albumBucketName : 'article-album',
        bucketRegion : 'us-east-1',
        IdentityPoolId : 'us-east-1:c2eab5aa-fd1e-4281-841a-cab3a77056e5',
        file : null,
        photoKey : null,
        thumbnail: null, 
        dayList: [
          {value: '1', text: '1화'},
          {value: '2', text: '2화'},
          {value: '3', text: '3화'},
          {value: '4', text: '4화'},
          {value: '5', text: '5화'},
          {value: '6', text: '6화'},
          {value: '7', text: '7화'},
          {value: '8', text: '8화'},
          {value: '9', text: '9화'},
          ],
        exifLat : '',
        exifLong : '',
        form: {
          bookno: this.$route.params.bookno,   //책 번호 url에서 받아서 bookno에 저장
          writedate:'',
          title:'',
          day:0,
          traveldate:'',
          text: '',
          articleno:this.$route.params.articleno
        },
        editorSettings: {
        }
      }
    },
    async mounted(){
        await this.$axios.get(`/api/article/${this.form.articleno}`, {
        headers: {'Content-Type': 'application/json'}
        }).then(res => {
        this.form.traveldate = res.data.traveldate
        this.form.writedate = res.data.writedate
        this.form.title = res.data.title
        this.form.day = res.data.day
        this.editorContent = res.data.text
        this.thumbnail = res.data.img != null ? this.dataURLtoFile('data:image/jpg;base64,' + res.data.img, 'original.jpg') : null
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
      dataURLtoFile(dataurl, fileName) {
        var arr = dataurl.split(','),
            mime = arr[0].match(/:(.*?);/)[1],
            bstr = atob(arr[1]),
            n = bstr.length,
            u8arr = new Uint8Array(n);

            while (n--) {
              u8arr[n] = bstr.charCodeAt(n);
            }

            return new File([u8arr], fileName, {type:mime});
      },
      createAction() {  
        this.createHandler();
      },
      createHandler() {
        lat.forEach(v => lats += v + " ")
        long.forEach(v => longs += v + " ")
        let formData = new FormData()
        formData.append('bookno', this.form.bookno)
        formData.append('articleno', this.form.articleno)
        formData.append('title', this.form.title)
        formData.append('day', this.form.day)
        formData.append('traveldate', this.form.traveldate)
        formData.append('text', this.editorContent)
        formData.append('thumbnail', this.thumbnail != null ? this.thumbnail : new File([""], ""))
        console.log(this.thumbnail)
        formData.append('exifLat', lats)
        console.log(lats)
        formData.append('exifLong', longs)
        console.log(longs)
      axios
        .put('/api/article/article', formData,
        {
          headers: {'Content-Type': 'multipart/form-data'}
        })
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

      EXIF.getData(this.file, function() {
        console.log('image info', this)
        console.log('exif data', this.exifdata)

        this.exifLong = EXIF.getTag(this, "GPSLongitude");
        console.log(this.exifLong)
        this.exifLat = EXIF.getTag(this, "GPSLatitude");
        this.exifLongRef = EXIF.getTag(this, "GPSLongitudeRef");
        this.exifLatRef = EXIF.getTag(this, "GPSLatitudeRef");
        // this.exifTime = EXIF.getTag(this, "DateTime");
        //계산식 적용이유는 해당라이브러리가 위도경도를 도분초 단위로 출력하기 때문
        if(this.exifLatRef != undefined && this.exifLongRef != undefined){
          if (this.exifLatRef == "S") {
              var latitude = (this.exifLat[0]*-1) + (( (this.exifLat[1]*-60) + (this.exifLat[2]*-1) ) / 3600);						
          } else {
            var latitude = this.exifLat[0] + (( (this.exifLat[1]*60) + this.exifLat[2] ) / 3600);
          }
            //console.log("위도 : " + latitude);
            lat.add(latitude)
          if (this.exifLongRef == "W") {
            var longitude = (this.exifLong[0]*-1) + (( (this.exifLong[1]*-60) + (this.exifLong[2]*-1) ) / 3600);						
          } else {
            var longitude = this.exifLong[0] + (( (this.exifLong[1]*60) + this.exifLong[2] ) / 3600);
          } 
          //console.log("경도 : " + longitude);
          long.add(longitude)
        }

      this.exifLat = lat
      console.log(this.exifLat)
      this.exifLong = long
      console.log(this.exifLong)

    })

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
        Bucket: this.albumBucketName
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
          var bucketUrl = href + this.albumBucketName + '/'; // 기본 주소 + 버킷이름
          var photoloc = file.name;
          console.log("사진경로이름:"+photoloc)
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

<style>
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
