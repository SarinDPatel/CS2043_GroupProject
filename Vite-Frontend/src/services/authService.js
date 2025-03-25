// Base API URL - Spring Boot default port
const API_URL = 'http://localhost:8080/api';

export const authService = {
  // Login user
  login: async (username, password) => {
    try {
      const response = await fetch(`${API_URL}/credentials/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ 
          username: username, 
          password: password 
        }),
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      const data = await response.text();
      
      // Store authentication status
      if (response.ok) {
        localStorage.setItem('isAuthenticated', 'true');
        localStorage.setItem('username', username);
      }
      
      return data;
    } catch (error) {
      console.error('Login error:', error);
      throw error;
    }
  },
  
  // Register user
  register: async (newUsername, newPassword, role) => {
    try {
      const response = await fetch(`${API_URL}/credentials/register`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ 
          newUsername: newUsername, 
          newPassword: newPassword,
          role: role
        }),
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.text();
    } catch (error) {
      console.error('Registration error:', error);
      throw error;
    }
  },
  
  // Logout user
  logout: () => {
    localStorage.removeItem('isAuthenticated');
    localStorage.removeItem('username');
  },
  
  // Check if user is authenticated
  isAuthenticated: () => {
    return localStorage.getItem('isAuthenticated') === 'true';
  },
  
  // Get current username
  getUsername: () => {
    return localStorage.getItem('username');
  }
};