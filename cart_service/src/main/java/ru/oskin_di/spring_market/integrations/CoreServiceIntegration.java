package ru.oskin_di.spring_market.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.oskin_di.spring_market.dtos.ProductDto;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {

    private final RestTemplate restTemplate;

    @Value("${integration.core-service.url}")
    private String coreServiceUrl;

    public ProductDto getProductById(int product_id) {
        return restTemplate.getForObject(coreServiceUrl + "/api/v1/products/" + product_id, ProductDto.class);
    }
}
