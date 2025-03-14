import {Routes, Route} from "react-router-dom"
import Home from "./pages/Home"
import Favorites from "./pages/Favorites"
import Login from "./pages/Login"
import Cart from "./pages/Cart"
import Profile from "./pages/Profile"


function App() {
  return (
      <Routes>
        <Route path= "/" element={<Home />}/>
        <Route path= "/favorites" element={<Favorites />}/>
        <Route path= "/login" element={<Login />}/>
        <Route path= "/cart" element={<Cart />}/>
        <Route path= "/profile" element={<Profile />}/>
      </Routes>
  )
}

export default App
