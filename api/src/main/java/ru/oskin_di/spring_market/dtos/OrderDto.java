package ru.oskin_di.spring_market.dtos;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    private int id;
    private List<OrderItemDto> items;
    private String address;
    private String phone;
    private int price;

    public OrderDto(int id, List<OrderItemDto> items, String address, String phone, int price) {
        this.id = id;
        this.items = items;
        this.address = address;
        this.phone = phone;
        this.price = price;
    }

    public OrderDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}