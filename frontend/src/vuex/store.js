import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

const state = {
    token: '',
    errorState: '',
    isAuth: false,
    joinSuccess: false,
    provinceList: ['서울', '경기', '강원', '충청', '전라', '경상', '제주', '부산'],
    cityList: ['']
}

export default new Vuex.Store({
    state,
    mutations,
    getters,
    actions,
})