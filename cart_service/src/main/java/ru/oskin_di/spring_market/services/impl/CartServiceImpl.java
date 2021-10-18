package ru.oskin_di.spring_market.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.oskin_di.spring_market.dtos.ProductDto;
import ru.oskin_di.spring_market.exceptions.ResourceNotFoundException;
import ru.oskin_di.spring_market.services.CartService;
import ru.oskin_di.spring_market.utils.Cart;

import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${utils.cart.prefix}")
    private String cartPrefix;

    @Override
    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    @Override
    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Cart getCurrentCart(String cartKey) {
        if (!redisTemplate.hasKey(cartKey)) {
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    @Override
    public void addToCart(String cartKey, ProductDto productDto) {
        execute(cartKey, c -> {
                c.add(productDto);
        });
    }

    public void clearCart(String cartKey) {
        execute(cartKey, Cart::clear);
    }

    public void removeItemFromCart(String cartKey, int productId) {
        execute(cartKey, c -> c.remove(productId));
    }

    public void decrementItem(String cartKey, int productId) {
        execute(cartKey, c -> c.decrement(productId));
    }

    public void merge(String userCartKey, String guestCartKey) {
        Cart guestCart = getCurrentCart(guestCartKey);
        Cart userCart = getCurrentCart(userCartKey);
        userCart.merge(guestCart);
        updateCart(guestCartKey, guestCart);
        updateCart(userCartKey, userCart);
    }

    private void execute(String cartKey, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    @Override
    public void updateCart(String cartKey, Cart cart) {
        redisTemplate.opsForValue().set(cartKey, cart);
    }
}
