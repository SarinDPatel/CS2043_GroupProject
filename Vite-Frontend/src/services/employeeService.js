// Base API URL - Spring Boot default port
const API_URL = 'http://localhost:8080/api';

// Helper to include auth token in requests
const getAuthHeader = () => {
  const token = localStorage.getItem('token');
  return token ? { 'Authorization': `Bearer ${token}` } : {};
};

export const employeeService = {
  // Get all employees
  getAllEmployees: async () => {
    try {
      const response = await fetch(`${API_URL}/employees`, {
        headers: {
          ...getAuthHeader()
        }
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.json();
    } catch (error) {
      console.error('Error fetching employees:', error);
      throw error;
    }
  },
  
  // Get employee by ID
  getEmployeeById: async (id) => {
    try {
      const response = await fetch(`${API_URL}/employees/${id}`, {
        headers: {
          ...getAuthHeader()
        }
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.json();
    } catch (error) {
      console.error(`Error fetching employee with ID ${id}:`, error);
      throw error;
    }
  },
  
  // Create new employee
  createEmployee: async (employeeData) => {
    try {
      const response = await fetch(`${API_URL}/employees`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          ...getAuthHeader()
        },
        body: JSON.stringify(employeeData)
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.json();
    } catch (error) {
      console.error('Error creating employee:', error);
      throw error;
    }
  },
  
  // Update employee
  updateEmployee: async (id, employeeData) => {
    try {
      const response = await fetch(`${API_URL}/employees/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          ...getAuthHeader()
        },
        body: JSON.stringify(employeeData)
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      
      return await response.json();
    } catch (error) {
      console.error(`Error updating employee with ID ${id}:`, error);
      throw error;
    }
  },
  
  // Delete employee
  deleteEmployee: async (id) => {
    try {
      const response = await fetch(`${API_URL}/employees/${id}`, {
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
      console.error(`Error deleting employee with ID ${id}:`, error);
      throw error;
    }
  },
  
  // Process checkout as an employee
  checkout: async (items) => {
    try {
      const response = await fetch(`${API_URL}/employees/checkout`, {
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
  }
}; 