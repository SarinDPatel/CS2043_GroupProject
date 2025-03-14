class UserController {
    constructor() {
        this.users = [];
    }

    addUser(user) {
        this.users.push(user);
    }

    authenticate(username, password) {
        return this.users.find(user => user.username === username && user.password === password);
    }
}