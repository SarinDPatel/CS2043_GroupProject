import axios from 'axios';

const API_URL = 'http://localhost:3001/api'

// Login user
export const login = async (email, password) => {
    try {
        const response = await axios.post(`${API_URL}/login`, { email, password });
        localStorage.setItem('token', response.data.token); // store token
        return response.data;
    } catch (error) {
        throw error.response?.data?.message || 'Login failed';
    }
};

// Register user
export const register = async (userData) => {
    try {
        const response = await axios.post(`${API_URL}/register`, userData);
        return response.data;
    } catch (error) {
        throw error.response?.data?.message || 'Registration failed';
    }
};

// Logout user
export const logout = () => {
    localStorage.removeItem('token');
};