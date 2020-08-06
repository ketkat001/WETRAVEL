<template>
    <div class="profile">
        <div class="container">    
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="well well-sm">
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <img src="http://placehold.it/150x150" alt="" class="img-rounded img-responsive" />
                            </div>
                            <div class="col-sm-6 col-md-8">
                                <h4>
                                    {{ nickname }}</h4>
                                <p>
                                    <i class="glyphicon glyphicon-envelope"></i>{{ email }}
                                    <br />
                                    {{ introduce }}
                                <!-- Split button -->
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary" @click="userUpdate">회원 정보 수정</button>
                                    <button type="button" class="btn btn-primary" @click="userDelete">회원 탈퇴</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <section class="py-5">
            <div class="container">
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
        </section>
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
        this.$store.dispatch('checkLogin').then(res => {
            this.email = res.email
            this.nickname = res.nickname
            this.introduce = res.introduce
            this.$axios.get(`/api/book/all/${this.email}`, {
                headers: {'Content-Type': 'application/json'}
            }).then(response => {
                for (var i = 0; i < response.data.length; i++) {
                    this.cards.push(response.data[i])
                }
            })
        })
    },
    methods: {
        userUpdate() {

        },
        userDelete() {
            
        }
    }
}
</script>

<style>
.content {
    width: 80%;
    margin: auto;
}
.row-wrapper {
    display: table-cell;
    margin: auto;
}
body{padding-top:30px;}

.glyphicon {  margin-bottom: 10px;margin-right: 10px;}

small {
display: block;
line-height: 1.428571429;
color: #999;
}
</style>