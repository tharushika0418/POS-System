package cmjd_106.project.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cmjd_106.project.demo.entity.Stock;
import cmjd_106.project.demo.entity.Product;
import cmjd_106.project.demo.repository.StockRepository;
import cmjd_106.project.demo.repository.ProductRepository;

@Service
public class StockServiceImpl implements StockService {
    
    @Autowired
    private StockRepository stockRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public Stock addStock(Stock stock) {
        stock.setTransactionDate(LocalDateTime.now());
        Stock savedStock = stockRepository.save(stock);
        
        // Update product quantity
        Product product = stock.getProduct();
        if (product != null) {
            if ("IN".equals(stock.getTransactionType())) {
                product.setQuantity(product.getQuantity() + stock.getQuantity());
            } else if ("OUT".equals(stock.getTransactionType())) {
                product.setQuantity(product.getQuantity() - stock.getQuantity());
            }
            productRepository.save(product);
        }
        
        return savedStock;
    }

    @Override
    public Stock updateStock(Long id, Stock stock) {
        Stock existingStock = stockRepository.findById(id).orElse(null);
        if (existingStock != null) {
            existingStock.setQuantity(stock.getQuantity());
            existingStock.setTransactionType(stock.getTransactionType());
            existingStock.setRemarks(stock.getRemarks());
            return stockRepository.save(existingStock);
        }
        return null;
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public List<Stock> getStockByProductId(Long productId) {
        return stockRepository.findByProductId(productId);
    }

    @Override
    public Integer getCurrentStockLevel(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return product != null ? product.getQuantity() : 0;
    }
}