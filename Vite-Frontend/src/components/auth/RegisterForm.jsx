import { useState } from 'react';
import { register } from '../../services/authService';
import { useNavigate } from 'react-router-dom';

const RegisterForm = () => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: '',
    });
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await register(formData);
            navigate('/login'); // redirect to login page
        } catch (err) {
            setError(err);
        }
    };

    return (
        <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded-1g shadow-md">
            <h2 className="text-2x1 font-semibold mb-4">Register</h2>
            {error && <p className="text-red-500">{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                  type="text"
                  name="name"
                  placeholder="Name"
                  className="w-full p-2 border rounded mb-3"
                  value={formData.name}
                  onChange={handleChange}
                />
                <input
                  type="email"
                  name="email"
                  placeholder="Email"
                  className="w-full p-2 border rounded mb-3"
                  value={formData.email}
                  onChange={handleChange}
                />
                <input
                  type="password"
                  name="password"
                  placeholder="Password"
                  className="w-full p-2 border rounded mb-3"
                  value={formData.password}
                  onChange={handleChange}
                />
                <button type="submit" className="w-full bg-green-500 text-white p-2 rounded">
                    Register
                </button>
            </form>
        </div>
    );
};

export default RegisterForm;