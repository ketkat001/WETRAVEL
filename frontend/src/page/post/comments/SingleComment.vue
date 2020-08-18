<template>
  <div class="comment">
		<div class="comment-text">
			<b-link class="username" :to="{name: 'profile'}">{{ comment.user }}</b-link>
			<span>{{ comment.text }}</span>
			<b-button @click="deleteComment">삭제</b-button>
		</div>
	</div>
</template>

<script>
export default {
	name: 'singleComment',
	props: ['comment']
}
</script>

<style scoped>
	.comment {
		display: flex;
		margin-bottom: 10px;
		background-color: rgb(245, 245, 245);
		width: 100%;
    border-radius: 30px;
		box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
	}
	.comment-text {
		text-align: left;
		margin-left: 5px;
	}
	.comment-text span {
		margin-left: 5px;
	}
	.comment-text .username {
		font-weight: bold;
	}
</style>