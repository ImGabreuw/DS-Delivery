package me.gabreuw.dsdelivery.backend.model.repositories;

import me.gabreuw.dsdelivery.backend.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByNameAsc();

}
