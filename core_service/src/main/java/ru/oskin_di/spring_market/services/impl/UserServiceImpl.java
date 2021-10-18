package ru.oskin_di.spring_market.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oskin_di.spring_market.models.Comment;
import ru.oskin_di.spring_market.models.Permission;
import ru.oskin_di.spring_market.models.Role;
import ru.oskin_di.spring_market.models.User;
import ru.oskin_di.spring_market.repositories.CommentRepository;
import ru.oskin_di.spring_market.repositories.ProductRepository;
import ru.oskin_di.spring_market.repositories.RoleRepository;
import ru.oskin_di.spring_market.repositories.UserRepository;
import ru.oskin_di.spring_market.services.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    @Override
    public void addComment(String username, int product_id, String text) {
        Comment comment = new Comment();
        comment.setUser(userRepository.findByUsername(username).get());
        comment.setProduct(productRepository.getById(product_id));
        comment.setText(text);
        commentRepository.save(comment);
    }

    @Transactional
    public void saveUser(String username, String password) {
        userRepository.save(new User(username, bCryptPasswordEncoder.encode(password), Collections.singletonList(roleRepository.findByName("ROLE_USER"))));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), createAuthoritiesUser(user));
    }


    private Collection<GrantedAuthority> createAuthoritiesUser(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(role);
            for (Permission permission : role.getPermissions()) {
                authorities.add(permission);
            }
        }
        return authorities.stream().distinct().collect(Collectors.toList());
    }
}
