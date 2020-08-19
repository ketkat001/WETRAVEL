<template>
  <div class="comments">
		<div class="input-wrap">
			<input 
				class="input-comment" 
				type="text"
				v-model.trim="reply"
				maxlength="200" 
				required 
				placeholder="댓글을 입력해주세요"
				@keyup.enter="submitComment"/>
			<div class="input-btn">
				<b-button variant="primary" style="border-radius: 10px;" @click.prevent="submitComment">댓글 입력</b-button>
			</div>
		</div>
		<hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
		<div :class="comments_wrapper_classes">
			<singlecomment
				v-for="comment in comments"
				:comment="comment"
				:key="comment.id"
			>
			</singlecomment>
		</div>
  </div>
</template>

<script>
import singlecomment from '../comments/SingleComment.vue'

export default {
	name: 'comments',
	components: {
		singlecomment,
	},
	data() {
		return {
			reply: '',
			comments: []
		}
	},
	mounted: function() {
		this.getCommentList();
	},
	methods: {
		submitComment() {
/* 			if(this.reply != '') {
				this.$emit('submit-comment', this.reply)
				this.reply=''
			} */

		},
		getCommentList() {
			this.$axios.get(`/api/comment/${this.$route.params.articleno}`).then(res => {
				for (var i = 0; i < res.data.length; i++) {
					this.comments.push({commentno: res.data[i].commentno, writer: res.data[i].writer, writedate: res.data[i].writedate, text: res.data[i].text})
				}
			})
		}
	},
	props: ['comments_wrapper_classes']

}
</script>

<style scoped>
	.input-wrap {
    width: 100%;
    min-height: 60px;
    display: flex;
    justify-content: center;
    background-color:rgb(245, 245, 245);
    border-radius: 30px;

  }
  .input-comment {
    width: 70%;
    margin-right: 10px;
    background-color:rgb(245, 245, 245);
  }
  .input-btn {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
	.custom-scrollbar::-webkit-scrollbar-track {
		-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
		-moz-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
		box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
		border-radius: 10px;
		background-color: #fff;
	}
	.custom-scrollbar::-webkit-scrollbar {
		width: 8px;
		background-color: #fff;
	}
	.custom-scrollbar::-webkit-scrollbar-thumb {
		border-radius: 10px;
		-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
		-moz-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
		box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
		background-color: #555;
	}

</style>