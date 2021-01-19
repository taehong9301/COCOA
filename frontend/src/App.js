import './App.css'
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import Login from './member/containers/Login'
import Profile from './member/containers/Profile'
import NavBar from './common/components/NavBar'
import Header from './common/components/Header'
import Footer from './common/components/Footer'
import Logo from './common/components/Logo'

function App() {
  return (
    <BrowserRouter>
      <div className="rootContainer">
        {/*헤더*/}
        <Header />

        {/*로고*/}
        <Logo />

        {/*네비게이션*/}
        <NavBar />

        {/*본문 - 라우팅 설정*/}
        <main>
          <Switch>
            <Route path="/login">
              <Login />
            </Route>
            <Route path="/profile">
              <Profile />
            </Route>
            <Route path="/">home</Route>
          </Switch>
        </main>

        {/*푸터*/}
        <Footer />
      </div>
    </BrowserRouter>
  )
}

export default App
