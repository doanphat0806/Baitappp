package phat.code.baitappp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phat.code.baitappp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
