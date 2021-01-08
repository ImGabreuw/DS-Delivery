package me.gabreuw.dsdelivery.backend.controllers;

import me.gabreuw.dsdelivery.backend.model.dto.ProductDTO;
import me.gabreuw.dsdelivery.backend.model.serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        final List<ProductDTO> productDTOs = service.findAll();

        return ResponseEntity
                .ok()
                .body(productDTOs);
    }

}
