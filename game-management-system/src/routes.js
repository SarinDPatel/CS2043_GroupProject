import React from 'react';
import { Routes, Route } from 'react-router-dom';
import  InventoryView  from './views/InventoryView';
import  { ProductList }  from './components/products/ProductList';
import  { ProductDetails }  from './components/products/ProductDetails';
import  LoginForm  from './components/auth/LoginForm';
import  RegisterForm  from './components/auth/RegisterForm';

const RoutesConfig = () => {
    return (
        <Routes>
            <Route path="/" element={<ProductList />} />
            <Route path="/product/:id" element={<ProductDetails />} />
            <Route path="/inventory" element={<InventoryView />} />
            <Route path="/login" element={<LoginForm />} />
            <Route path="/register" element={<RegisterForm />} /> 
        </Routes>
    );
};

export default RoutesConfig;