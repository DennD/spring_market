package ru.oskin_di.spring_market.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductDto {

    private int id;
    @NotNull(message = "Название не может быть пустым")
    @Length(min = 3, max = 255, message = "Длина названия должна составлять 3-255 символов")
    private String title;

    @Min(value = 1, message = "Цена должна быть больше 1")
    private BigDecimal price;

    public ProductDto(int id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public ProductDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setCost(BigDecimal cost) {
        this.price = price;
    }
}
