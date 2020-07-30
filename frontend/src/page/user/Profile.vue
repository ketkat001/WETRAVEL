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
                                    {{ this.$store.getters.getNickname }}</h4>
                                <p>
                                    <i class="glyphicon glyphicon-envelope"></i>{{ this.$store.getters.getEmail }}
                                    <br />
                                    {{ this.$store.getters.getIntroduce }}
                                <!-- Split button -->
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary">
                                        Social</button>
                                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span><span class="sr-only">Social</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">Twitter</a></li>
                                        <li><a href="https://plus.google.com/+Jquery2dotnet/posts">Google +</a></li>
                                        <li><a href="https://www.facebook.com/jquery2dotnet">Facebook</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Github</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="row-wrapper">
                            <div class="row">
                                <div class="col-3">
                                <b-card
                                    img-src="https://picsum.photos/600/300/"
                                    style="max-width: 20rem;"
                                    class="mb-2"
                                >
                                    <b-card-text>서울, 대한민국의 수도</b-card-text>
                                </b-card>
                                </div>
                                <div class="col-3">
                                <b-card
                                    img-src="https://picsum.photos/600/300/"
                                    style="max-width: 20rem;"
                                    class="mb-2"
                                >
                                    <b-card-text>경기, 활력 넘치는 도시</b-card-text>
                                </b-card>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-3">
                                <b-card
                                    img-src="https://picsum.photos/600/300/"
                                    style="max-width: 20rem;"
                                    class="mb-2"
                                >
                                    <b-card-text>전라, 맛의 중심</b-card-text>
                                </b-card>
                                </div>
                                <div class="col-3">
                                <b-card
                                    img-src="https://picsum.photos/600/300/"
                                    style="max-width: 20rem;"
                                    class="mb-2"
                                >
                                    <b-card-text>경상, 바다의 도시</b-card-text>
                                </b-card>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
export default {
    name: "profile",
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
</style>