package ru.oskin_di.spring_market.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DataValidationExceptions extends RuntimeException {

    List<String> messages;

}
