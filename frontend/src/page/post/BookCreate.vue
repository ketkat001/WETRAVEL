<template>
  <div id="main" class="main">
    <div class="book-create container">
      <h2>user 님의 여행기를 만들어 보세요!</h2>
      <hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-form-group
          id="input-group-1"
          label="썸네일 사진 등록"
          label-for="input-1"
          description="여행기의 대표 사진을 등록해주세요!">
          <b-form-file multiple :file-name-formatter="formatNames"></b-form-file>

        </b-form-group>
      </b-form> 
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {

      },
      show: true
    }
  },
  methods: {
    formatNames(files) {
      if (files.length === 1) {
        return files[0].name
      } else {
        return `${files.length} files selected`
      }
    }
  }
  
}
</script>

<style>
  .book-create {
    margin-top: 120px;
  }
</style>