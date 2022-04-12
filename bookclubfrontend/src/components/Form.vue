<template>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-sm-12">
            <form @submit.prevent>
                <div class="mb-3">
                    <label class="form-label">Név</label>
                    <input type="text" class="form-control" placeholder="Kiss Gergő" v-model="nameInput">
                </div>
                <div class="mb-3">
                    <label class="form-label">Nem</label>
                    <input type="text" class="form-control" placeholder="M, F vagy egyéb" v-model="genderInput">
                </div>
                <div class="mb-3">
                    <label class="form-check-label">Születési dátum</label>
                    <input type="date" class="form-control" v-model="birthDateInput">
                </div>
                <div>
                    <button class="btn btn-success" @click="submitBtnClick">Küldés</button>
                </div>
            </form>  
        </div>
    </div>
</div>
</template>

<script>
  export default {
    emits: ['formSent'],
    data() {
      return {
        nameInput: "",
        genderInput: "",
        birthDateInput: ""
      }
    },
    methods: {
        submitBtnClick() {
            let error = this.checkInput(); 
            if (error != "") {
                alert(error);
            } else {
                let gender = this.genderInput.trim().toUpperCase();
                if (gender != 'M' || gender != "F") {
                    gender = null;
                }
                let member = {
                    name: this.nameInput.trim(),
                    gender: this.genderInput.trim().toUpperCase(),
                    birth_date: this.birthDateInput.trim(),
                    banned: 0
                };
                this.sendData(member);
            }
        },
        sendData(member) {
            fetch('http://localhost:8000/api/member', {
                method: "POST",
                headers: {
                    "Content-Type" : "application/json"
                },
                body: JSON.stringify(member)
            })
            .then(response => response)
            .then(data => {
                console.log(data);
                this.$emit('formSent');
            });
        },
        checkInput() {
            let error = "";
            if (this.nameInput.trim() == "") {
                error += "The name must not be empty\n";
            }
            if (this.genderInput.trim() == "") {
                error += "The gender input must not be empty\n";
            }
            if (this.birthDateInput.trim() == "") {
                error += "The birth date input must not be empty";
            }
            return error;
        }
    }
  }
</script>

<style>

</style>
