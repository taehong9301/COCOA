import React from 'react'
import '../style/login.css'
import { Link } from 'react-router-dom'

const Login = () => {
  const onSubmit = (event) => {
    event.preventDefault()
  }
  return (
    <div className="loginWrapper">
      <form onSubmit={onSubmit} className="loginForm">
        <h3 className="loginTit">회원 로그인</h3>
        <input
          type="text"
          name="email"
          className="loginInput"
          placeholder="Enter your email"
        />
        <input
          type="password"
          name="password"
          className="loginInput"
          placeholder="Enter your password"
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

      <div className="signUpWrapper">
        <div className="signUpDesc">
          <h3 className="signUpTit">아직 회원이 아니신가요?</h3>
          <p>
            회원가입을 하시면 회원에게만 제공되는 다양한 혜택과 이벤트에
            참여하실 수 있습니다. 코코아 회원만의 특별한 혜택을 만나보세요.
          </p>
        </div>
        <button className="signUpBtn">SIGN UP</button>
      </div>
    </div>
  )
}

export default Login
