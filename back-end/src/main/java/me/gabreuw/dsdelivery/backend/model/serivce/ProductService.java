package me.gabreuw.dsdelivery.backend.model.serivce;

import me.gabreuw.dsdelivery.backend.model.dto.ProductDTO;
import me.gabreuw.dsdelivery.backend.model.entities.Product;
import me.gabreuw.dsdelivery.backend.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        final List<Product> products = repository.findAllByOrderByNameAsc();

        return products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

}
