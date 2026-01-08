package cmjd_106.project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cmjd_106.project.demo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
