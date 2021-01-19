import React from 'react'
import { Link } from 'react-router-dom'
import '../style/header.css'

const Header = () => {
  return (
    <>
      <header role="header">
        <ul className="menu">
          {/*로그인 하지 않았을 때*/}
          <li>
            <Link to="/login">LOGIN</Link>
          </li>
          <li>
            <Link to="/signup">SIGN UP</Link>
          </li>
          {/*로그인 후*/}
          <li>
            <Link to="/profile">PROFILE</Link>
          </li>
          <li>
            <Link to="/qna">Q&A</Link>
          </li>
          <li>
            <Link to="/logout">LOGOUT</Link>
          </li>
        </ul>
      </header>
    </>
  )
}

export default Header
