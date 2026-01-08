package cmjd_106.project.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cmjd_106.project.demo.dto.StockDto;
import cmjd_106.project.demo.entity.Stock;
import cmjd_106.project.demo.entity.Product;
import cmjd_106.project.demo.service.StockService;
import cmjd_106.project.demo.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Stock> getAllStock() {
        return stockService.getAllStock();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        Stock stock = stockService.getStockById(id);
        if (stock != null) {
            return ResponseEntity.ok(stock);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/product/{productId}")
    public List<Stock> getStockByProduct(@PathVariable Long productId) {
        return stockService.getStockByProductId(productId);
    }

    @GetMapping("/product/{productId}/level")
    public ResponseEntity<Integer> getCurrentStockLevel(@PathVariable Long productId) {
        Integer level = stockService.getCurrentStockLevel(productId);
        return ResponseEntity.ok(level);
    }

    @PostMapping
    public ResponseEntity<Stock> addStock(@RequestBody StockDto stockDto) {
        Stock stock = new Stock();
        Product product = productService.getProductById(stockDto.getProductId());
        
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }
        
        stock.setProduct(product);
        stock.setQuantity(stockDto.getQuantity());
        stock.setTransactionType(stockDto.getTransactionType());
        stock.setRemarks(stockDto.getRemarks());
        
        Stock savedStock = stockService.addStock(stock);
        return ResponseEntity.ok(savedStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody StockDto stockDto) {
        Stock stock = new Stock();
        stock.setQuantity(stockDto.getQuantity());
        stock.setTransactionType(stockDto.getTransactionType());
        stock.setRemarks(stockDto.getRemarks());
        
        Stock updatedStock = stockService.updateStock(id, stock);
        if (updatedStock != null) {
            return ResponseEntity.ok(updatedStock);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok().build();
    }
}