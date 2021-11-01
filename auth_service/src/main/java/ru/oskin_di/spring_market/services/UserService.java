package ru.oskin_di.spring_market.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.oskin_di.spring_market.models.User;

import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {

    void saveUser(String username, String password);

    Optional<User> findByUsername(String username);

    UserDetails loadUserByUsername(String username);
}
