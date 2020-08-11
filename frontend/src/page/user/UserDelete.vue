<template>
    <div class="vertical-center">
        <div class="inner-block">
            <form action="">
                <h5>회원 탈퇴한 후에는 모든 정보는 복구되지 않습니다. </h5>
                <h5>확인을 위해, 다시 한 번 비밀번호를 입력하여 주십시오. </h5>
                <div class="form-group">
                    <input 
                        v-model="password" 
                        name="password" 
                        :type="passwordType" 
                        placeholder="비밀번호를 입력해주세요" 
                        class="form-control"
                        ref="password"
                        v-validate="'required|min:8|verify_password'"
                    />
                    <div class="alert alert-danger" v-if="errors.has('password')">
                        {{ errors.first('password') }}
                    </div>
                </div>
                <button type="submit" @click="backsubmit" class="btn btn-dark btn-lg btn-block">회원 탈퇴</button>
            </form>
        </div>
    </div>
</template>

<script>
export default {
    name: 'userdelete',
    methods: {
        backsubmit(event) {
            event.preventDefault();
            if (this.password == "") {
                alert('패스워드를 입력해 주세요')
                return
            }
            this.$validator.validate('password').then(async (isValid) => {
                if (isValid) {
                    await this.$store.dispatch("checkLogin").then(res => {
                        if (this.password == res.password) {
                            this.$axios.delete(`/api/user/${res.email}`, {
                                headers: {'Content-Type': 'application/json'}
                            }).then(res => {
                                alert('회원 탈퇴가 완료되었습니다')
                                sessionStorage.removeItem('jwt-auth-token')
                                this.$store.commit('TOKEN', '')
                                this.$store.commit('IS_AUTH', false)
                                this.$router.push('/')
                            }).catch(e => {
                                alert('오류가 발생했습니다. 다시 한 번 시도해 주세요')
                            })
                        }
                    })
                }
            });
        }
    },
    data: () => {
        return {
            password: "",
            passwordType: "password",
            passwordConfirmType: "password",
        };
    },
}
</script>

<style scoped>
  .vertical-center {
    display: flex;
    text-align: left;
    justify-content: center;
    flex-direction: column;    
  }

  .inner-block {
    width: 450px;
    margin: auto;
    margin-top: 30px;
    margin-bottom: 30px;
    background: #ffffff;
    box-shadow: 0px 14px 80px rgba(34, 35, 58, 0.2);
    padding: 40px 40px 40px 40px;
    border-radius: 15px;
    transition: all .3s;
  }

  .vertical-center .form-control:focus {
    border-color: #2554FF;
    box-shadow: none;
  }
  button.btn {
    background-color:#6495ED;
  }
  .alert-danger {
    font-size: 14px;
  }
</style>