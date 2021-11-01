package ru.oskin_di.spring_market.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.oskin_di.spring_market.dtos.ProductDto;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {

    private final WebClient coreServiceWebClient;

    public ProductDto getProductById(int product_id) {
        ProductDto productDto = coreServiceWebClient.get()
                .uri("/api/v1/products/" + product_id)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
        return productDto;
    }
}
