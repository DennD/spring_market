package ru.oskin_di.spring_market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.oskin_di.spring_market.dtos.CommentDto;
import ru.oskin_di.spring_market.dtos.ProductDto;
import ru.oskin_di.spring_market.exceptions.DataValidationExceptions;
import ru.oskin_di.spring_market.exceptions.ResourceNotFoundException;
import ru.oskin_di.spring_market.models.Product;
import ru.oskin_di.spring_market.services.ProductService;
import ru.oskin_di.spring_market.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final Converter converter;

    @GetMapping
    public Page<ProductDto> findAll(
            @RequestParam(defaultValue = "1", name = "p") int pageIndex,
            @RequestParam MultiValueMap<String, String> params
    ) {
        System.out.println(pageIndex);
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10, params).map(p-> converter.productToDto(p));
    }

    @GetMapping("/{id}")
    public ProductDto showProduct(@PathVariable int id) {
        Product product = productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Prduct id = " + id+" not found"));
        return converter.productToDto(product);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Integer> deleteProduct(@RequestParam int id) {
        productService.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationExceptions(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }

        productService.save(productDto.getTitle(), productDto.getPrice());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public void updateProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationExceptions(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        productService.updateProduct(productDto.getTitle(), productDto.getPrice(), productDto.getId());
    }


}
