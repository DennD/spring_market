package ru.oskin_di.spring_market.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.oskin_di.spring_market.models.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.username = :username")
    @EntityGraph(value = "orders.for-front")
    List<Order> findAllByUsername(String username);

    Optional<Order> findOneByIdAndUsername(int id, String username);

}
