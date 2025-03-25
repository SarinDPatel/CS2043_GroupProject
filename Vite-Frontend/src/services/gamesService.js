// Base API URL - Spring Boot default port
const API_URL = 'http://localhost:8080/api';

export const gamesService = {
  // Get all games
  getAllGames: async () => {
    try {
      const response = await fetch(`${API_URL}/games`);
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      const data = await response.json();
      
      // If imageUrl is missing, add placeholder images
      return data.map(game => ({
        ...game,
        imageUrl: game.imageUrl || `https://placehold.co/600x400/3a3a3c/FFFFFF?text=${encodeURIComponent(game.title)}`
      }));
    } catch (error) {
      console.error('Error fetching games:', error);
      throw error;
    }
  },
  
  // Get a single game by ID
  getGameById: async (id) => {
    try {
      const response = await fetch(`${API_URL}/games/${id}`);
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      const game = await response.json();
      
      // If imageUrl is missing, add placeholder image
      return {
        ...game,
        imageUrl: game.imageUrl || `https://placehold.co/600x400/3a3a3c/FFFFFF?text=${encodeURIComponent(game.title)}`
      };
    } catch (error) {
      console.error(`Error fetching game with ID ${id}:`, error);
      throw error;
    }
  },
  
  // Add more methods as needed (search games, filter by genre, etc.)
}; 