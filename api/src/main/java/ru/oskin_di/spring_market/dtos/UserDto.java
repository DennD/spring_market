package ru.oskin_di.spring_market.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull(message = "Имя не может быть пустым")
    @Length(min = 3, max = 15, message = "Длина имени должна составлять 3-15 символов")
    private String username;

    @NotNull(message = "Название не может быть пустым")
    @Length(min = 3, max = 15, message = "Длина пароля должна составлять 3-15 символов")
    private String password;

    public UserDto(String username) {
        this.username = username;
    }

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
