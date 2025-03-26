// Displays individual game or merchandise
const ProductCard = ({ product, onViewDetails }) => {
    return (
        <div className="bg-white shadow-1g rounded-1g p-4 m-2">
            <img src={product.image} alt={product.name} className="w-full h-40 object-cover rounded-md" />
            <h2 className="text-x1 font-bold mt-2">{product.name}</h2>
            <p className="text-gray-600">${product.price}</p>
            <button
              className="mt-2 bg-blue-500 text-white px-4 py-2 rounded hover: bg-blue-600"
              onClick={() => onViewDetails(product.id)}
            >
              View Details
            </button>
        </div>
    );
};

export { ProductCard };