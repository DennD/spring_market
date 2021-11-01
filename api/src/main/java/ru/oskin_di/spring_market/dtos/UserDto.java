package ru.oskin_di.spring_market.dtos;

public class UserDto {

    private String username;

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
