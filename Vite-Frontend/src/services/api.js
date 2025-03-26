import axios from 'axios';

const API_URL = 'http://localhost:8080';  // Spring Boot default port

const apiClient = axios.create({
    baseURL: process.env.REACT_APP_API_URL || API_URL,
    headers: {
        'Content-Type': 'application/json',
    },
});

// Add request interceptor for auth tokens
apiClient.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default apiClient;