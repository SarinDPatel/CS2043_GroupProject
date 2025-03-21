const ProductDetails = ({ product, onClose }) => {
    if (!product) return null;
    return (
        <div className="fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center">
            <div className="bg-white p-6 rounded-lg w-96">
                <img src={product.image} alt={product.name} className="w-full h-48 object-cover rounded-md" />
                <h2 className ="text-2x1 font-bold mt-4">{product.name}</h2>
                <p className="text-gray-700 mt-2">{product.description}</p>
                <p className="text-gray-600 font-bold mt-2">Price: ${product.price}</p>
                <button className="mt-4 bg-red-500 text-white px-4 py-2 rounded" onClick={onClose}>Close</button>
            </div>
        </div>
    );
};

export { ProductDetails };