<template>
  <div class="comment">
		<div class="comment-text row">
			<div class="col-3">
				<b-link class="username" :to="{name: 'profile'}">{{ comment.writer }}</b-link>
			</div>
			<span>{{ comment.text }}</span>
		</div>
		<b-button variant="primary" class="mx-1" v-show="username == comment.writer">수정</b-button>
		<b-button variant="danger" class="mx-1" v-show="username == comment.writer" @click="deleteComment">삭제</b-button>
	</div>
</template>

<script>
export default {
	name: 'singleComment',
	props: ['comment'],
	data() {
		return {
			username: ''
		}
	},
	async mounted() {
		if (sessionStorage.getItem('jwt-auth-token')) {
			await this.$store.dispatch('checkLogin').then(res => {
				this.username = res.nickname
			})
		}
	},
	methods: {
		async deleteComment () {
			  let res = confirm("정말로 삭제하시겠습니까?")
			  if (res == true) {
				await this.$axios.delete(`/api/comment/${this.comment.commentno}`).then(({data}) => {
					if (data === 'success') {
						alert('삭제가 완료되었습니다')
						this.$emit('changed')
					}
					else {
						alert('삭제 중 오류가 발생하였습니다')
					}
				})
			  }
		}
	}
}
</script>

<style scoped>
	.comment {
		display: flex;
		min-height: 40px;
		line-height: 40px;
		margin-bottom: 10px;
		background-color: rgb(245, 245, 245);
		width: 100%;
    	border-radius: 30px;
		box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
	}
	.comment-text {
		display: flex;
		flex-direction: column;
		justify-content: center;
		text-align: left;
		margin-left: 5px;
		width: 80%;
	}
	.comment-text span {
		margin-left: 5px;
	}
	.comment-text .username {
		font-weight: bold;
		color: black;
		text-decoration: none;
	}
</style>