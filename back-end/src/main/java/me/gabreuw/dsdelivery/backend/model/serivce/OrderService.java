package me.gabreuw.dsdelivery.backend.model.serivce;

import me.gabreuw.dsdelivery.backend.model.dto.OrderDTO;
import me.gabreuw.dsdelivery.backend.model.dto.ProductDTO;
import me.gabreuw.dsdelivery.backend.model.entities.Order;
import me.gabreuw.dsdelivery.backend.model.entities.Product;
import me.gabreuw.dsdelivery.backend.model.enums.OrderStatus;
import me.gabreuw.dsdelivery.backend.model.repositories.OrderRepository;
import me.gabreuw.dsdelivery.backend.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        final List<Order> orders = orderRepository.findOrdersWithProducts();

        return orders
                .stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order(
                null,
                dto.getAddress(),
                dto.getLatitude(),
                dto.getLongitude(),
                Instant.now(),
                OrderStatus.PENDING
        );

        for (ProductDTO productDTO : dto.getProductDTOs()) {
            Product product = productRepository.getOne(productDTO.getId());

            order.getProducts().add(product);
        }

        return new OrderDTO(
                orderRepository.save(order)
        );
    }

    @Transactional
    public OrderDTO setDelivered(Long id) {
        Order order = orderRepository.getOne(id);

        order.setStatus(OrderStatus.DELIVERED);

        return new OrderDTO(
                orderRepository.save(order)
        );
    }
}
