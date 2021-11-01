package ru.oskin_di.spring_market.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.oskin_di.spring_market.dtos.CommentDto;
import ru.oskin_di.spring_market.models.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(int id);

    void save(String title, BigDecimal price);

    void deleteById(int id);

//    List<Product> findProductsLessPrice(BigDecimal maxPrice);
//
//    List<Product> findProductsMorePrice(BigDecimal minPrice);
//
//    List<Product> findProductsLessPriceAndMorePrice(BigDecimal minPrice, BigDecimal maxPrice);

    void updateProduct(String title, BigDecimal price, int id);

    Page<Product> findAll(int pageIndex, int pageSize, MultiValueMap<String, String> rqParams);

    Product save(Product product);

    Optional<Product> findByTitle(String title);

}
