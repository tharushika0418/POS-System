// package cmjd_106.project.demo.service;

// import cmjd_106.project.demo.entity.Product;
// import cmjd_106.project.demo.repository.ProductRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// /**
//  * Unit tests for ProductService
//  * Tests CRUD operations for products
//  */
// @ExtendWith(MockitoExtension.class)
// public class ProductServiceTest {

//     @Mock
//     private ProductRepository productRepository;

//     @InjectMocks
//     private ProductServiceImpl productService;

//     private Product testProduct;

//     @BeforeEach
//     public void setUp() {
//         testProduct = new Product();
//         testProduct.setId(1L);
//         testProduct.setName("Test Product");
//         testProduct.setPrice(100.0);
//         testProduct.setQuantity(50);
//     }

//     @Test
//     public void testGetAllProducts() {
//         // Arrange
//         Product product2 = new Product();
//         product2.setId(2L);
//         product2.setName("Product 2");
//         product2.setPrice(200.0);
        
//         List<Product> products = Arrays.asList(testProduct, product2);
//         when(productRepository.findAll()).thenReturn(products);

//         // Act
//         List<Product> result = productService.getAllProducts();

//         // Assert
//         assertNotNull(result);
//         assertEquals(2, result.size());
//         verify(productRepository, times(1)).findAll();
//     }

//     @Test
//     public void testGetProductById() {
//         // Arrange
//         when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

//         // Act
//         Product result = productService.getProductById(1L);

//         // Assert
//         assertNotNull(result);
//         assertEquals("Test Product", result.getName());
//         assertEquals(100.0, result.getPrice());
//         assertEquals(50, result.getQuantity());
//         verify(productRepository, times(1)).findById(1L);
//     }

//     @Test
//     public void testGetProductById_NotFound() {
//         // Arrange
//         when(productRepository.findById(999L)).thenReturn(Optional.empty());

//         // Act
//         Product result = productService.getProductById(999L);

//         // Assert
//         assertNull(result);
//         verify(productRepository, times(1)).findById(999L);
//     }

//     @Test
//     public void testCreateProduct() {
//         // Arrange
//         when(productRepository.save(any(Product.class))).thenReturn(testProduct);

//         // Act
//         Product result = productService.createProduct(testProduct);

//         // Assert
//         assertNotNull(result);
//         assertEquals("Test Product", result.getName());
//         verify(productRepository, times(1)).save(testProduct);
//     }

//     @Test
//     public void testUpdateProduct() {
//         // Arrange
//         Product updatedProduct = new Product();
//         updatedProduct.setName("Updated Product");
//         updatedProduct.setPrice(150.0);
        
//         when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
//         when(productRepository.save(any(Product.class))).thenReturn(testProduct);

//         // Act
//         Product result = productService.updateProduct(1L, updatedProduct);

//         // Assert
//         assertNotNull(result);
//         assertEquals("Updated Product", result.getName());
//         assertEquals(150.0, result.getPrice());
//         verify(productRepository, times(1)).findById(1L);
//         verify(productRepository, times(1)).save(any(Product.class));
//     }

//     @Test
//     public void testUpdateProduct_NotFound() {
//         // Arrange
//         Product updatedProduct = new Product();
//         when(productRepository.findById(999L)).thenReturn(Optional.empty());

//         // Act
//         Product result = productService.updateProduct(999L, updatedProduct);

//         // Assert
//         assertNull(result);
//         verify(productRepository, times(1)).findById(999L);
//         verify(productRepository, never()).save(any(Product.class));
//     }

//     @Test
//     public void testDeleteProduct() {
//         // Arrange
//         doNothing().when(productRepository).deleteById(1L);

//         // Act
//         productService.deleteProduct(1L);

//         // Assert
//         verify(productRepository, times(1)).deleteById(1L);
//     }
// }