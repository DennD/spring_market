package ru.oskin_di.spring_market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.oskin_di.spring_market.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    Optional<Product> findByTitle(String title);

    List<Product> findProductsByCostIsAfter(int minCost);

    List<Product> findProductsByCostIsBefore(int maxCost);

    List<Product> findProductsByCostIsAfterAndCostIsBefore(int minCost, int maxCost);

}
