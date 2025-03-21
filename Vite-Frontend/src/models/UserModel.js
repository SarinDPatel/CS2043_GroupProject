class User {
    constructor(id, username, password, role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role; // 'customer', 'employee', 'manager'
    }

    hasPermission(action) {
        const rolePermissions = {
            'customer' : ['viewProducts', 'purchase'],
            'employee': ['viewProducts', 'processSales'],
            'manager': ['viewProducts', 'processSales', 'manageInventory', 'manageUsers']
        };
        return rolePermissions[this.role]?.includes(action);
    }
}

class Customer extends User {
    constructor(id, username, password) {
        super(id, username, password, 'customer');
        this.purchaseHistory = [];
        this.wishlist = [];
    }
}

class Employee extends User {
    constructor(id, username, password, role) {
        super(id, username, password, role); // role can be 'employee' or 'manager'
    }
}