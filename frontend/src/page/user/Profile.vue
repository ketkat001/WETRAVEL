<template>
  <div id="main" class="main">
    <div class="profile container">
      <div class="user-info">
        <div class="my-back-img">
          <img src="">
          <div class="bg-cover">
          </div>
        </div>
        <div class="u-info row">
          <div class="col-4">
            <div class="user-img">
              <a>
                <img src="http://placehold.it/100x100">
                <div class="user-img-cover">
                </div>
              </a>
            </div>
          </div>
          <div class="follow-btn">
            <b-button variant="primary">Follow</b-button>
          </div>
        </div>
      </div>
      <div class="profile-info row">
        <div class="col-4">
          <div class="user-profile">
            <h4>{{ nickname }}</h4>
            <hr>
            <p><i class="glyphicon glyphicon-envelope"></i>{{ email }}</p>
            <p>{{ introduce }}</p>
          </div>
        </div>
        <div class="col-6">
          <div class="follow-info">
          </div>
        </div>
        <div class="col-2 p-0">
          <div class="user-btn">
            <div>
              <b-button class="my-3" variant="primary"><router-link style="text-decoration:none" to="/userupdate">회원 정보 수정</router-link></b-button>
            </div>
            <div>
              <b-button class="my-3" variant="primary" style="float:right"><router-link style="text-decoration:none" to="/userdelete">회원 탈퇴</router-link></b-button>
            </div>
          </div>
        </div>
      </div>
      <hr style="border: 1px solid rgb(196, 195, 208); margin-bottom: 30px;">
      <h2 class="mb-5 text-center">나의 여행기</h2>
      <div class="card-deck">
        <div v-for="(card, index) in cards" :key="index" :ref="`card_${index}`" class="card border-0">
          <b-link :to="{name: 'bookpage', params: { province: card.province, city: card.city, bookno: card.bookno }}">
            <a href="#"><img class="card-img-top mb-2" :src="card.image" alt=""></a>
            <div class="card-body">
              <small class="text-muted">{{ card.city }}</small>
              <h5 class="card-title my-2"><a href="#">{{ card.title }}</a></h5>
            </div>
          </b-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "profile",
    data: function() {
        return {
            email: '',
            nickname: '',
            introduce: '',
            cards: []
        }
    },
    mounted: function() {
      this.getUserInfo()
    },
    methods: {
      getUserInfo: async function() {
        await this.$store.dispatch('checkLogin').then(res => {
            this.email = res.email
            this.nickname = res.nickname
            this.introduce = res.introduce
            this.$axios.get(`/api/book/all/${this.nickname}`, {
                headers: {'Content-Type': 'application/json'}
            }).then(response => {
                for (var i = 0; i < response.data.length; i++) {
                    this.cards.push(response.data[i])
                }
            })
        })
      }
    }
}
</script>

<style scoped>
.profile {
  width: 960px;
  padding: 0;
  margin-top: 60px;
}
.user-info {
  position: relative;
  height: 330px;
  margin-bottom: 30px;
}
.user-info .my-back-img{
  height: 270px;
  position: relative;
  overflow: hidden;
}
.user-info .my-back-img img {
  width: 960px;
  position: absolute;
  top: -62.5px;
}
.user-info .bg-cover {
  position: absolute;
  top: 0;
  width: 960px;
  height: 270px;
  background: url("http://placehold.it/960x270");
}
.user-info .u-info {
  width: 100%;
  position: absolute;
  top: 220px;
}
.user-img a {
  display: inline-block;
  width: 110px;
  height: 110px;
  position: relative;
}
.user-img a img {
  width: 100px;
  height: 100px;
  border-radius: 100px;
  margin-left: 5px;
}
.user-img a .user-img-cover {
  width: 100px;
  height: 100px;
  border-radius: 100px;
  margin-left: 5px;
  display: block;
  position: absolute;
  top: 0;
  background: url("http://placehold.it/100x100")
}
.follow-btn {
  position: relative;
  top: 60px;
  left: 560px;
}
.user-btn {
  float: right;
}
</style>