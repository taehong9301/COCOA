import React from 'react'
import '../style/login.css'
import LoginForm from '../components/LoginForm'

const Login = () => {
  return (
    <div className="loginWrapper">
      <LoginForm />

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
