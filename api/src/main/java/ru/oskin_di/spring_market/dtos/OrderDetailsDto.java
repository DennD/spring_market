package ru.oskin_di.spring_market.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class OrderDetailsDto {

    @NotNull(message = "Поле номер телефона не может быть пустым")
    @Length(min = 10, max = 12, message = "Длина номера телефона должна составлять 10-12 символов")
    private String phone;

    @NotNull(message = "Адресс не может быть пустым")
    @Length(min = 3, max = 255, message = "Длина адреса должна составлять 3-255 символов")
    private String address;

    public OrderDetailsDto() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
