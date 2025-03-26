import React from 'react';
import GameCard from './GameCard';

const GamesList = ({ games }) => {
  return (
    <div className="container mx-auto px-4 py-8">
      <h2 className="text-2xl font-bold text-gray-800 mb-6">Games Store</h2>
      
      {games.length === 0 ? (
        <p className="text-gray-600">No games available at the moment.</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
          {games.map((game) => (
            <GameCard
              key={game.id}
              title={game.title}
              genre={game.genre}
              price={game.price}
              imageUrl={game.imageUrl}
            />
          ))}
        </div>
      )}
    </div>
  );
};

export default GamesList; 