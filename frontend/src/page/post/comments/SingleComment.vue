<template>
  <div class="comment">
		<div class="comment-text">
			<b-link class="username" :to="{name: 'profile'}">{{ comment.writer }}</b-link>
			<span>{{ comment.text }}</span>
		</div>
		<b-button variant="primary" class="mx-1">수정</b-button>
		<b-button variant="danger" class="mx-1" @click="deleteComment">삭제</b-button>
	</div>
</template>

<script>
export default {
	name: 'singleComment',
	props: ['comment'],
	methods: {
		deleteComment () {
			
		}
	}
}
</script>

<style scoped>
	.comment {
		display: flex;
		justify-content: center;
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
	}
</style>