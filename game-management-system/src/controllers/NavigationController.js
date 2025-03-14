// Controls views for Employees vs Customers
class NavigationController {
    constructor(user) {
        this.user = user;
    }

    navigate() {
        if (this.user.role === 'customer') {
            return 'Customer Dashboard';
        } else if (this.user.role === 'employee') {
            return 'Employee Management Panel';
        } else if (this.user.role === 'manager') {
            return 'Manager Dashboard';
        } else {
            return 'Access Denied';
        }
    }
}