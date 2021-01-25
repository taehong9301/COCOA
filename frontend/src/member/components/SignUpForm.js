import React from 'react'

import { shallowEqual, useDispatch, useSelector } from 'react-redux'
import { changeSignUpField } from '../actions/member'

const SignUpForm = () => {
  const dispatch = useDispatch()

  const {
    username,
    password,
    passwordRe,
    name,
    email,
    phone,
    address,
  } = useSelector((state) => state.member.signup, shallowEqual)

  const onChangeInput = (event) => {
    const { name, value } = event.target
    dispatch(changeSignUpField({ [name]: value }))
  }

  return (
    <form className="signUpForm">
      <h3 className="signUpTit">기본 정보</h3>
      <div className="signUpFormRow">
        <label className="requiredLabel">아이디</label>
        <input
          className="field"
          type="text"
          name="username"
          defaultValue={username}
          onChange={onChangeInput}
        />
      </div>
      <div className="signUpFormRow">
        <label className="requiredLabel">비밀번호</label>
        <input
          className="field"
          type="password"
          name="password"
          defaultValue={password}
          onChange={onChangeInput}
        />
      </div>
      <div className="signUpFormRow">
        <label className="requiredLabel">비밀번호 확인</label>
        <input
          className="field"
          type="password"
          name="passwordRe"
          defaultValue={passwordRe}
          onChange={onChangeInput}
        />
      </div>
      <div className="signUpFormRow">
        <label className="requiredLabel">이름</label>
        <input
          className="field"
          type="text"
          name="name"
          defaultValue={name}
          onChange={onChangeInput}
        />
      </div>
      <div className="signUpFormRow">
        <label className="requiredLabel">이메일</label>
        <input
          className="field"
          type="text"
          name="email"
          defaultValue={email}
          onChange={onChangeInput}
        />
      </div>
      <h3 className="signUpTit">추가 정보</h3>
      <div className="signUpFormRow">
        <label className="label">전화번호</label>
        <input
          className="field"
          type="text"
          name="phone"
          defaultValue={phone}
          onChange={onChangeInput}
        />
      </div>
      <div className="signUpFormRow">
        <label className="label">주소</label>
        <input
          className="field"
          type="text"
          name="address"
          defaultValue={address}
          onChange={onChangeInput}
        />
      </div>
    </form>
  )
}

export default SignUpForm
