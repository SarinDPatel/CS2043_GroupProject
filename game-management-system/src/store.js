import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./reducers/userReducer";
import { productReducer } from "./services/ProductService"; 

const store = configureStore({
    reducer: {
        user: userReducer,
        products: productReducer,
    },
});

export default store;