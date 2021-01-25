import React from 'react'
import '../style/signup.css'
import SignUpForm from '../components/SignUpForm'
import { shallowEqual, useSelector } from 'react-redux'
import SignUpAgreeForm from '../components/SignUpAgreeForm'

const Login = () => {
  const { username, password, passwordRe, name, email } = useSelector(
    (state) => state.member.signup,
    shallowEqual
  )

  return (
    <div className="signUpWrapper">
      <SignUpForm />
      <SignUpAgreeForm />
    </div>
  )
}

export default Login
