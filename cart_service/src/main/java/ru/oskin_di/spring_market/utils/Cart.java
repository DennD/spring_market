package ru.oskin_di.spring_market.utils;

import lombok.Data;
import ru.oskin_di.spring_market.dtos.OrderItemDto;
import ru.oskin_di.spring_market.dtos.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(ProductDto productDto) {
        for (OrderItemDto i : items) {
            if (i.getProductId() == productDto.getId()) {
                i.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new OrderItemDto(productDto.getId(), productDto.getTitle(), 1, productDto.getPrice(), productDto.getPrice()));
        recalculate();
    }


    public void decrement(int productId) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto i = iter.next();
            if (i.getProductId() == productId) {
                i.changeQuantity(-1);
                if (i.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(int productId) {
        items.removeIf(i -> i.getProductId() == productId);
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (OrderItemDto i : items) {
            totalPrice = totalPrice.add(i.getPrice());
        }
    }

    public void merge(Cart another) {
        for (OrderItemDto anotherItem : another.items) {
            boolean merged = false;
            for (OrderItemDto myItem : items) {
                if (myItem.getProductId() == anotherItem.getProductId()) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }
}
