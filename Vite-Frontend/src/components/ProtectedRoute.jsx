import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { authService } from '../services/authService';

const ProtectedRoute = ({ redirectPath = '/login', children }) => {
  const isAuthenticated = authService.isAuthenticated();
  
  if (!isAuthenticated) {
    return <Navigate to={redirectPath} replace />;
  }
  
  return children ? children : <Outlet />;
};

export default ProtectedRoute; 