import React from 'react'
import { Link } from 'react-router-dom'
import '../style/logo.css'

const Logo = () => {
  return (
    <div className="logo">
      <h1>
        <Link to="/">COCOA</Link>
      </h1>
      <p className="tit">여성 전문 쇼핑몰 코코아</p>
    </div>
  )
}

export default Logo
