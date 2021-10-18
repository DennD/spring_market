package ru.oskin_di.spring_market.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.oskin_di.spring_market.dtos.CommentDto;
import ru.oskin_di.spring_market.models.Product;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    List<Product> findAll();

    List<CommentDto> getComments(int id);

    Optional<Product> findById(int id);

    void save(String title, int cost);

    void deleteById(int id);

    List<Product> findProductsLessCost(int maxCost);

    List<Product> findProductsMoreCost(int minCost);

    List<Product> findProductsLessCostAndMoreCost(int minCost, int maxCost);

    void updateProduct(String title, int cost, int id);

    Page<Product> findAll(int pageIndex, int pageSize, MultiValueMap<String, String> rqParams);

    Product save(Product product);

    Optional<Product> findByTitle(String title);

}
