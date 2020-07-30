import axios from 'axios'

export default {
    async login (store, {email, password}) {
        localStorage.setItem("jwt-auth-token", "")
        localStorage.setItem("login_user", "")
        localStorage.setItem("login_user_nickname", "")
        await axios.post(`/api/user/login`,
        {
            email: email,
            password: password
        }, {headers: {'Content-Type': 'application/json'}}
        ).then((response) => {
            if (response.data.status) {
                this.showAlert = true;
                this.errMsg = response.data.data.email + "로그인 되었습니다"
                store.commit('TOKEN', response.headers["jwt-auth-token"])
                store.commit('EMAIL', response.data.data.email)
                store.commit('NICKNAME', response.data.data.nickname)
                store.commit('INTRODUCE', response.data.data.introduce)
                store.commit('IS_AUTH', true)
                localStorage.setItem("jwt-auth-token", response.headers["jwt-auth-token"])
                localStorage.setItem("login_email", response.data.data.email)
                localStorage.setItem("login_nickname", response.data.data.nickname)
                localStorage.setItem("login_introduce", response.data.data.introduce)
            }
            else {
                store.commit('TOKEN', '')
                store.commit('EMAIL', '')
                store.commit('NICKNAME', '')
                store.commit('INTRODUCE', '')
                store.commit('IS_AUTH', false)
            }
        })
        return store.getters.getIsAuth
    },

    async signUp (store, {email, password, nickname}) {
        await axios.post(`/api/user/signUp`,
        {
            email: email,
            password: password,
            nickname: nickname,
            introduce: "안녕하세요"
        }, {headers: {'Content-Type': 'application/json'}}
        ).then((response) => {
            store.commit('JOIN_SUCCESS', response.data < 2)
        })
        return store.getters.getJoinSuccess
    },

    checkLogin (store) {
        if (localStorage.getItem('jwt-auth-token')) {
            store.commit('TOKEN', localStorage.getItem('jwt-auth-token'))
            store.commit('EMAIL', localStorage.getItem('login_email'))
            store.commit('NICKNAME', localStorage.getItem('login_nickname'))
            store.commit('INTRODUCE', localStorage.getItem('login_introduce'))
            store.commit('IS_AUTH', true)
        }
    }
}