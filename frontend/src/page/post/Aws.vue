<template>
  <div>
    <h1>파일 리스트</h1>
    <div v-for="(file, index) in fileList" :key="file.Key">
      #{{index + 1}}{{file.Key}}
      <button @click="deleteFile(file.Key)" color ="red" falt icon>x</button>
    </div>
    <h1> 파일 업로더 </h1>
    <input
      id="file-selector"
      ref="file"
      type="file"
      @change="handleFileUpload()"  multiple>
      <button @click="uploadFile" color="primary">업로드</button>

      <div v-for="p in photoList" :key="p">
        <p v-html=p></p>
      </div>
  </div>
</template>

<script>
import AWS from 'aws-sdk'

export default {
  data(){
    return{
      file:[],
      albumBucketName : 'article-album',
      bucketRegion : 'us-east-1',
      IdentityPoolId : 'us-east-1:c2eab5aa-fd1e-4281-841a-cab3a77056e5',
      fileList : [],
      photoList : [],
      photoKey : []
    }
  },  created(){
    this.getFiles()
  },
  methods:{
    handleFileUpload(){
      this.files = this.$refs.file.files
      //console.log(this.file)
      for (let index = 0; index < this.files.length; index++) {
        this.file[index] = this.$refs.file.files[index]
        console.log(this.file[index])
      }
      //console.log('파일 업로드')
    },

    uploadFile(){
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      })

    const s3 = new AWS.S3({
      apiVersion: "2006-03-01",
      params: { 
        Bucket: this.albumBucketName 
      }
    })

    //let photoKey = this.file.name;
    //console.log("photoKey"+photoKey)

    for (let index = 0; index < this.file.length; index++) {
      this.photoKey[index] = this.file[index].name
      console.log(this.photoKey[index])
    }
    
    // Use S3 ManagedUpload class as it supports multipart uploads
    for (let index = 0; index < this.photoKey.length; index++) {
      console.log(this.photoKey[index])
      s3.upload({
        Key: this.photoKey[index],
        Body: this.file[index],
        ACL: "public-read"
        },(err) => {
          if(err){
            console.log(err)
            return alert("실패",err.message);
          }
          alert('성공');
          this.getFiles()
        });
      }
    },

    getFiles(){
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      })

      const s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: { 
            Bucket: this.albumBucketName 
          }
      })
      
      s3.listObjects({ Delimiter: "/" },(err, data) =>{
        if (err) {
          return alert("There was an error listing your albums: " + err.message);
        } else {
          this.fileList = data.Contents
          console.log("길이:"+data.Contents.length)
          for (let index = 0; index < data.Contents.length; index++) {
            var href = "https://s3.amazonaws.com/"; // 기본 주소
            var bucketUrl = href + this.albumBucketName + "/"; // 기본 주소 + 버킷이름
            console.log("bucketUrl " + bucketUrl)
            var photoKey = data.Contents[index].Key; // +(폴더이름/) + 사진이름 
            console.log("photoKey " + photoKey)
            var photoUrl = bucketUrl + photoKey; // 최종 이미지 경로
            this.photoList[index] = "<img src="+'"'+photoUrl+'">' // src 형태로 리스트에 저장
            console.log("결과 : " + this.photoList[index])
          }
        }
      });
    },
    
    deleteFile(key){
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId
        })
      })
      const s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: { 
            Bucket: this.albumBucketName 
          }
      })

      s3.deleteObject({ Key: key }, (err) => {
      if (err) {
        return alert("There was an error deleting your photo: ", err.message);
      }
      alert("Successfully deleted photo.");
      this.getFiles()
    });
    },
  }
}

</script>