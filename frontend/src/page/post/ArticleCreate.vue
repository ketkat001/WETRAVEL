<template>
  <div id="main" class="main">
    <div class="article-create container">
      <h2>글 작성</h2>
      <hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
      <quill-editor v-model="content"
        ref="myQuillEditor"
        :options="editorOption">
      </quill-editor>
      <b-button @click="createAction">작성완료</b-button>
    </div>
  </div>  
</template>

<script>
  export default {
    data () {
      return {
        content: '<h2>I am Example</h2>',
        editorOption: {
        }
      }
    },
    methods: {
      createAction() {
        var content2 = this.$refs.myQuillEditor.$options.propsData.value
        console.log(content2)
      }
    },
    
  }
</script>

<style>
  .article-create {
    margin-top: 120px;
  }
</style>