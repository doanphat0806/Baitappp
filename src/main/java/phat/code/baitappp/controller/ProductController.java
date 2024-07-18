package phat.code.baitappp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phat.code.baitappp.model.Category;
import phat.code.baitappp.model.Product;
import phat.code.baitappp.repository.CategoryRepository;
import phat.code.baitappp.repository.ProductRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public ResponseEntity findAll(){
        return new  ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Product product){
        productRepository.save(product);
        return new ResponseEntity<>("Them thanh cong",HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Product product){
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setAmount(product.getAmount());
            existingProduct.setCategory(product.getCategory());
            productRepository.save(existingProduct);
            return ResponseEntity.ok(existingProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        productRepository.deleteById(id);
        return new ResponseEntity<>("Delete thanh cong",HttpStatus.OK);
    }

    @GetMapping("/sortByPrice")
    public ResponseEntity<List<Product>> sortByPrice() {
        List<Product> products = productRepository.findAllByOrderByPriceDesc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/searchByPriceRange")
    public ResponseEntity<List<Product>> search(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/searchByCategory")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/searchByName")
    public ResponseEntity<List<Product>> findByName(@RequestParam String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
