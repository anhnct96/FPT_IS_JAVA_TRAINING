package vn.fis.training.ordermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fis.training.ordermanagement.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
