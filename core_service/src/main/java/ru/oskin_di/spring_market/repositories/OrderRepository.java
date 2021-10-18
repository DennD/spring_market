package ru.oskin_di.spring_market.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.oskin_di.spring_market.models.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o where o.user.username = :username")
    @EntityGraph(value = "orders.for-front")
    List<Order> findAllByUsername(String username);

}
