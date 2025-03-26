import React from 'react';

const GameCard = ({ title, genre, price, imageUrl }) => {
  return (
    <div className="bg-white rounded-lg shadow-md overflow-hidden">
      <img 
        src={imageUrl || '/placeholder-game.jpg'} 
        alt={title} 
        className="w-full h-48 object-cover"
      />
      <div className="p-4">
        <h3 className="text-lg font-semibold text-gray-800">{title}</h3>
        <p className="text-sm text-gray-600 mt-1">{genre}</p>
        <div className="mt-3 flex justify-between items-center">
          <span className="text-lg font-bold text-gray-900">${price.toFixed(2)}</span>
          <button className="bg-gray-800 text-white px-3 py-1 rounded-md text-sm hover:bg-gray-700">
            Add to Cart
          </button>
        </div>
      </div>
    </div>
  );
};

export default GameCard; 