import Controller from './BaseController';

export default class InventoryController extends Controller {
    constructor(productService) {
        super();
        this.productService = productService;
    }

    loadProducts() {
        return this.productService.getProducts();
    }

    updateProduct(productId, productData) {
        return this.productService.updateProduct(productId, productData);
    }

    deleteProduct(productId) {
        return this.productService.deleteProduct(productId);
    }

    createProduct(productData) {
        return this.productService.createProduct(productData);
    }
}