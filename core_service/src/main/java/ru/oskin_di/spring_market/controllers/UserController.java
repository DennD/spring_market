package ru.oskin_di.spring_market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oskin_di.spring_market.dtos.ProfileDto;
import ru.oskin_di.spring_market.exceptions.ResourceNotFoundException;
import ru.oskin_di.spring_market.models.User;
import ru.oskin_di.spring_market.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ProfileDto aboutCurrentUser(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя. Имя пользователя: " + principal.getName()));
        return new ProfileDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    @PostMapping("/add_comment")
    public ResponseEntity<?> addComment(@RequestParam int id, @RequestParam String text, Principal principal) {
        userService.addComment(principal.getName(), id, text);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
