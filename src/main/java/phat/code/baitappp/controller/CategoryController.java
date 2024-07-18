package phat.code.baitappp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phat.code.baitappp.model.Category;
import phat.code.baitappp.repository.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity getAllCategories(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            return ResponseEntity.ok(category);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category category1 = categoryRepository.findById(id).orElse(null);
        if (category1 != null) {
            category1.setName(category.getName());
            categoryRepository.save(category1);
            return ResponseEntity.ok(category1);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
