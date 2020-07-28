import * as types from './mutation_type'

export default {
    [types.EMAIL] (state, email) {
        state.email = email
    },
    [types.NICKNAME] (state, nickname) {
        state.nickname = nickname
    },
    [types.ERROR_STATE] (state, errorState) {
        state.errorState = errorState
    },
    [types.IS_AUTH] (state, isAuth) {
        state.isAuth = isAuth
    }
}