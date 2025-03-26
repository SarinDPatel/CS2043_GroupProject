import React, { useState, useEffect } from 'react';
import GamesList from '../components/GamesList';
import { mockGames } from '../data/mockGames';
import { gamesService } from '../services/gamesService';

function Home() {
  const [games, setGames] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [useMockData, setUseMockData] = useState(false);

  useEffect(() => {
    const fetchGames = async () => {
      try {
        if (useMockData) {
          // Use mock data for development or if API isn't available
          setGames(mockGames);
        } else {
          // Fetch real data from the API
          const data = await gamesService.getAllGames();
          setGames(data);
        }
        setError(null);
      } catch (err) {
        console.error("Failed to fetch games:", err);
        setError("Failed to load games. Using mock data instead.");
        setGames(mockGames);
      } finally {
        setLoading(false);
      }
    };

    fetchGames();
  }, [useMockData]);

  // Toggle between mock and real data (for development purposes)
  const toggleDataSource = () => {
    setUseMockData(!useMockData);
    setLoading(true);
  };

  return (
    <div>
      <div className="bg-gray-100 py-8">
        <div className="container mx-auto px-4">
          <h1 className="text-3xl font-bold text-gray-900">GameStart</h1>
          <p className="mt-2 text-lg text-gray-600">Find your next gaming adventure</p>
          
          <div className="mt-4 flex items-center">
            <button 
              onClick={toggleDataSource} 
              className="text-sm bg-gray-200 px-3 py-1 rounded hover:bg-gray-300"
            >
              {useMockData ? "Use Real Data" : "Use Mock Data"}
            </button>
            {error && <p className="ml-4 text-sm text-red-600">{error}</p>}
          </div>
        </div>
      </div>
      
      {loading ? (
        <div className="container mx-auto px-4 py-8 text-center">
          <p className="text-gray-600">Loading games...</p>
        </div>
      ) : (
        <GamesList games={games} />
      )}
    </div>
  );
}

export default Home;