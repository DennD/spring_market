package ru.oskin_di.spring_market.utils;

import org.springframework.stereotype.Component;
import ru.oskin_di.spring_market.dtos.OrderDto;
import ru.oskin_di.spring_market.dtos.OrderItemDto;
import ru.oskin_di.spring_market.dtos.ProductDto;
import ru.oskin_di.spring_market.models.Order;
import ru.oskin_di.spring_market.models.OrderItem;
import ru.oskin_di.spring_market.models.Product;

import java.util.stream.Collectors;

@Component
public class Converter {
    public ProductDto productToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

    public OrderItemDto orderItemToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(), orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }

    public OrderDto orderToDto(Order order) {
        return new OrderDto(order.getId(), order.getItems().stream().map(oi -> orderItemToDto(oi)).collect(Collectors.toList()), order.getAddress(), order.getPhone(), order.getPrice(), order.isPayment());

    }
}
