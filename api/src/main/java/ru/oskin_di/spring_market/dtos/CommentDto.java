package ru.oskin_di.spring_market.dtos;

public class CommentDto {

    private String text;
    private String username;

    public CommentDto(String text, String username) {
        this.text = text;
        this.username = username;
    }

    public CommentDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
