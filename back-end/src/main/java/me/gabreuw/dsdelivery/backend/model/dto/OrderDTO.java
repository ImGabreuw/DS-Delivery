package me.gabreuw.dsdelivery.backend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gabreuw.dsdelivery.backend.model.entities.Order;
import me.gabreuw.dsdelivery.backend.model.enums.OrderStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//LOMBOK
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO implements Serializable {

    private Long id;

    private String address;

    private Double latitude;
    private Double longitude;

    private Instant moment;

    private OrderStatus status;

    private List<ProductDTO> productDTOs = new ArrayList<>();

    public OrderDTO(Order order) {
        this(
                order.getId(),
                order.getAddress(),
                order.getLatitude(),
                order.getLongitude(),
                order.getMoment(),
                order.getStatus(),
                order.getProducts()
                        .stream()
                        .map(ProductDTO::new)
                        .collect(Collectors.toList())
        );
    }
}
