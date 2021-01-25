import { handleActions } from 'redux-actions'
import {
  CHANGE_LOGIN_FIELD,
  CHANGE_SIGNUP_FIELD,
  CHANGE_SIGNUP_AGREE_FIELD,
} from '../actions/member'

// 초기 값
const initState = {
  login: {
    username: '',
    password: '',
  },
  signup: {
    username: '',
    password: '',
    passwordRe: '',
    name: '',
    email: '',
    phone: '',
    address: '',
  },
  signupAgree: {
    all: false, // 전체 동의
    use: false, // 이용 약관
    info: false, // 개인 정보 수집
    sms: false, // SMS 마켓팅
  },
}

// 리듀서 생성
export const memberReducer = handleActions(
  {
    [CHANGE_LOGIN_FIELD]: (state, { payload }) => ({
      ...state,
      login: {
        ...state.login,
        username:
          payload.username !== undefined
            ? payload.username
            : state.login.username,
        password:
          payload.password !== undefined
            ? payload.password
            : state.login.password,
      },
    }),
    [CHANGE_SIGNUP_FIELD]: (state, { payload }) => ({
      ...state,
      signup: {
        ...state.signup,
        username:
          payload.username !== undefined
            ? payload.username
            : state.signup.username,
        password:
          payload.password !== undefined
            ? payload.password
            : state.signup.password,
        passwordRe:
          payload.passwordRe !== undefined
            ? payload.passwordRe
            : state.signup.passwordRe,
        name: payload.name !== undefined ? payload.name : state.signup.name,
        email: payload.email !== undefined ? payload.email : state.signup.email,
        phone: payload.phone !== undefined ? payload.phone : state.signup.phone,
        address:
          payload.address !== undefined
            ? payload.address
            : state.signup.address,
      },
    }),
    // TODO
    [CHANGE_SIGNUP_AGREE_FIELD]: (state, { payload }) => {
      console.log(payload)
      return {
        ...state,
        signupAgree: {
          ...state.signupAgree,
          all: payload.all !== undefined ? payload.all : state.signupAgree.all,
          use: payload.use !== undefined ? payload.use : state.signupAgree.use,
          info:
            payload.info !== undefined ? payload.info : state.signupAgree.info,
          sms: payload.sms !== undefined ? payload.sms : state.signupAgree.sms,
        },
      }
    },
  },
  initState
)
