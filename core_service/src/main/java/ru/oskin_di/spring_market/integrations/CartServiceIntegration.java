package ru.oskin_di.spring_market.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.oskin_di.spring_market.dtos.CartDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    @Value("${integration.cart-service.url}")
    private String cartServiceUrl;

    public CartDto getUserCartDto(String username) {
        if (username == null) {
            throw new RuntimeException("ERROR!!!");
        }
        CartDto cartDto = cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
        return cartDto;
    }

    public void clearUserCart(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/0/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}

