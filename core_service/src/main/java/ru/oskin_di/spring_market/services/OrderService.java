package ru.oskin_di.spring_market.services;

import org.springframework.stereotype.Service;
import ru.oskin_di.spring_market.dtos.OrderDetailsDto;
import ru.oskin_di.spring_market.models.Order;

import java.security.Principal;
import java.util.List;

@Service
public interface OrderService {

    void createOrder(Principal principal, OrderDetailsDto orderDetailsDto);

    List<Order> findAllByUsername(String username);

}
