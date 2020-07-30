import * as types from './mutation_type'
import { _ } from 'core-js'

export default {
    [types.TOKEN] (state, token) {
        state.token = token
    },
    [types.EMAIL] (state, email) {
        state.email = email
    },
    [types.NICKNAME] (state, nickname) {
        state.nickname = nickname
    },
    [types.INTRODUCE] (state, introduce) {
        state.introduce = introduce
    },
    [types.ERROR_STATE] (state, errorState) {
        state.errorState = errorState
    },
    [types.IS_AUTH] (state, isAuth) {
        state.isAuth = isAuth
    },
    [types.JOIN_SUCCESS] (state, joinSuccess) {
        state.joinSuccess = joinSuccess
    },
    logout (state) {
        if (localStorage.getItem('jwt-auth-token')) {
            localStorage.removeItem('jwt-auth-token')
            localStorage.removeItem('login_email')
            localStorage.removeItem('login_nickname')
            localStorage.removeItem('login_introduce')
            this.commit('TOKEN', '')
            this.commit('EMAIL', '')
            this.commit('NICKNAME', '')
            this.commit('INTRODUCE', '')
            this.commit('IS_AUTH', false)
        }
    },
    isLogin (state) {
        return state.isAuth
    }
}