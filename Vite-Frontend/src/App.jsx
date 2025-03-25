import { useState } from 'react'
import './App.css'
import {Routes, Route} from "react-router-dom"

import Home from './pages/Home'
import Favorites from './pages/Favorites';
import Login from './pages/Login';
import Cart from './pages/Cart';
import Profile from './pages/Profile';
import Contact from './pages/Contact';
import About from './pages/About';
import ProtectedRoute from './components/ProtectedRoute';

import Navbar from './components/Navbar'
import AdminLogin from './pages/AdminLogin';

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        {/* Public routes */}
        <Route path="/" element={<Home />}/>
        <Route path="/login" element={<Login />}/>
        <Route path="/admin" element={<AdminLogin />}/>
        <Route path="/about" element={<About />}/>
        <Route path="/contact" element={<Contact />}/>
        
        {/* Protected routes - require authentication */}
        <Route element={<ProtectedRoute />}>
          <Route path="/profile" element={<Profile />}/>
          <Route path="/favorites" element={<Favorites />}/>
          <Route path="/cart" element={<Cart />}/>
        </Route>
      </Routes>
    </>
  )
}

export default App
