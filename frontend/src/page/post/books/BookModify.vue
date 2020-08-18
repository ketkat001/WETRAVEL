<template>
  <div id="main" class="main">
    <div class="vertical-center">
      <div class="book-create">
        <h2>{{ form.userName }} 님의 여행기를 만들어 보세요!</h2>
        <hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
        <b-form>
          <b-form-group
            id="input-group-0"
            label="여행기 제목"
            label-for="input-0"
            description="여행기의 제목을 입력해주세요!">
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
            description="여행기의 대표 사진을 등록해주세요!">
            <b-form-file 
              multiple :file-name-formatter="formatNames"
              v-model="thumbnail"
              placeholder="파일 없음"></b-form-file>
          </b-form-group>
          <b-form-group
            id="input-group-2"
            label="지역 선택"
            label-for="input-2"
            description="여행 지역을 선택해주세요">
            <div class="d-flex">
              <b-form-select
                id="select-1"
                v-model="form.province"
                 @change="cityList"
                required>
                <option selected value="">지역 선택</option> 
                <option v-for="(province, index) in this.$store.getters.getProvinceList" :key="index" :value="province">{{ province }}</option>
              </b-form-select>
              <b-form-select
                id="select-2"
                v-model="form.city"
                :disabled="form.province == ''"
                required>
                <option selected value="">도시 선택</option> 
                <option v-for="(city, index) in replaceCityList" :key="index" :value="city">{{ city }}</option>
              </b-form-select>
            </div>
          </b-form-group>
          <b-form-group
            id="input-group-3"
            label="시작 날짜 선택"
            label-for="input-3"
            description="시작 날짜를 선택해주세요!">
            <b-form-datepicker id="example-datepicker" v-model="form.startdate" class="mb-2"></b-form-datepicker>
          </b-form-group>
          <b-form-group
            id="input-group-4"
            label="기행문 소개"
            label-for="input-4"
            description="기행문을 짧게 소개해주세요!">
            <b-form-textarea
              id="input-4"
              v-model="form.description"
              required
              placeholder="기행문 소개를 입력해주세요!">
            </b-form-textarea>
          </b-form-group>
          <div class="text-center">
            <b-button type="submit" variant="primary" @click="createAction">Submit</b-button>
          </div>
        </b-form>
      </div>
    </div>
  </div>
</template>

<script>
import AWS from 'aws-sdk'

export default {
  data() {
    return {
      editorContent: 'Initial Content',
        albumBucketName : 'article-album',
        bucketRegion : 'us-east-1',
        IdentityPoolId : 'us-east-1:c2eab5aa-fd1e-4281-841a-cab3a77056e5',
        thumbnail: null,
      form: {
        title: "",
        userName: '',
        province: '',
        city: '',
        startdate: "",
        description: "",
        bookno: this.$route.params.bookno
      }
    }
  },
  computed: {
    replaceCityList() {
      return this.$store.getters.getCityList
    }
  },
  async mounted() {
    await this.$store.dispatch('checkLogin').then(res => {
      this.form.userName = res.nickname
    })
    await this.$axios.get(`/api/book/${this.form.bookno}`).then(res => {
      this.form.title = res.data.title
      this.form.province = res.data.province
      this.cityList()
      this.form.city = res.data.city
      this.form.startdate = res.data.startdate
      this.form.description = res.data.description
      this.thumbnail = this.dataURLtoFile('data:image/jpg;base64,' + res.data.img, 'original.jpg')
    })
  },
  methods: {
    cityList: async function() {
      if (this.form.province == '') {
        this.form.city = ''
      }
      else {
        await this.$store.dispatch('getCityList', this.form.province)
      }
    },
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
    createAction(){
      let formData = new FormData() 
      formData.append('bookno', this.form.bookno)
      formData.append('title', this.form.title)
      formData.append('writer', this.form.userName)
      formData.append('province', this.form.province)
      formData.append('city', this.form.city)
      formData.append('startdate', this.form.startdate)
      formData.append('description', this.form.description)
      formData.append('thumbnail', this.thumbnail != null ? this.thumbnail : new File([""], ""))
      this.$axios.put(`/api/book/${this.form.bookno}`, formData, {
        headers: {'Content-Type': 'multipart/form-data'}
      })
      .then(({ data }) => {
        let msg = '등록 처리시 문제가 발생했습니다.';
        if (data === 'success') {
          msg = '등록이 완료되었습니다.';
        }
        alert(msg);
      });
    }
  }
}
</script>

<style>
  .vertical-center {
    display: flex;
    text-align: left;
    justify-content: center;
    flex-direction: column;    
  }
  .book-create {
    width: 600px;
    margin: 0 auto;
    margin-top: 30px;
    margin-bottom: 30px;
    background: #ffffff;
    box-shadow: 0px 14px 80px rgba(34, 35, 58, 0.2);
    padding: 40px 40px 40px 40px;
    border-radius: 15px;
    transition: all .3s;
  }
  

</style>