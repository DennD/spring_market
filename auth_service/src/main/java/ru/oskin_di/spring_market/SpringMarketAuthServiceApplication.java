package ru.oskin_di.spring_market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SpringMarketAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMarketAuthServiceApplication.class, args);
    }

}
