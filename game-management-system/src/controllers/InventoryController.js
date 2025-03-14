// Handles filtering, discounts, warranties, voiding items
class InventoryController {
    constructor() {
        this.products = [];
    }

    addProduct(product) {
        this.product.push(product);
    }

    filterByCategory(category) {
        return this.products.filter(p => p.category === category);
    }

    filterByConsole(console) {
        return this.products.filter(p => p.console === console);
    }

    applyDiscount(productId, discountPercentage) {
        let product = this.products.find(p => p.id === productId);
        if (product) {
            product.price -= produce.price * (discountPercentage / 100);
        }
    }

    voidItem(productId) {
        let productIndex = this.products.findIndex(p => p.id === productId);
        if(productIndex !== -1) {
            this.products.splice(productIndex, 1); // remove from inventory
        }
    }
}