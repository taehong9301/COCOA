import { combineReducers } from 'redux'
import { memberReducer } from './member/reducers/member'

// root reducer 에 추가
export const rootReducer = combineReducers({ member: memberReducer })
