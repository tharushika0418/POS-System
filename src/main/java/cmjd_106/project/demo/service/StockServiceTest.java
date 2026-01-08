// package cmjd_106.project.demo.service;

// import cmjd_106.project.demo.entity.Stock;
// import cmjd_106.project.demo.entity.Product;
// import cmjd_106.project.demo.repository.StockRepository;
// import cmjd_106.project.demo.repository.ProductRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import java.time.LocalDateTime;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// /**
//  * Unit tests for StockService
//  * Tests stock management and transaction operations
//  */
// @ExtendWith(MockitoExtension.class)
// public class StockServiceTest {

//     @Mock
//     private StockRepository stockRepository;

//     @Mock
//     private ProductRepository productRepository;

//     @InjectMocks
//     private StockServiceImpl stockService;

//     private Stock testStock;
//     private Product testProduct;

//     @BeforeEach
//     public void setUp() {
//         testProduct = new Product();
//         testProduct.setId(1L);
//         testProduct.setName("Test Product");
//         testProduct.setQuantity(100);

//         testStock = new Stock();
//         testStock.setId(1L);
//         testStock.setProduct(testProduct);
//         testStock.setQuantity(10);
//         testStock.setTransactionType("IN");
//         testStock.setTransactionDate(LocalDateTime.now());
//         testStock.setRemarks("Initial stock");
//     }

//     @Test
//     public void testGetAllStock() {
//         // Arrange
//         Stock stock2 = new Stock();
//         stock2.setId(2L);
//         stock2.setQuantity(5);
//         stock2.setTransactionType("OUT");
        
//         List<Stock> stocks = Arrays.asList(testStock, stock2);
//         when(stockRepository.findAll()).thenReturn(stocks);

//         // Act
//         List<Stock> result = stockService.getAllStock();

//         // Assert
//         assertNotNull(result);
//         assertEquals(2, result.size());
//         verify(stockRepository, times(1)).findAll();
//     }

//     @Test
//     public void testGetStockById() {
//         // Arrange
//         when(stockRepository.findById(1L)).thenReturn(Optional.of(testStock));

//         // Act
//         Stock result = stockService.getStockById(1L);

//         // Assert
//         assertNotNull(result);
//         assertEquals(10, result.getQuantity());
//         assertEquals("IN", result.getTransactionType());
//         verify(stockRepository, times(1)).findById(1L);
//     }

//     @Test
//     public void testAddStock_StockIn() {
//         // Arrange
//         when(stockRepository.save(any(Stock.class))).thenReturn(testStock);
//         when(productRepository.save(any(Product.class))).thenReturn(testProduct);

//         // Act
//         Stock result = stockService.addStock(testStock);

//         // Assert
//         assertNotNull(result);
//         assertNotNull(result.getTransactionDate());
//         assertEquals(110, testProduct.getQuantity()); // 100 + 10
//         verify(stockRepository, times(1)).save(any(Stock.class));
//         verify(productRepository, times(1)).save(any(Product.class));
//     }

//     @Test
//     public void testAddStock_StockOut() {
//         // Arrange
//         testStock.setTransactionType("OUT");
//         when(stockRepository.save(any(Stock.class))).thenReturn(testStock);
//         when(productRepository.save(any(Product.class))).thenReturn(testProduct);

//         // Act
//         Stock result = stockService.addStock(testStock);

//         // Assert
//         assertNotNull(result);
//         assertEquals(90, testProduct.getQuantity()); // 100 - 10
//         verify(stockRepository, times(1)).save(any(Stock.class));
//         verify(productRepository, times(1)).save(any(Product.class));
//     }

//     @Test
//     public void testGetStockByProductId() {
//         // Arrange
//         List<Stock> stocks = Arrays.asList(testStock);
//         when(stockRepository.findByProductId(1L)).thenReturn(stocks);

//         // Act
//         List<Stock> result = stockService.getStockByProductId(1L);

//         // Assert
//         assertNotNull(result);
//         assertEquals(1, result.size());
//         assertEquals(testStock.getId(), result.get(0).getId());
//         verify(stockRepository, times(1)).findByProductId(1L);
//     }

//     @Test
//     public void testGetCurrentStockLevel() {
//         // Arrange
//         when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));

//         // Act
//         Integer result = stockService.getCurrentStockLevel(1L);

//         // Assert
//         assertNotNull(result);
//         assertEquals(100, result);
//         verify(productRepository, times(1)).findById(1L);
//     }

//     @Test
//     public void testGetCurrentStockLevel_ProductNotFound() {
//         // Arrange
//         when(productRepository.findById(999L)).thenReturn(Optional.empty());

//         // Act
//         Integer result = stockService.getCurrentStockLevel(999L);

//         // Assert
//         assertEquals(0, result);
//         verify(productRepository, times(1)).findById(999L);
//     }

//     @Test
//     public void testUpdateStock() {
//         // Arrange
//         Stock updatedStock = new Stock();
//         updatedStock.setQuantity(20);
//         updatedStock.setTransactionType("IN");
//         updatedStock.setRemarks("Updated stock");
        
//         when(stockRepository.findById(1L)).thenReturn(Optional.of(testStock));
//         when(stockRepository.save(any(Stock.class))).thenReturn(testStock);

//         // Act
//         Stock result = stockService.updateStock(1L, updatedStock);

//         // Assert
//         assertNotNull(result);
//         assertEquals(20, result.getQuantity());
//         assertEquals("IN", result.getTransactionType());
//         verify(stockRepository, times(1)).findById(1L);
//         verify(stockRepository, times(1)).save(any(Stock.class));
//     }

//     @Test
//     public void testDeleteStock() {
//         // Arrange
//         doNothing().when(stockRepository).deleteById(1L);

//         // Act
//         stockService.deleteStock(1L);

//         // Assert
//         verify(stockRepository, times(1)).deleteById(1L);
//     }
// }