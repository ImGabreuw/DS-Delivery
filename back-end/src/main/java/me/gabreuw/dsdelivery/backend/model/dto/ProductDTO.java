package me.gabreuw.dsdelivery.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.gabreuw.dsdelivery.backend.model.entities.Product;

import java.io.Serializable;

// LOMBOK
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO implements Serializable {

    private Long id;

    private Double price;

    private String name;
    private String description;
    private String imageUri;

    public ProductDTO(Product product) {
        this(
                product.getId(),
                product.getPrice(),
                product.getName(),
                product.getDescription(),
                product.getImageUri()
        );
    }
}
