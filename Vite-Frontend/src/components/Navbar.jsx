import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { authService } from '../services/authService';

const Navbar = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const navigate = useNavigate();
  const isAuthenticated = authService.isAuthenticated();
  const username = authService.getUsername();
  
  const handleLogout = () => {
    authService.logout();
    navigate('/login');
  };
  
  return (
    <nav className="flex items-center justify-between px-4 py-4 bg-white shadow-sm">
      {/* Logo */}
      <Link to="/" className="text-xl font-bold text-gray-800">GameStart</Link>
      
      {/* Center Navigation Links */}
      <div className="hidden md:flex space-x-6">
        <Link to="/" className="text-gray-700 hover:text-gray-900">Home</Link>
        <Link to="/contact" className="text-gray-700 hover:text-gray-900">Contact</Link>
        <Link to="/about" className="text-gray-700 hover:text-gray-900">About</Link>
        {!isAuthenticated && (
          <Link to="/login" className="text-gray-700 hover:text-gray-900">Login / Register</Link>
        )}
      </div>
      
      {/* Right Side Icons */}
      <div className="flex items-center space-x-4">
        {/* Search Bar */}
        <div className="relative hidden md:block">
          <input 
            type="text" 
            placeholder="What are you looking for?" 
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            className="pl-3 pr-8 py-1 w-64 rounded-lg border border-gray-300 text-sm focus:outline-none focus:ring-1 focus:ring-gray-400" 
          />
        </div>
        
        {/* Icons - only show when authenticated */}
        {isAuthenticated && (
          <>
            <Link to="/favorites" className="text-gray-700 hover:text-gray-900 w-6 h-6 flex items-center justify-center">
              F
            </Link>
            <Link to="/cart" className="text-gray-700 hover:text-gray-900 w-6 h-6 flex items-center justify-center">
              C
            </Link>
            <div className="relative group">
              <Link to="/profile" className="text-gray-700 hover:text-gray-900 w-6 h-6 flex items-center justify-center">
                P
              </Link>
              <div className="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg py-1 z-10 hidden group-hover:block">
                <div className="px-4 py-2 text-sm text-gray-700 border-b">
                  Signed in as <span className="font-medium">{username}</span>
                </div>
                <Link to="/profile" className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Your Profile</Link>
                <button 
                  onClick={handleLogout}
                  className="block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                >
                  Sign out
                </button>
              </div>
            </div>
          </>
        )}
        
        {/* Login link when not authenticated */}
        {!isAuthenticated && (
          <Link to="/login" className="text-gray-700 hover:text-gray-900 px-3 py-1 border border-gray-300 rounded-md text-sm">
            Login
          </Link>
        )}
      </div>
    </nav>
  );
};

export default Navbar; 