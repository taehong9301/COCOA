import React from 'react'
import { Link } from 'react-router-dom'

const NavBar = () => {
  return (
    <nav>
      <ul>
        <li>
          <Link to="/">TOP</Link>
        </li>
        <li>
          <Link to="/login">EVENT</Link>
        </li>
        <li>
          <Link to="/">OUTER</Link>
        </li>
        <li>
          <Link to="/profile">DRESS</Link>
        </li>
        <li>
          <Link to="/login">KNIT/TEE</Link>
        </li>
        <li>
          <Link to="/profile">BLOUSE</Link>
        </li>
        <li>
          <Link to="/">SKIRT</Link>
        </li>
        <li>
          <Link to="/login">PANTS</Link>
        </li>
        <li>
          <Link to="/profile">SHOES</Link>
        </li>
        <li>
          <Link to="/profile">ACC</Link>
        </li>
        <li>
          <Link to="/profile">BAG</Link>
        </li>
        <li>
          <Link to="/profile">당일발송</Link>
        </li>
      </ul>
    </nav>
  )
}

export default NavBar
