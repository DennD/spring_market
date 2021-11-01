package ru.oskin_di.spring_market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.oskin_di.spring_market.dtos.OrderDetailsDto;
import ru.oskin_di.spring_market.dtos.OrderDto;
import ru.oskin_di.spring_market.dtos.StringResponse;
import ru.oskin_di.spring_market.services.OrderService;
import ru.oskin_di.spring_market.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Converter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StringResponse createOrder(@RequestBody OrderDetailsDto orderDetailsDto, @RequestHeader String username) {
        return new StringResponse(Integer.toString(orderService.createOrder(username, orderDetailsDto).getId()));
    }

    @GetMapping
    public List<OrderDto> getOrdersForCurrentUser(@RequestHeader String username) {
        return orderService.findAllByUsername(username).stream().map(o -> converter.orderToDto(o)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderDto getOrderForCurrentUser(@RequestHeader String username, @PathVariable int id) {
        return orderService.findDtoByIdAndUsername(id, username).get();
    }

}
