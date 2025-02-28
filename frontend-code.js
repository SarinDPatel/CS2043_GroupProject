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
  
    render() {
      const { 
        filteredProducts, 
        categories, 
        selectedCategory, 
        searchQuery, 
        isLoading, 
        error 
      } = this.state;
  
      if (isLoading) return <div>Loading products...</div>;
      if (error) return <div className="error">{error}</div>;
  
      return (
        <div className="product-view">
          <h2>Game Store Inventory</h2>
          
          <div className="filters">
            <input
              type="text"
              placeholder="Search products..."
              value={searchQuery}
              onChange={this.handleSearch}
              className="search-input"
            />
            
            <div className="category-selector">
              <span>Category:</span>
              <select 
                value={selectedCategory}
                onChange={(e) => this.handleCategoryChange(e.target.value)}
              >
                <option value="All">All Categories</option>
                {categories.map(category => (
                  <option key={category.id} value={category.name}>
                    {category.name}
                  </option>
                ))}
              </select>
            </div>
          </div>
          
          <div className="product-grid">
            {filteredProducts.length > 0 ? (
              filteredProducts.map(product => (
                <ProductCard 
                  key={product.id} 
                  product={product} 
                  onEdit={this.handleEditProduct}
                  onDelete={this.handleDeleteProduct}
                />
              ))
            ) : (
              <div className="no-results">No products found</div>
            )}
          </div>
        </div>
      );
    }
  }

  // UserManagementComponent.js - Managing employees and permissions
class UserManagementComponent extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        users: [],
        roles: ['Customer', 'Employee', 'Manager'],
        selectedUser: null,
        isEditing: false,
        formData: {
          username: '',
          email: '',
          role: 'Customer',
          department: '',
          wage: '',
          schedule: ''
        }
      };
    }
  
    componentDidMount() {
      this.loadUsers();
    }
  
    loadUsers = async () => {
      try {
        const response = await api.get('/users');
        this.setState({ users: response.data });
      } catch (error) {
        console.error('Failed to load users', error);
      }
    }
  
    handleSelectUser = (user) => {
      this.setState({
        selectedUser: user,
        isEditing: true,
        formData: {
          username: user.username,
          email: user.email,
          role: user.role,
          department: user.department || '',
          wage: user.wage || '',
          schedule: user.schedule || ''
        }
      });
    }
  
    handleInputChange = (event) => {
      const { name, value } = event.target;
      this.setState(prevState => ({
        formData: {
          ...prevState.formData,
          [name]: value
        }
      }));
    }
  
    handleSubmit = async (event) => {
      event.preventDefault();
      const { formData, selectedUser, isEditing } = this.state;
      
      try {
        if (isEditing && selectedUser) {
          await api.put(`/users/${selectedUser.id}`, formData);
        } else {
          await api.post('/users', formData);
        }
        
        this.loadUsers();
        this.resetForm();
      } catch (error) {
        console.error('Error saving user', error);
      }
    }
  
    resetForm = () => {
      this.setState({
        selectedUser: null,
        isEditing: false,
        formData: {
          username: '',
          email: '',
          role: 'Customer',
          department: '',
          wage: '',
          schedule: ''
        }
      });
    }
  
  }