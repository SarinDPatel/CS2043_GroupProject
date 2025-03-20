import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import RoutesConfig from './routes';
import { Provider } from 'react-redux';
import store from './store';
import './assets/styles/global.css';

const App = () => {
    return (
        <Provider store={store}>
            <Router>
                <RoutesConfig />
            </Router>
        </Provider>
    );
};

export default App;