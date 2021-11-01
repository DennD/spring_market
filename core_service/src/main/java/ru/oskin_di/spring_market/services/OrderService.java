package ru.oskin_di.spring_market.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oskin_di.spring_market.dtos.OrderDetailsDto;
import ru.oskin_di.spring_market.dtos.OrderDto;
import ru.oskin_di.spring_market.models.Order;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {

    Order createOrder(String username, OrderDetailsDto orderDetailsDto);

    List<Order> findAllByUsername(String username);

    Optional<Order> findById(int id);

    Optional<OrderDto> findDtoByIdAndUsername(int id, String username);

}
