package ru.oskin_di.spring_market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAspectJAutoProxy
@PropertySource("secret.properties")
public class SpringMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMarketApplication.class, args);
    }

}
