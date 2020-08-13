<template>
  <div id="main" class="main">
    <div class="vertical-center">
      <div class="book-create">
        <h2>user 님의 여행기를 만들어 보세요!</h2>
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
                :options="provinces"
                required>
              </b-form-select>
              <b-form-select
                id="select-2"
                v-model="form.city"
                :options="cities"
                required>
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
            <b-button type="submit" variant="primary">Submit</b-button>
          </div>
        </b-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        title: "",
        province: null,
        city: null,
        startdate: "",
        description: ""
      },
      provinces: [],
      cities: [],
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
  }
  
}
</script>

<style scoped>
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