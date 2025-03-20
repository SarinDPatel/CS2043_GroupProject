import ProductModel from '../models/ProductModel';
import apiClient from '../services/api';

const productService = new ProductService(apiClient);

export default class ProductService {
    constructor(apiClient) {
        this.apiClient = apiClient;
    }

    async getProducts() {
        try {
            const response = await this.apiClient.get('/products');
            return response.data.map(product => new ProductModel(
                product.id,
                product.title,
                product.category,
                product.price,
                product.quantity,
                product.description
            ));
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
            return new ProductModel(
                response.data.id,
                response.data.title,
                response.data.category,
                response.data.price,
                response.data.quantity,
                response.data.description
            );
        } catch (error) {
            console.error('Error creating product:', error);
            return null;
        }
    }
} 

