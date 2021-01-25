import { createAction } from 'redux-actions'

// Action Types
export const CHANGE_LOGIN_FIELD = 'member/login/CHANGE_LOGIN_FIELD'
export const CHANGE_SIGNUP_FIELD = 'member/signup/CHANGE_SIGNUP_FIELD'
export const CHANGE_SIGNUP_AGREE_FIELD =
  'member/signup/CHANGE_SIGNUP_AGREE_FIELD'

// Action Creator
export const changeLoginField = createAction(CHANGE_LOGIN_FIELD)
export const changeSignUpField = createAction(CHANGE_SIGNUP_FIELD)
export const changeSignUpAgreeField = createAction(CHANGE_SIGNUP_AGREE_FIELD)
