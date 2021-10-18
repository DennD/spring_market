package ru.oskin_di.spring_market.services;

import org.springframework.stereotype.Service;
import ru.oskin_di.spring_market.dtos.ProductDto;
import ru.oskin_di.spring_market.utils.Cart;

@Service
public interface CartService {

    Cart getCurrentCart(String cartKey);

    String generateCartUuid();

    String getCartUuidFromSuffix(String suffix);

    void addToCart(String cartKey, ProductDto productDto);

    void clearCart(String cartKey);

    void removeItemFromCart(String cartKey, int productId);

    void decrementItem(String cartKey, int productId);

    void merge(String userCartKey, String guestCartKey);

    void updateCart(String cartKey, Cart cart);
}
