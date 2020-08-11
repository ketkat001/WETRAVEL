<template>
  <div class="vertical-center">
    <div class="inner-block">
      <form name="frm" action="join" method="post">
        <h3>회원 가입</h3>
        <div class="form-group">
          <label for="nickname">닉네임</label>
          <div class="input-group">
            <input 
              v-model="nickName" 
              v-validate="'required|min:3|max:20'" 
              placeholder="닉네임을 입력해주세요" 
              type="text" 
              name="nickname"
              class="form-control"
              autocomplete="off"
              :disabled="nicknameDup === false"
            />
            <span class="input-group-btn" style="margin-left: 10px">
              <button type="button" class="btn btn-primary" @click="nicknameCheck">중복 확인</button>
            </span>
          </div>
          <div class="alert alert-danger" v-if="errors.has('nickname')">
            {{ errors.first('nickname') }}
          </div>
        </div>
        <div class="form-group">
          <label for="email">이메일 주소</label>
          <div class="input-group">
            <input
              v-model="email"
              placeholder="이메일을 입력해주세요" 
              type="email" 
              name="email"
              class="form-control"
              v-validate="'required|email'"  
              :disabled="emailDup == false"
            />
            <span class="input-group-btn" style="margin-left: 10px">
              <button type="button" class="btn btn-primary" @click="emailCheck">중복 확인</button>
            </span>
          </div>
          <div class="alert alert-danger" v-if="errors.has('email')">
            {{ errors.first('email') }}
          </div>
        </div>
        <div class="form-group">
          <label for="password">비밀번호</label>
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
        <div class="form-group">
          <label for="password-confirmation">비밀번호 확인</label>
          <input 
            v-model="passwordConfirm"
            v-validate="'required|confirmed:password'" 
            name="password_confirmation" 
            type="password" 
            class="form-control" 
            placeholder="비밀번호를 재입력해주세요" 
            data-vv-as="password"
          />
          <div class="alert alert-danger" v-if="errors.has('password_confirmation')">
            {{ errors.first('password_confirmation') }}          
          </div>
        </div>
        <button type="submit" @click="backsubmit" class="btn btn-dark btn-lg btn-block">회원가입</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  components: {},
  created() {},
  props: {
    visible2: {
      type: Boolean,
      require: true,
      default: false,
    },
    title: {
      type: String,
      require: false,
    },
  },
  watch: {
    'this.email': function() {
      emailDup = true
    },
    'this.nickName': function() {
      nicknameDup = true
    }
  },
  methods: {
    backsubmit(event) {
      event.preventDefault();
      this.message = "";
      this.submitted = true;
      if (this.nicknameDup) {
        alert('닉네임 중복 확인을 해 주세요')
        return
      }
      if (this.emailDup) {
        alert('이메일 중복 확인을 해 주세요')
        return
      }
      this.$validator.validate().then(async (isValid) => {
        if (isValid) {
          this.successful = await this.$store.dispatch("signUp", {email: this.email, password: this.password, nickname: this.nickName})
          if (this.successful == true) {
            this.$store.dispatch("login", {email: this.email, password: this.password})
            this.$router.push('/')
          }
          else {
            alert("회원가입에 실패했습니다")
          }
        }
      });
    },
    emailCheck() {
      this.$validator.validate('email').then(isValid => {
        if (isValid) {
          this.$axios.get('/api/user/emailCheck', {
            params: {
              email: this.email
            },
            headers: {'Content-Type': 'application/json'}
          }).then(res => {
            if (res.data == true) {
              alert('이미 사용중인 이메일입니다. 다른 이메일을 입력해 주세요')
            }
            else {
              alert('사용 가능한 이메일입니다')
            }
            this.emailDup = res.data
          })
        }
      })
    },
    nicknameCheck() {
      this.$validator.validate('nickname').then(isValid => {
        if (isValid) {
          this.$axios.get('/api/user/nicknameCheck', {
            params: {
              nickname: this.nickName
            },
            headers: {'Content-Type': 'application/json'}
          }).then(res => {
            if (res.data == true) {
              alert('이미 사용중인 닉네임입니다. 다른 닉네임을 입력해 주세요')
              this.nickName = ""
            }
            else {
              alert('사용 가능한 닉네임입니다')
            }
            this.nicknameDup = res.data
          })
        }
      })
    }
  },
  data: () => {
    return {
      email: "",
      nickName: "",
      password: "",
      passwordConfirm: "",
      isTerm: false,
      passwordType: "password",
      passwordConfirmType: "password",
      submitted: false,
      message: "",
      successful: false,
      emailDup: true,
      nicknameDup: true
    };
  },
};
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

  .vertical-center .form-control:disabled {
    background-color: #888888;
  }

  .vertical-center h3 {
    text-align: center;
    margin: 0;
    line-height: 1;
    padding-bottom: 20px;
  }
  button.btn {
    background-color:#6495ED;
  }
  .alert-danger {
    font-size: 14px;
  }
</style>