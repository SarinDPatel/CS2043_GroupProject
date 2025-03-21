import { Profiler, useState } from 'react'
import './App.css'
import {Routes, Route} from "react-router-dom"

import Home from './pages/Home'
import Favorites from './pages/Favorites';
import Login from './pages/Login';
import Cart from './pages/Cart';
import Profile from './pages/Profile';
import Contact from './pages/Contact';
import About from './pages/About';

import Navbar from './components/Navbar'
import AdminLogin from './pages/AdminLogin';

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Navbar />
      <Routes>
        <Route path= "/" element={<Home />}/>
        <Route path= "/admin" element={<AdminLogin/>}/>
      </Routes>
    </>
  )
}

export default App
