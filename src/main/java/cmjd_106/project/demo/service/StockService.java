package cmjd_106.project.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cmjd_106.project.demo.entity.Stock;

@Service
public interface StockService {
    List<Stock> getAllStock();
    Stock getStockById(Long id);
    Stock addStock(Stock stock);
    Stock updateStock(Long id, Stock stock);
    void deleteStock(Long id);
    List<Stock> getStockByProductId(Long productId);
    Integer getCurrentStockLevel(Long productId);
}