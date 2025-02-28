export default class Controller {
    constructor() {
        this.model = null;
        this.view = null;
    }

    initialize() {
        console.log("Controller initialized");
    }

    handleEvent(eventType, eventData) {
        console.log(`Handling event: ${eventType}`, eventData);
    }
}