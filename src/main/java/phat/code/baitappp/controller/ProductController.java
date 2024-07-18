package phat.code.baitappp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phat.code.baitappp.model.Category;
import phat.code.baitappp.model.Product;
import phat.code.baitappp.repository.CategoryRepository;
import phat.code.baitappp.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(productDetails.getName());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setAmount(productDetails.getAmount());
            existingProduct.setCategory(productDetails.getCategory());
            productRepository.save(existingProduct);
            return ResponseEntity.ok(existingProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/sortByPrice")
    public List<Product> sortProductsByPrice() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    @GetMapping("/sortByAmount")
    public List<Product> sortProductsByAmount() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "amount"));
    }

    @GetMapping("/searchByPriceRange")
    public List<Product> searchByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/searchByCategory")
    public List<Product> searchByCategory(@RequestParam Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        return productRepository.findByCategory(category);
    }

    @GetMapping("/searchByName")
    public List<Product> searchByName(@RequestParam String name) {
        return productRepository.findByNameContaining(name);
    }
}
