import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
  const [searchQuery, setSearchQuery] = useState('');
  
  return (
    <nav className="flex items-center justify-between px-4 py-4 bg-white shadow-sm">
    {/* Logo */}
    <Link to="/" className="text-xl font-bold text-gray-800">GameStart</Link>
    
    {/* Center Navigation Links */}
    <div className="hidden md:flex space-x-6">
      <Link to="/" className="text-gray-700 hover:text-gray-900">Home</Link>
      <Link to="/contact" className="text-gray-700 hover:text-gray-900">Contact</Link>
      <Link to="/about" className="text-gray-700 hover:text-gray-900">About</Link>
      <Link to="/signup" className="text-gray-700 hover:text-gray-900">Sign Up</Link>
    </div>
    
    {/* Right Side Icons */}
    <div className="flex items-center space-x-4">
      {/* Search Bar */}
      <div className="relative hidden md:block">
          <input 
            type="text" 
            placeholder="What are you looking for?" 
            className="pl-3 pr-8 py-1 w-64 rounded-lg border border-gray-300 text-sm focus:outline-none focus:ring-1 focus:ring-gray-400" 
          />
        </div>
      
      {/* Icons using letters as placeholders */}
      
      <Link to="/favorites" className="text-gray-700 hover:text-gray-900 w-6 h-6 flex items-center justify-center">
        F
      </Link>
      <Link to="/notifications" className="text-gray-700 hover:text-gray-900 w-6 h-6 flex items-center justify-center">
        N
      </Link>
      <Link to="/cart" className="text-gray-700 hover:text-gray-900 w-6 h-6 flex items-center justify-center">
        C
      </Link>
      <Link to="/profile" className="text-gray-700 hover:text-gray-900 w-6 h-6 flex items-center justify-center">
        P
      </Link>
    </div>
  </nav>
  );
};

export default Navbar; 