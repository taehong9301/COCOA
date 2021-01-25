import React from 'react'

import { shallowEqual, useDispatch, useSelector } from 'react-redux'
import { changeSignUpAgreeField } from '../actions/member'

const SignUpAgreeForm = () => {
  const dispatch = useDispatch()

  const { all, use, info, sms } = useSelector(
    (state) => state.member.signupAgree,
    shallowEqual
  )

  const onChangeCheckbox = (event) => {
    const { name, value } = event.target
    dispatch(changeSignUpAgreeField({ [name]: value }))
  }

  return (
    <form className="signUpAgreeForm">
      <h3 className="signUpAgreeTit">동의</h3>
      <div className="signUpAgreeRow">
        <label className="desc">
          이용약관, 개인정보 수집, SMS & 이메일 수신에 동의하십니까?
        </label>
        <input
          type="checkbox"
          className="fieldCheckbox"
          name="all"
          value={all}
          onChange={onChangeCheckbox}
        />
        <label className="agree">전체 동의</label>
        <div className="agreeText">asdasdsad</div>
      </div>
      <div className="signUpAgreeRow">
        <label className="desc">이용약관에 동의하십니까?</label>
        <input
          type="checkbox"
          className="fieldCheckbox"
          name="use"
          defaultValue={use}
          onChange={onChangeCheckbox}
        />
        <label className="agree">동의함</label>
      </div>
      <div className="signUpAgreeRow">
        <label className="desc">개인정보 수집 및 이용에 동의하십니까?</label>
        <input
          type="checkbox"
          className="fieldCheckbox"
          name="info"
          defaultValue={info}
          onChange={onChangeCheckbox}
        />
        <label className="agree">동의함</label>
      </div>
      <div className="signUpAgreeRow">
        <label className="desc">SMS & 이메일 수신에 동의하십니까?</label>
        <input
          type="checkbox"
          className="fieldCheckbox"
          name="sms"
          defaultValue={sms}
          onChange={onChangeCheckbox}
        />
        <label className="agree">동의함</label>
      </div>
    </form>
  )
}

export default SignUpAgreeForm
