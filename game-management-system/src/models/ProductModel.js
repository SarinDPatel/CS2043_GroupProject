import Model from './BaseModel';

export default class ProductModel extends Model {
    constructor(id = "", title = "", category = "", price = 0, quantity = 0, description = "") {
        super();
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    updateStock(quantity) {
        this.quantity = quantity;
        return true;
    }

    setPrice(newPrice) {
        this.price = newPrice;
        return true;
    }
}