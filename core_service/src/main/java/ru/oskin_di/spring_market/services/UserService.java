package ru.oskin_di.spring_market.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.oskin_di.spring_market.models.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    void saveUser(String username, String password);

    Optional<User> findByUsername(String username);

    UserDetails loadUserByUsername(String username);

    void addComment(String username, int product_id, String text);
}
