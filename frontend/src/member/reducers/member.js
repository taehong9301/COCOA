import { handleActions } from 'redux-actions'
import { CHANGE_FIELD } from '../actions/member'

// 초기 값
const initState = {
  login: {
    username: '',
    password: '',
  },
}

// 리듀서 생성
export const memberReducer = handleActions(
  {
    [CHANGE_FIELD]: (state, { payload }) => ({
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
  },
  initState
)
