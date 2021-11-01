package ru.oskin_di.spring_market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.oskin_di.spring_market.dtos.UserDto;
import ru.oskin_di.spring_market.exceptions.MarketError;
import ru.oskin_di.spring_market.services.UserService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class RegController {

    private final UserService userService;

    @PostMapping("/reg")
    public ResponseEntity<?> save(@RequestBody UserDto userDto) {
        if (userService.findByUsername(userDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new MarketError("Пользователь с таким именем уже есть"), HttpStatus.BAD_REQUEST);
        }

        userService.saveUser(userDto.getUsername(), userDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

