package cmjd_106.project.demo.service;
import java.util.List;

import org.springframework.stereotype.Service;

import cmjd_106.project.demo.entity.Product;

@Service
public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);

}