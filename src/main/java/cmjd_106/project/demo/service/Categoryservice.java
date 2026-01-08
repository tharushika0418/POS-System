package cmjd_106.project.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cmjd_106.project.demo.entity.Category;

@Service
public interface Categoryservice {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);

    
}
