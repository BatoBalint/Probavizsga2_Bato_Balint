<template>
<div class="container">
  <div class="row my-5">
    <h1>Members</h1>
  </div>
  <div class="row">
    <div class="col-sm-3">
      <div class="card" style="width: 18rem;">
        <img src="" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">Card title</h5>
          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
          <a href="#" class="btn btn-primary">Go somewhere</a>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
  export default {
    data() {
      return {
        members: {}
      }
    },
    methods: {
      getData() {
        fetch('http://localhost:8000/api/member')
        .then(response => response.json())
        .then(data => members = data);
      }
    },
    mounted() {
      this.getData();
    }
  }
</script>

<style>

</style>
