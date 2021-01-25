import React from 'react'
import { Link } from 'react-router-dom'
import { shallowEqual, useDispatch, useSelector } from 'react-redux'
import { changeLoginField } from '../actions/member'

const LoginForm = () => {
  const dispatch = useDispatch()

  const { username, password } = useSelector(
    (state) => state.member.login,
    shallowEqual
  )

  const onChangeInput = (event) => {
    const { name, value } = event.target
    dispatch(changeLoginField({ [name]: value }))
  }

  const onSubmit = (event) => {
    event.preventDefault()
    console.log('Try login... username:', username)
  }

  return (
    <form onSubmit={onSubmit} className="loginForm">
      <h3 className="loginTit">회원 로그인</h3>
      <input
        type="text"
        name="username"
        defaultValue={username}
        className="loginInput"
        placeholder="Enter your username"
        onChange={onChangeInput}
      />
      <input
        type="password"
        name="password"
        defaultValue={password}
        className="loginInput"
        placeholder="Enter your password"
        onChange={onChangeInput}
      />
      <button className="loginBtn">LOGIN</button>

      <div className="loginHelpMenu">
        <Link to="/search/email">이메일 찾기</Link>/
        <Link to="/search/password">비밀번호 찾기</Link>
      </div>

      <h4 className="oauthLoginTit">간편 로그인</h4>
      <button className="googleLoginBtn">GOOGLE LOGIN</button>
      <button className="kakaoLoginBtn">KAKAO LOGIN</button>
      <button className="naverLoginBtn">NAVER LOGIN</button>
    </form>
  )
}

export default LoginForm
