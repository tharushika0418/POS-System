// package cmjd_106.project.demo.service;

// import cmjd_106.project.demo.entity.Order;
// import cmjd_106.project.demo.entity.Product;
// import cmjd_106.project.demo.repository.OrderRepository;
// import cmjd_106.project.demo.repository.ProductRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// /**
//  * Unit tests for OrderService
//  * Tests order management and POS transaction operations
//  */
// @ExtendWith(MockitoExtension.class)
// public class OrderServiceTest {

//     @Mock
//     private OrderRepository orderRepository;

//     @Mock
//     private ProductRepository productRepository;

//     @InjectMocks
//     private OrderServiceImpl orderService;

//     private Order testOrder;
//     private Product testProduct;

//     @BeforeEach
//     public void setUp() {
//         testProduct = new Product();
//         testProduct.setId(1L);
//         testProduct.setName("Test Product");
//         testProduct.setPrice(50.0);
//         testProduct.setQuantity(100);

//         testOrder = new Order();
//         testOrder.setId(1L);
//         testOrder.setTotalPrice(0.0);
//         testOrder.setOrderDate(LocalDateTime.now());
//         testOrder.setOrdered_products(new ArrayList<>());
//     }

//     @Test
//     public void testGetAllOrders() {
//         // Arrange
//         Order order2 = new Order();
//         order2.setId(2L);
//         order2.setTotalPrice(100.0);
        
//         List<Order> orders = Arrays.asList(testOrder, order2);
//         when(orderRepository.findAll()).thenReturn(orders);

//         // Act
//         List<Order> result = orderService.getAllOrders();

//         // Assert
//         assertNotNull(result);
//         assertEquals(2, result.size());
//         verify(orderRepository, times(1)).findAll();
//     }

//     @Test
//     public void testGetOrderById() {
//         // Arrange
//         when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));

//         // Act
//         Order result = orderService.getOrderById(1L);

//         // Assert
//         assertNotNull(result);
//         assertEquals(0.0, result.getTotalPrice());
//         verify(orderRepository, times(1)).findById(1L);
//     }

//     @Test
//     public void testCreateOrder() {
//         // Arrange
//         when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

//         // Act
//         Order result = orderService.createOrder(testOrder);

//         // Assert
//         assertNotNull(result);
//         assertNotNull(result.getOrderDate());
//         verify(orderRepository, times(1)).save(testOrder);
//     }

//     @Test
//     public void testAddProductsToOrder() {
//         // Arrange
//         when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
//         when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
//         when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

//         // Act
//         Order result = orderService.addProductsToOrder(1L, 1L, 2);

//         // Assert
//         assertNotNull(result);
//         assertEquals(1, result.getOrdered_products().size());
//         assertEquals(100.0, result.getTotalPrice()); // 50.0 * 2
//         verify(orderRepository, times(1)).findById(1L);
//         verify(productRepository, times(1)).findById(1L);
//         verify(orderRepository, times(1)).save(any(Order.class));
//     }

//     @Test
//     public void testAddProductsToOrder_OrderNotFound() {
//         // Arrange
//         when(orderRepository.findById(999L)).thenReturn(Optional.empty());

//         // Act
//         Order result = orderService.addProductsToOrder(999L, 1L, 2);

//         // Assert
//         assertNull(result);
//         verify(orderRepository, times(1)).findById(999L);
//         verify(productRepository, never()).findById(any());
//     }

//     @Test
//     public void testAddProductsToOrder_ProductNotFound() {
//         // Arrange
//         when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
//         when(productRepository.findById(999L)).thenReturn(Optional.empty());

//         // Act
//         Order result = orderService.addProductsToOrder(1L, 999L, 2);

//         // Assert
//         assertNull(result);
//         verify(orderRepository, times(1)).findById(1L);
//         verify(productRepository, times(1)).findById(999L);
//     }

//     @Test
//     public void testDeleteProductFromOrder() {
//         // Arrange
//         testOrder.getOrdered_products().add(testProduct);
//         testOrder.setTotalPrice(50.0);
        
//         when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
//         when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
//         when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

//         // Act
//         Order result = orderService.deleteProductFromOrder(1L, 1L);

//         // Assert
//         assertNotNull(result);
//         assertEquals(0, result.getOrdered_products().size());
//         assertEquals(0.0, result.getTotalPrice());
//         verify(orderRepository, times(1)).findById(1L);
//         verify(productRepository, times(1)).findById(1L);
//         verify(orderRepository, times(1)).save(any(Order.class));
//     }

//     @Test
//     public void testUpdateOrder() {
//         // Arrange
//         Order updatedOrder = new Order();
//         updatedOrder.setTotalPrice(150.0);
//         updatedOrder.setOrderDate(LocalDateTime.now());
        
//         when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));
//         when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

//         // Act
//         Order result = orderService.updateOrder(1L, updatedOrder);

//         // Assert
//         assertNotNull(result);
//         assertEquals(150.0, result.getTotalPrice());
//         verify(orderRepository, times(1)).findById(1L);
//         verify(orderRepository, times(1)).save(any(Order.class));
//     }

//     @Test
//     public void testDeleteOrder() {
//         // Arrange
//         doNothing().when(orderRepository).deleteById(1L);

//         // Act
//         orderService.deleteOrder(1L);

//         // Assert
//         verify(orderRepository, times(1)).deleteById(1L);
//     }
// }