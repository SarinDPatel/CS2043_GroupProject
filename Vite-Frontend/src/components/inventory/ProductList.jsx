import React from 'react';

const ProductList = ({ products, onSort, onEdit, onDelete }) => {
    return (
        <div className="product-list">
            <div className="product-list-header">
                <div className="product-list-controls">
                    <button onClick={() => onSort('title')}>Sort by Title</button>
                    <button onClick={() => onSort('price')}>Sort by Price</button>
                    <button onClick={() => onSort('quantity')}>Sort by Quantity</button>
                </div>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map(product => (
                        <tr key={product.is}>
                            <td>{product.title}</td>
                            <td>{product.category}</td>
                            <td>${product.price.toFixed(2)}</td>
                            <td>{product.quantity}</td>
                            <td>
                                <button onClick={() => onEdit(product.id)}>Edit</button>
                                <button onClick={() => onDelete(product.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                    {products.length === 0 && (
                        <tr>
                            <td colSpan="5">No products found</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
};

export default ProductList;