import axios from 'axios'

export default {
    async login (store, {email, password}) {
        await axios.post(`${store.state.host}/api/user/login`,
            {
                email: email,
                password: password
            }, {headers: {'Content-Type': 'application/json'}}
            ).then((response) => {
            if (response.data.email == "No Match") {
                this.showAlert = true;
                this.errMsg = "로그인 정보가 올바르지 않습니다"
                store.commit('IS_AUTH', false)
            }
            else {
                store.commit('EMAIL', response.data.email)
                store.commit('NICKNAME', response.data.nickname)
                store.commit('INTRODUCE', response.data.introduce)
                store.commit('IS_AUTH', true)
            }
        })
        console.log(store.getters.getIsAuth)
        return store.getters.getIsAuth
    }
}