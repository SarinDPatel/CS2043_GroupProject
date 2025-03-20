export class Product {
    constructor(id, name, price, stock, category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category; // 'game' or 'merchandise'
    }
}

export class Game extends Product {
    constructor(id, name, price, stock, console, warrantyAvailable) {
        super(id, name, price, stock, 'game');
        this.console = console; // e.g., PS5, Xbox
        this.warrantyAvailable = warrantyAvailable; // Boolean
    }
}

export class Merchandise extends Product {
    constructor(id, name, price, stock, type) {
        super(id, name, price, stock, 'merchandise');
        this.type = type; // e.g., T-shirt, collectible
    }
}

// Default export for backward compatibility
export default Product;