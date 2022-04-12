<template>
<div class="container">
  <div class="row my-5">
    <h1>Tagok</h1>
  </div>
  <div class="row">
    <div v-for="member in members" :key="member.id" class="col-sm-12 col-md-6 col-lg-4 my-5">
      <div class="card text-dark">
        <img v-if="member.gender == 'M'" src="./assets/male.png" class="card-img-top" alt="male image">
        <img v-else-if="member.gender == 'F'" src="./assets/female.png" class="card-img-top" alt="female image">
        <img v-else src="./assets/other.png" class="card-img-top" alt="unidentified gender image">
        <div class="card-body">
          <h5 class="card-title">{{ member.name }}</h5>
          <p class="card-text">Született: {{ member.birth_date }}</p>
          <p class="card-text">Csatlakozott: {{ member.created_at.split('.')[0].replace('T', ' ') }}</p>
        </div>
      </div>
    </div>
  </div>
  <div class="row my-5">
    <h2>Új tag</h2>
    <Form @formSent="getData" />
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
        fetch('http://localhost:8000/api/members')
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
