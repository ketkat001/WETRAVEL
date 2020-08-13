<template>
  <div id="vueeditor2">
    <h1>Vue2Editor - Images Upload</h1>
    <h2>Upload an image to server and use response to get url and use that for editor content instead of defualt Base64 that Quill.js provides</h2>
    <p>This example uses the imgur API, and uses my account to upload photos.</p>
    <p>I plan to merge this feature soon but am making it available for testing and feedback.</p>

    <h3>To test out this version in your project:</h3>
<pre>
  <code class="bash">yarn add vue2-editor@images</code>
</pre>
<strong>OR</strong>
<pre>
  <code class="bash">npm install vue2-editor@images</code>
</pre>
    <vue-editor id="editor"
      use-custom-image-handler
      :editor-options="editorSettings"
      @image-added="handleImageAdded" @image-removed="handleImageRemoved" v-model="editorContent">
    </vue-editor>
  </div>
</template>

<script>
import AWS from 'aws-sdk'
import EXIF from 'exif-js'
import { VueEditor, Quill } from 'vue2-editor'
import axios from 'axios'
export default {
  name: 'vueeditor2',
  components: {
    VueEditor
  },
  data () {
    return {
      editorContent: 'Initial Content',


      albumBucketName : 'article-album',
      bucketRegion : 'us-east-1',
      IdentityPoolId : 'us-east-1:c2eab5aa-fd1e-4281-841a-cab3a77056e5',

      file : null,
      photoKey : null,
      
      exifLong : '',
      exifLat : '',
      exifLongRef : '',
      exifLatRef : '',
      exifTime : '',

      editorSettings: {

      }
    }
  },
  methods: {


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
                    if (this.exifLatRef == "S") {
                        var latitude = (this.exifLat[0]*-1) + (( (this.exifLat[1]*-60) + (this.exifLat[2]*-1) ) / 3600);						
                    } else {
                        var latitude = this.exifLat[0] + (( (this.exifLat[1]*60) + this.exifLat[2] ) / 3600);
                    }
                    console.log("위도 : " + latitude);
                    if (this.exifLongRef == "W") {
                        var longitude = (this.exifLong[0]*-1) + (( (this.exifLong[1]*-60) + (this.exifLong[2]*-1) ) / 3600);						
                    } else {
                        var longitude = this.exifLong[0] + (( (this.exifLong[1]*60) + this.exifLong[2] ) / 3600);
                    } 
                    console.log("경도 : " + longitude);
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
        Bucket: this.albumBucketName+'/1/1'
      }
    })

    // Use S3 ManagedUpload class as it supports multipart uploads
      s3.upload({
        Key: photoKey,
        Body: this.file,
        ACL: "public-read"
        },(err) => {
          if(err){
            console.log(err)
            return alert("실패",err.message);
          }
          var href = "https://s3.amazonaws.com/"; // 기본 주소
          var bucketUrl = href + this.albumBucketName + "/"; // 기본 주소 + 버킷이름
          var photoloc = "1/1/" + file.name;
          console.log("사진경로이름:"+photoloc)
          var photoUrl = bucketUrl + photoloc; // 최종 이미지 경로
          console.log("최종경로:"+photoUrl)
          Editor.insertEmbed(cursorLocation,'image',photoUrl)
          alert('성공');
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
        return alert("There was an error deleting your photo: ", err.message);
      }
      alert("Successfully deleted photo.");
    });


      }
    }
  }
</script>

<style scoped>
@import "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.12.0/styles/default.min.css";
#editor {
  height: 400px;
}
#vueeditor2 {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /*margin-top: 60px;*/
  max-width: 800px;
  margin-left: auto;
  margin-right: auto;
  padding-bottom: 3em;
}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>