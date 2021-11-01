package ru.oskin_di.spring_market.configs;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class PayPalConfig {

    @Value("${paypal.client-id}")
    private String clientId;

    @Value("${paypal.client-secret}")
    private String secret;

    @Value("${paypal.mode}")
    private String mode;

    private PayPalEnvironment payPalEnvironment;

    @PostConstruct
    private void init() {
        this.payPalEnvironment = new PayPalEnvironment.Sandbox(clientId, secret);
    }

    @Bean
    public PayPalHttpClient payPalHttpClient() {
        return new PayPalHttpClient(payPalEnvironment);
    }
}
