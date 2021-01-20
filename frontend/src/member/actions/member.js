import { createAction } from 'redux-actions'

// Action Types
export const CHANGE_FIELD = 'member/login/CHANGE_FIELD'

// Action Creator
export const changeField = createAction(CHANGE_FIELD)
