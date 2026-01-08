package cmjd_106.project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cmjd_106.project.demo.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
