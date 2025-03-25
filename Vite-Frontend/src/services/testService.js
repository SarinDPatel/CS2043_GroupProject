// Base API URL - Spring Boot default port
const API_URL = 'http://localhost:8080/api';

export const testService = {
  // Simple test endpoint
  testConnection: async () => {
    try {
      const response = await fetch(`${API_URL}/test`);
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.text();
    } catch (error) {
      console.error('Error testing connection:', error);
      throw error;
    }
  },
  
  // Generic test endpoint
  runTest: async (testName) => {
    try {
      const response = await fetch(`${API_URL}/test/${testName}`);
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.text();
    } catch (error) {
      console.error(`Error running test ${testName}:`, error);
      throw error;
    }
  },
  
  // Can add more test endpoints as needed
  healthCheck: async () => {
    try {
      const response = await fetch(`${API_URL}/test/health`);
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.json();
    } catch (error) {
      console.error('Error checking health:', error);
      throw error;
    }
  }
}; 