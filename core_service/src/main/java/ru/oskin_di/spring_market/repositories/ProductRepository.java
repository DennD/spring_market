package ru.oskin_di.spring_market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.oskin_di.spring_market.models.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    Optional<Product> findByTitle(String title);

//    List<Product> findProductsByPriceIsAfter(BigDecimal minPrice);
//
//    List<Product> findProductsByPriceIsBefore(BigDecimal maxPrice);
//
//    List<Product> findProductsByPricetIsAfterAndPriceIsBefore(BigDecimal minPrice, BigDecimal maxPrice);

}
