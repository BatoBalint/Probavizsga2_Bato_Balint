<template>
<div class="container-liquid p-0">
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <span class="navbar-brand fw-bold">Petrik Könyvklub</span>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link text-primary" aria-current="page" href="#ujTag">Új tag felvétele</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-primary" target="_blank" href="https://petrik.hu/">Petrik honlap</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="container">
    <div class="row my-5">
      <h1>Tagok</h1>
    </div>
    <div class="row">
      <div v-for="(member, index) in members" :key="member.id" class="col-sm-12 col-md-6 col-lg-4 my-5">
        <div class="card text-dark">
          <img v-if="member.gender == 'M'" src="./assets/male.png" class="card-img-top" alt="male image">
          <img v-else-if="member.gender == 'F'" src="./assets/female.png" class="card-img-top" alt="female image">
          <img v-else src="./assets/other.png" class="card-img-top" alt="unidentified gender image">
          <div class="card-body">
            <h5 class="card-title">{{ member.name }}</h5>
            <p class="card-text">Született: {{ member.birth_date }}</p>
            <p class="card-text">Csatlakozott: {{ member.created_at.split('.')[0].replace('T', ' ') }}</p>
            <button class="btn btn-primary" @click="payBtnClick(index)">Befizetés</button>
            <p :class="{hide: membersCardMessageVisible[index]}">{{ membersCardMessage[index] }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="row my-5">
      <h2>Új tag</h2>
      <Form id="ujTag" @formSent="getData" />
    </div>
  </div>
  <div class="row bg-secondary p-5">
    <h5 class="text-dark fb-bold">Készítette: Bató Bálint</h5>
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
        members: {},
        membersCardMessageVisible: [],
        membersCardMessage: [],
        paymentResponse: {}
      }
    },
    methods: {
      getData() {
        fetch('http://localhost:8000/api/members')
        .then(response => response.json())
        .then(data => {
          this.members = data;
          this.membersCardMessage = Array(this.members.length);
          this.membersCardMessageVisible.fill(true);
        });
      },
      pay(memberIndex) {
        let id = this.members[memberIndex].id;
        fetch('http://localhost:8000/api/members/' + id + '/pay', {
          method: "POST"
        })
        .then(response => response.json())
        .then(data => {
          this.paymentResponse = data;
          if (this.paymentResponse.message == undefined) {
            this.membersCardMessage[memberIndex] = "Sikeres befizetés";
          } else {
            this.membersCardMessage[memberIndex] = this.paymentResponse.message;
            // alert(this.paymentResponse.message);
          }
          this.membersCardMessageVisible[memberIndex] = false;
          });
      },
      payBtnClick(memberIndex) {
        this.pay(memberIndex);
      }
    },
    mounted() {
      document.title = "Petrik Könyvklub"
      this.getData();
    }
  }
</script>

<style>
.hide {
  display: none;
}

.green {
  color: green;
}

.red {
  color: red;
}
</style>
