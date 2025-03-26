import { useState } from 'react';
import { login } from '../../services/authService';
import { useNavigate } from 'react-router-dom';

const LoginForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await login(email, password);
            navigate('/dashboard'); // redirect after login
        } catch (err) {
            setError(err);
        }
    };

    return (
        <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded-1g shadow-md">
            <h2 className="text-2x1 font-semibold mb-4">Login</h2>
            {error && <p className="text-red-500">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                  type="email"
                  placeholder="Email"
                  className="w-full p-2 border rounded mb-3"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
                <input
                  type="password"
                  placeholder="Password"
                  className="w-full p-2 border rounded mb-3"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
                <button type="submit" className="w-full bg-flue-500 text-white p-2 rounded">
                    Login
                </button>
            </form>
        </div>
    );
};

export default LoginForm;