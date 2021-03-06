package ru.oskin_di.spring_market.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import ru.oskin_di.spring_market.dtos.CommentDto;
import ru.oskin_di.spring_market.exceptions.ResourceNotFoundException;
import ru.oskin_di.spring_market.models.Product;
import ru.oskin_di.spring_market.repositories.ProductRepository;
import ru.oskin_di.spring_market.repositories.specifications.ProductSpecifications;
import ru.oskin_di.spring_market.services.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final String FILTER_MIN_PRICE = "min_price";
    private static final String FILTER_MAX_PRICE = "max_price";
    private static final String FILTER_TITLE = "title";

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(String title, BigDecimal price) {
        productRepository.saveAndFlush(new Product(title, price));
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

//    @Override
//    public List<Product> findProductsLessPrice(BigDecimal maxPrice) {
//        return productRepository.findProductsByPriceIsAfter(maxPrice);
//    }
//
//    @Override
//    public List<Product> findProductsMorePrice(BigDecimal minPrice) {
//        return productRepository.findProductsByPriceIsBefore(minPrice);
//    }
//
//    @Override
//    public List<Product> findProductsLessPriceAndMorePrice(BigDecimal minPrice, BigDecimal maxPrice) {
//        return productRepository.findProductsByPricetIsAfterAndPriceIsBefore(minPrice, maxPrice);
//    }

    @Transactional
    @Override
    public void updateProduct(String title, BigDecimal price, int id) {
        Product product = findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));
        product.setPrice(price);
        product.setTitle(title);
    }


    @Override
    public Page<Product> findAll(int pageIndex, int pageSize, MultiValueMap<String, String> rqParams) {
        return productRepository.findAll(constructSpecification(rqParams), PageRequest.of(pageIndex, pageSize));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    private Specification<Product> constructSpecification(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);
        if (params.containsKey(FILTER_MIN_PRICE) && !params.getFirst(FILTER_MIN_PRICE).isBlank()) {
            BigDecimal minPrice = new BigDecimal(params.getFirst(FILTER_MIN_PRICE));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (params.containsKey(FILTER_MAX_PRICE) && !params.getFirst(FILTER_MAX_PRICE).isBlank()) {
            BigDecimal maxPrice = new BigDecimal(params.getFirst(FILTER_MAX_PRICE));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }
        if (params.containsKey(FILTER_TITLE) && !params.getFirst(FILTER_TITLE).isBlank()) {
            String title = params.getFirst(FILTER_TITLE);
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        return spec;
    }


}
