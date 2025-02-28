class ProductView extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        products: [],
        filteredProducts: [],
        categories: [],
        selectedCategory: 'All',
        searchQuery: '',
        isLoading: true,
        error: null
      };
    }
  
    componentDidMount() {
      // Load products when component mounts
      this.fetchProducts();
      this.fetchCategories();
    }
  
    fetchProducts = async () => {
      try {
        // In a real implementation, this would fetch from your backend API
        const response = await api.get('/products');
        this.setState({ 
          products: response.data, 
          filteredProducts: response.data,
          isLoading: false 
        });
      } catch (error) {
        this.setState({ 
          error: 'Failed to load products', 
          isLoading: false 
        });
      }
    }
  
    fetchCategories = async () => {
      try {
        const response = await api.get('/categories');
        this.setState({ categories: response.data });
      } catch (error) {
        console.error('Failed to load categories', error);
      }
    }
  
    handleSearch = (event) => {
      const query = event.target.value.toLowerCase();
      this.setState({ searchQuery: query }, this.filterProducts);
    }
  
    handleCategoryChange = (category) => {
      this.setState({ selectedCategory: category }, this.filterProducts);
    }
  
    filterProducts = () => {
      const { products, selectedCategory, searchQuery } = this.state;
      
      let filtered = products;
      
      // Filter by category
      if (selectedCategory !== 'All') {
        filtered = filtered.filter(product => 
          product.category === selectedCategory
        );
      }
      
      // Filter by search query
      if (searchQuery) {
        filtered = filtered.filter(product => 
          product.title.toLowerCase().includes(searchQuery) ||
          product.description.toLowerCase().includes(searchQuery)
        );
      }
      
      this.setState({ filteredProducts: filtered });
    }
  
  }

  