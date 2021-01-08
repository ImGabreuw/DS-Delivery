package me.gabreuw.dsdelivery.backend.controllers;


import me.gabreuw.dsdelivery.backend.model.dto.OrderDTO;
import me.gabreuw.dsdelivery.backend.model.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        final List<OrderDTO> orderDTOs = service.findAll();

        return ResponseEntity
                .ok()
                .body(orderDTOs);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(dto);
    }

    @PutMapping("/{id}/delivered")
    public ResponseEntity<OrderDTO> setDelivered(@PathVariable Long id) {
        final OrderDTO dto = service.setDelivered(id);

        return ResponseEntity
                .ok()
                .body(dto);
    }
}
