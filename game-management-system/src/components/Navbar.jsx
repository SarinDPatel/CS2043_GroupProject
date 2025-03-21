import React, { useState } from 'react';
import { Search, Heart, ShoppingCart, User } from 'lucide-react';
import { Link } from 'react-router-dom';

const Navbar = () => {
  const [searchQuery, setSearchQuery] = useState('');
  
  return (
    <nav className="bg-white shadow-sm px-4 py-2 flex items-center justify-between">
      {/* Logo */}
      <div className="font-bold text-xl">GameStart</div>
      
      {/* Navigation Links */}
      <div className="hidden md:flex space-x-8">
        <Link to="/" className="text-gray-700 hover:text-gray-900">Home</Link>
        <Link to="/contact" className="text-gray-700 hover:text-gray-900">Contact</Link>
        <Link to="/about" className="text-gray-700 hover:text-gray-900">About</Link>
        <Link to="/login" className="text-gray-700 hover:text-gray-900">Sign Up</Link>
      </div>
      
      {/* Search Bar */}
      <div className="flex items-center bg-gray-100 rounded-md px-3 py-1 ml-4">
        <input
          type="text"
          placeholder="What are you looking for?"
          className="bg-transparent border-none outline-none text-sm w-40 md:w-64"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
        />
        <Search className="h-4 w-4 text-gray-500 ml-2" />
      </div>
      
      {/* Icons */}
      <div className="flex items-center space-x-4 ml-4">
        <Link to="/favorites" className="text-gray-700 hover:text-gray-900">
          <Heart className="h-5 w-5" />
        </Link>
        <Link to="/cart" className="text-gray-700 hover:text-gray-900">
          <ShoppingCart className="h-5 w-5" />
        </Link>
        <Link to="/profile" className="text-gray-700 hover:text-gray-900">
          <User className="h-5 w-5" />
        </Link>
      </div>
    </nav>
  );
};

export default Navbar; 