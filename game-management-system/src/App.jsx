import {Routes, Route} from "react-router-dom"
import Home from "./pages/Home"
import Favorites from "./pages/Favorites"
import Login from "./pages/Login"
import Cart from "./pages/Cart"
import Profile from "./pages/Profile"
import Contact from "./pages/Contact"
import About from "./pages/About"
import Navbar from "./components/Navbar"

function App() {
  return (
    <>
      <Navbar />
      <main className="container mx-auto px-4 py-6">
        <Routes>
          <Route path= "/" element={<Home />}/>
          <Route path= "/favorites" element={<Favorites />}/>
          <Route path= "/login" element={<Login />}/>
          <Route path= "/cart" element={<Cart />}/>
          <Route path= "/profile" element={<Profile />}/>
          <Route path= "/contact" element={<Contact />}/>
          <Route path= "/about" element={<About />}/>
        </Routes>
      </main>
    </>
  )
}

export default App
