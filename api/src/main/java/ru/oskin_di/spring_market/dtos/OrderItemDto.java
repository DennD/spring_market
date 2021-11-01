package ru.oskin_di.spring_market.dtos;

import java.math.BigDecimal;

public class OrderItemDto {
    private int productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public OrderItemDto(int product_id, String productTitle, int quantity, BigDecimal pricePerProduct, BigDecimal price) {
        this.productId = product_id;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.price = price;
        this.pricePerProduct = pricePerProduct;
    }


    public void changeQuantity(int delta) {
        quantity += delta;
        if (quantity < 0) {
            quantity = 0;
        }
        price = pricePerProduct.multiply(new BigDecimal(quantity));
    }

    public OrderItemDto() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
