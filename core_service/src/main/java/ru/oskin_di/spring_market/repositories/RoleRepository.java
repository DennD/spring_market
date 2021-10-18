package ru.oskin_di.spring_market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.oskin_di.spring_market.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
