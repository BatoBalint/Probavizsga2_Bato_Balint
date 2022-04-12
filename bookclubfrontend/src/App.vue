<template>
<div class="container">
  <div class="row my-5">
    <h1>Members</h1>
  </div>
  <div class="row">
    <div v-for="member in members" :key="member.id" class="col-sm-12 col-md-6 col-lg-4 my-5">
      <div class="card text-dark">
        <img v-if="member.gender == 'M'" src="./assets/male.png" class="card-img-top" alt="male image">
        <img v-else-if="member.gender == 'F'" src="./assets/female.png" class="card-img-top" alt="female image">
        <img v-else src="./assets/other.png" class="card-img-top" alt="unidentified gender image">
        <div class="card-body">
          <h5 class="card-title">{{ member.name }}</h5>
          <p class="card-text">Text</p>
        </div>
      </div>
    </div>
  </div>
  <div class="row my-5">
    <h2>New member</h2>
    <Form />
  </div>
</div>
</template>

<script>
    import Form from './components/Form.vue'
  export default {
    components: {
      Form
    },
    data() {
      return {
        members: {}
      }
    },
    methods: {
      getData() {
        fetch('http://localhost:8000/api/member')
        .then(response => response.json())
        .then(data => this.members = data);
      }
    },
    mounted() {
      this.getData();
    }
  }
</script>

<style>

</style>
