<template>
  <div id="imagetest">
      <img src="https://user-images.githubusercontent.com/4029349/27200620-0ccfd1aa-51e0-11e7-9e11-26f304149e92.png">
  </div>
</template>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script>
import $ from 'jquery'
export default {
    mounted: function() {
        var img = $('#imagetest').find('img:first').attr('src')
        console.log(img)
    }
}
</script>

<style>

</style>