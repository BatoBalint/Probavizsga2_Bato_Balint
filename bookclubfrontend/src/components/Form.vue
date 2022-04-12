<template>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-sm-12">
            <form @submit.prevent>
                <div class="mb-3">
                    <label class="form-label">Name</label>
                    <input type="text" class="form-control" v-model="nameInput">
                </div>
                <div class="mb-3">
                    <label class="form-label">Gender</label>
                    <input type="text" class="form-control" v-model="genderInput">
                </div>
                <div class="mb-3">
                    <label class="form-check-label">Birth Date</label>
                    <input type="date" class="form-control" v-model="birthDateInput">
                </div>
                <div>
                    <button class="btn btn-success" @click="submitBtnClick">Submit</button>
                </div>
            </form>  
        </div>
    </div>
</div>
</template>

<script>
  export default {
    data() {
      return {
        member: {},
        nameInput: "",
        genderInput: "",
        birthDateInput: ""
      }
    },
    methods: {
        submitBtnClick() {
            alert(this.nameInput + " , " + this.genderInput + " , " + this.birthDateInput);
        },
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
