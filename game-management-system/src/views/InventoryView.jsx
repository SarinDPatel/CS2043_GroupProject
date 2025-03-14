import React, { useState, useEffect } from 'react';
import ProductList from '../components/inventory/ProductList';
import ProductFilter from '../components/inventory/ProductFilter';
import CategorySelector from '../components/inventory/CategorySelector';

const InventoryView = ({ controller }) => {
    const [products, setProducts] = useState([]);
    const [filteredProducts, setFilteredProducts] = useState([]);
    const [searchQuery, setSearchQuery] = useState('');
    const [selectedCategory, setSelectedCategory] = useState('');

    useEffect(() => {
        const loadProductData = async () => {
            const productData = await controller.loadProducts();
            setProducts(productData);
            setFilteredProducts(productData);
        };

        loadProductData();
    }, [controller]);

    const handleSearch = (query) => {
        setSearchQuery(query);
        filteredProducts(query, selectedCategory);
    };

    const handleCategoryChange = (category) => {
        setSelectedCategory(category);
        filteredProducts(searchQuery, category);
    };

    const filterProducts = (query, category) => {
        let filtered = [...products];

        if (query) {
            filtered = filtered.filter(product =>
                product.title.toLowerCase().includes(query.toLowerCase()) ||
                product.description.toLowerCase().includes(query.toLowerCase())
            );
        }

        if (category) {
            filtered = filtered.filter(product => product.category === category);
        }

        setFilteredProducts(filtered);
    };

    const handleSort = (criteria) => {
        const sorted = [...filteredProducts].sort((a, b) => {
            if (criteria === 'title') {
                return a.title.localeCompare(b.title);
            } else if (criteria === 'price') {
                return a.price - b.price;
            } else if (criteria === 'quantity') {
                return a.quantity - b.quantity;
            }
            return 0;
        });

        setFilteredProducts(sorted);
    };

    return (
        <div className="inventory-view">
            <h1>Inventory Management</h1>

            <div className="inventory-controls">
                <ProductFilter onSearch={handleSearch} />
                <CategorySelector
                    onCategoryChange={handleCategoryChange}
                    categories={[...new Set(products.map(p => p.category))]}
                />
            </div>

            <ProductList
                products={filteredProducts}
                onSort={handleSort}
                onEdit={(productId) => {/* handle edit */}}
                onDelete={async (productId) => {
                    const success = await controller.deleteProduct(productId);
                    if (success) {
                        setProducts(products.filter(p => p.id !== productId));
                        setFilteredProducts(filteredProducts.filter(p => p.id !== productId));
                    }
                }}
            />
        </div>
    );
};

export default InventoryView;