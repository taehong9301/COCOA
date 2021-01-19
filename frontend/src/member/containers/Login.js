import React from 'react'
import '../style/login.css'

const Login = () => {
  const onSubmit = (event) => {
    event.preventDefault()
  }
  return (
    <div className="loginWrapper">
      <form onSubmit={onSubmit} className="loginForm">
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
        <button className="signUpBtn">SIGN UP</button>
      </form>
    </div>
  )
}

export default Login
