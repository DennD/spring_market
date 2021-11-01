package ru.oskin_di.spring_market.services;

import com.paypal.orders.OrderRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface PayPalService {
    OrderRequest createOrderRequest(int orderId);
}
