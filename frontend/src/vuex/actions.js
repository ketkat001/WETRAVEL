import axios from 'axios'
import router from '@/router'

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
                store.commit('IS_AUTH', true)
                localStorage.setItem("jwt-auth-token", response.headers["jwt-auth-token"])
            }
            else {
                store.commit('TOKEN', '')
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

    async checkLogin (store) {
        var result;
        if (localStorage.getItem('jwt-auth-token')) {
            store.commit('TOKEN', localStorage.getItem('jwt-auth-token'))
            store.commit('IS_AUTH', true)
            await axios.get(`/api/user/${store.getters.getToken}`, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => {
                result = res.data
            }).catch(e => {
                alert('로그인 정보가 유효하지 않습니다. 다시 로그인해주세요')
                store.commit('logout')
                router.push('/');
            })
            return result;
        }
    }
}