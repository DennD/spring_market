package ru.oskin_di.spring_market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMarketApplication.class, args);
    }

}
