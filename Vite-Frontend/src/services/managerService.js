// Base API URL - Spring Boot default port
const API_URL = 'http://localhost:8080/api';

// Helper to include auth token in requests
const getAuthHeader = () => {
  const token = localStorage.getItem('token');
  return token ? { 'Authorization': `Bearer ${token}` } : {};
};

export const managerService = {
  // Get all managers
  getAllManagers: async () => {
    try {
      const response = await fetch(`${API_URL}/managers`, {
        headers: {
          ...getAuthHeader()
        }
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.json();
    } catch (error) {
      console.error('Error fetching managers:', error);
      throw error;
    }
  },
  
  // Get manager by ID
  getManagerById: async (id) => {
    try {
      const response = await fetch(`${API_URL}/managers/${id}`, {
        headers: {
          ...getAuthHeader()
        }
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.json();
    } catch (error) {
      console.error(`Error fetching manager with ID ${id}:`, error);
      throw error;
    }
  },
  
  // Create new manager
  createManager: async (managerData) => {
    try {
      const response = await fetch(`${API_URL}/managers`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          ...getAuthHeader()
        },
        body: JSON.stringify(managerData)
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.json();
    } catch (error) {
      console.error('Error creating manager:', error);
      throw error;
    }
  },
  
  // Update manager
  updateManager: async (id, managerData) => {
    try {
      const response = await fetch(`${API_URL}/managers/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          ...getAuthHeader()
        },
        body: JSON.stringify(managerData)
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.json();
    } catch (error) {
      console.error(`Error updating manager with ID ${id}:`, error);
      throw error;
    }
  },
  
  // Delete manager
  deleteManager: async (id) => {
    try {
      const response = await fetch(`${API_URL}/managers/${id}`, {
        method: 'DELETE',
        headers: {
          ...getAuthHeader()
        }
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return true;
    } catch (error) {
      console.error(`Error deleting manager with ID ${id}:`, error);
      throw error;
    }
  },
  
  // Process checkout as a manager
  checkout: async (items) => {
    try {
      const response = await fetch(`${API_URL}/managers/checkout`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(items)
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.text();
    } catch (error) {
      console.error('Error processing checkout:', error);
      throw error;
    }
  },
  
  // Add inventory
  addInventory: async (playwaresWithQtys) => {
    try {
      const response = await fetch(`${API_URL}/managers/addinv`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(playwaresWithQtys)
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.text();
    } catch (error) {
      console.error('Error adding inventory:', error);
      throw error;
    }
  },
  
  // Remove inventory
  removeInventory: async (playwareIds) => {
    try {
      const response = await fetch(`${API_URL}/managers/removeinv`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(playwareIds)
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.text();
    } catch (error) {
      console.error('Error removing inventory:', error);
      throw error;
    }
  },
  
  // Apply discount to an item
  applyDiscount: async (playwareId, discountAmt) => {
    try {
      const response = await fetch(`${API_URL}/managers/discount/${playwareId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(discountAmt)
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.text();
    } catch (error) {
      console.error(`Error applying discount to playware ${playwareId}:`, error);
      throw error;
    }
  },
  
  // Offer warranty for an item
  offerWarranty: async (playwareId, numMonths) => {
    try {
      const response = await fetch(`${API_URL}/managers/warranty/${playwareId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(numMonths)
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.text();
    } catch (error) {
      console.error(`Error offering warranty for playware ${playwareId}:`, error);
      throw error;
    }
  }
}; 