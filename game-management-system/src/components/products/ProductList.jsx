// Displays a list of products (games/merchandise)
const ProductList = ({ products, onViewDetails }) => {
    return (
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            {products.map((product) => (
                <ProductCard key={product.id} product={product} onViewDetails={onViewDetails} />
            ))}
        </div>
    );
};

export { ProductList };