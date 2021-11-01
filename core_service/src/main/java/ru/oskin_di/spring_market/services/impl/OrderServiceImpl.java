package ru.oskin_di.spring_market.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oskin_di.spring_market.dtos.CartDto;
import ru.oskin_di.spring_market.dtos.OrderDetailsDto;
import ru.oskin_di.spring_market.dtos.OrderDto;
import ru.oskin_di.spring_market.dtos.OrderItemDto;
import ru.oskin_di.spring_market.exceptions.ResourceNotFoundException;
import ru.oskin_di.spring_market.integrations.CartServiceIntegration;
import ru.oskin_di.spring_market.models.Order;
import ru.oskin_di.spring_market.models.OrderItem;
import ru.oskin_di.spring_market.repositories.OrderRepository;
import ru.oskin_di.spring_market.services.OrderService;
import ru.oskin_di.spring_market.services.ProductService;
import ru.oskin_di.spring_market.utils.Converter;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;
    private final Converter converter;

    @Transactional
    @Override
    public Order createOrder(String username, OrderDetailsDto orderDetailsDto) {
        CartDto cart = cartServiceIntegration.getUserCartDto(username);
        Order order = new Order();
        order.setUsername(username);
        order.setPrice(cart.getTotalPrice());
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDto i : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(i.getPrice());
            orderItem.setPricePerProduct(i.getPricePerProduct());
            orderItem.setQuantity(i.getQuantity());
            orderItem.setProduct(productService.findById(i.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти продукт при оформлении заказа. ID продукта: " + i.getProductId())));
            items.add(orderItem);
        }
        order.setItems(items);
        orderRepository.save(order);
        cartServiceIntegration.clearUserCart(username);
        return order;
    }

    @Override
    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }

    @Override
    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<OrderDto> findDtoByIdAndUsername(int id, String username) {
        return orderRepository.findOneByIdAndUsername(id, username).map(o -> converter.orderToDto(o));
    }
}
