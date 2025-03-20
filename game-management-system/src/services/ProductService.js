import { Product, Game, Merchandise } from '../models/ProductModel';
import apiClient from '../services/api';
import { createSlice } from '@reduxjs/toolkit';

// Product reducer for Redux store
const initialState = {
    products: [],
    loading: false,
    error: null
};

export const productSlice = createSlice({
    name: 'products',
    initialState,
    reducers: {
        startLoading: (state) => {
            state.loading = true;
        },
        loadProductsSuccess: (state, action) => {
            state.loading = false;
            state.products = action.payload;
            state.error = null;
        },
        loadProductsFailure: (state, action) => {
            state.loading = false;
            state.error = action.payload;
        }
    }
});

export const { startLoading, loadProductsSuccess, loadProductsFailure } = productSlice.actions;
export const productReducer = productSlice.reducer;

export default class ProductService {
    constructor(apiClient) {
        this.apiClient = apiClient;
    }

    async getProducts() {
        try {
            const response = await this.apiClient.get('/products');
            return response.data.map(product => {
                if (product.category === 'game') {
                    return new Game(
                        product.id,
                        product.name,
                        product.price,
                        product.stock,
                        product.console,
                        product.warrantyAvailable
                    );
                } else if (product.category === 'merchandise') {
                    return new Merchandise(
                        product.id,
                        product.name,
                        product.price,
                        product.stock,
                        product.type
                    );
                } else {
                    return new Product(
                        product.id,
                        product.name,
                        product.price,
                        product.stock,
                        product.category
                    );
                }
            });
        } catch (error) {
            console.error('Error fetching products:', error);
            return [];
        }
    }

    async updateProduct(productId, productData) {
        try {
            const response = await this.apiClient.put(`/products/${productId}`, productData);
            return response.data;
        } catch (error) {
            console.error(`Error updating product ${productId}:`, error);
            return false;
        }
    }

    async deleteProduct(productId) {
        try {
            await this.apiClient.delete(`/products/${productId}`);
            return true;
        } catch (error) {
            console.error(`Error deleting product ${productId}:`, error);
            return false;
        }
    }

    async createProduct(productData) {
        try {
            const response = await this.apiClient.post('/products', productData);
            const data = response.data;
            
            if (data.category === 'game') {
                return new Game(
                    data.id,
                    data.name,
                    data.price,
                    data.stock,
                    data.console,
                    data.warrantyAvailable
                );
            } else if (data.category === 'merchandise') {
                return new Merchandise(
                    data.id,
                    data.name,
                    data.price,
                    data.stock,
                    data.type
                );
            } else {
                return new Product(
                    data.id,
                    data.name,
                    data.price,
                    data.stock,
                    data.category
                );
            }
        } catch (error) {
            console.error('Error creating product:', error);
            return null;
        }
    }
}

// Create and export an instance separately
export const productService = new ProductService(apiClient); 

