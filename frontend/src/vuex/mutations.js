import * as types from './mutation_type'

export default {
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
        if (state.email) {
            state.email = ''
            state.nickname = ''
            state.introduce = ''
        }
    },
    isLogin (state) {
        return state.email != ''
    }
}