package phat.code.baitappp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import phat.code.baitappp.model.Category;
import phat.code.baitappp.model.Product;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findAllByOrderByPriceDesc();
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByNameContainingIgnoreCase(String name);
}
