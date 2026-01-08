# POS-System-POSync
IJSE coursework-CMJD106

# POS System - Features

## System Features

### 1. User Authentication & Authorization
- User registration with email validation
- Secure login with JWT token authentication
- Password encryption using BCrypt
- Session management
- Protected routes and endpoints

### 2. Dashboard
- Real-time statistics display
- Total products count
- Total orders count
- Total users count
- Total categories count
- Quick action buttons for common tasks
- System status information

### 3. Product Management
- Add new products with details (name, price, quantity, category)
- Edit existing products
- Delete products
- Search products by name
- Filter products by category
- View product stock status (In Stock, Low Stock, Out of Stock)
- Product categorization
- Stock level indicators

### 4. Category Management
- Create product categories
- Edit category names
- Delete categories
- View all categories
- Assign products to categories

### 5. Order Management
- Create new orders
- Add products to orders
- Remove products from orders
- View order history
- Edit existing orders
- Delete orders
- Calculate order total automatically
- View order details (date, items, total price)
- Track number of items per order

### 6. Point of Sale (POS) Interface
- Interactive shopping cart
- Add products to cart
- Remove products from cart
- Update product quantities in cart
- Real-time total calculation
- Stock validation before adding to cart
- Search products
- Filter by category
- Checkout functionality
- Clear cart option
- Visual stock availability

### 7. User Management
- Create new users
- Edit user information
- Delete users
- View all users
- Username and email uniqueness validation
- Password validation (minimum length)

### 8. Responsive Design
- Mobile-friendly interface
- Tablet compatible
- Desktop optimized
- Bootstrap 5 styling
- Modern UI/UX design
- Smooth animations and transitions

### 9. Security Features
- JWT token-based authentication
- Automatic token refresh
- Protected routes
- Password encryption
- Secure API endpoints
- Automatic logout on token expiration
- CORS configuration

### 10. User Interface Features
- Professional navigation bar
- Icon-based menu items
- Alert messages for success/error
- Loading spinners
- Form validation
- Confirmation dialogs
- Modern gradient backgrounds
- Clean card-based layouts
- Bootstrap icons throughout

## Technical Specifications

### Frontend Technologies
- React 18
- React Router DOM (v6)
- Axios for HTTP requests
- Bootstrap 5
- Bootstrap Icons
- CSS3 animations

### Backend Technologies
- Spring Boot
- Spring Security
- JWT Authentication
- JPA/Hibernate
- MySQL Database
- BCrypt Password Encoder

### Key Functionalities
- RESTful API architecture
- CRUD operations for all entities
- Real-time data updates
- Form validation (client & server side)
- Error handling
- Session management
- Database persistence

## User Workflow

1. **Registration** → User creates account with username, email, password
2. **Login** → User logs in with credentials
3. **Dashboard** → View system statistics and quick actions
4. **Setup Categories** → Create product categories
5. **Add Products** → Add products with prices and stock
6. **POS Operations** → Make sales using shopping cart
7. **Order Management** → View and manage all orders
8. **User Management** → Admin manages system users


