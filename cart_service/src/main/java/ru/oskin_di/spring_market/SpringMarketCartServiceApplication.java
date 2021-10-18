package ru.oskin_di.spring_market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SpringMarketCartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMarketCartServiceApplication.class, args);
    }

}
