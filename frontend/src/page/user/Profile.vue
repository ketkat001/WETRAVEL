<template>
  <div class="profile">
      <div class="user-information">
          <table>
              <tr>
                <td colspan="2">유저 정보</td>
              </tr>
              <tr>
                  <td>E-Mail</td>
                  <td>{{ this.$store.getters.getEmail }}</td>
              </tr>
              <tr>
                  <td>Nickname</td>
                  <td>{{ this.$store.getters.getNickname }}</td>
              </tr>
              <tr>
                  <td>Introduce</td>
                  <td>{{ this.$store.getters.getIntroduce }}</td>
              </tr>
          </table>
      </div>
      <div class="user-book-list">
          Book List
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
      <div style="clear: both">
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
.user-information {
    float: left;
    text-align: center;
}
.user-book-list {
    float: right;
    text-align: center;
}
.row-wrapper {
    display: table-cell;
    margin: auto;
}
</style>