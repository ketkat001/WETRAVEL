import axios from 'axios'
import router from '@/router'

export default {
    async login (store, {email, password}) {
        sessionStorage.setItem("jwt-auth-token", "")
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
                sessionStorage.setItem("jwt-auth-token", response.headers["jwt-auth-token"])
            }
            else {
                store.commit('TOKEN', '')
                store.commit('IS_AUTH', false)
            }
        }).catch(e => {
            store.commit('TOKEN', '')
            store.commit('IS_AUTH', false)
            alert('로그인 정보가 올바르지 않습니다')
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
        if (sessionStorage.getItem('jwt-auth-token')) {
            store.commit('TOKEN', sessionStorage.getItem('jwt-auth-token'))
            store.commit('IS_AUTH', true)
            await axios.get(`/api/user/${store.getters.getToken}`, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(res => {
                result = res.data
            }).catch(e => {
                store.commit('logout')
                alert('토큰이 만료되었습니다. 다시 로그인해주세요')
                router.push('/');
            })
            return result;
        }
    },

    async getBookInfo (store, bookno) {
        var result;
        await axios.get(`/api/book/${bookno}`, {
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(res => {
            result = res.data
        }).catch(e => {
            alert('여행기 정보를 불러올 수 없습니다')
        })
        return result
    },

    async getCityList(store, province) {
        var result;
        await axios.get(`/api/search/${province}`, {
            headers: {'Content-Type': 'application/json'}
        }).then(res => {
            store.commit('CITY_LIST', res.data)
        }).catch(e => {
            alert('지역 목록을 불러오는 도중 오류가 발생했습니다. ')
        })
    }
}