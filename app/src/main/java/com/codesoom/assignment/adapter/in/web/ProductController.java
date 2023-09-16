package com.codesoom.assignment.adapter.in.web;

import com.codesoom.assignment.utils.ProductMapper;
import com.codesoom.assignment.application.in.ProductUseCase;
import com.codesoom.assignment.domain.Product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;

    @GetMapping
    public List<Product> list() {
        return productUseCase.getProducts();
    }

    @GetMapping("{id}")
    public Product detail(@PathVariable Long id) {
        return productUseCase.getProduct(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody ProductDto productDto) {
        return productUseCase.createProduct(ProductMapper.dtoToDomain(productDto));
    }

    @PatchMapping("{id}")
    public Product patch(@PathVariable Long id, @RequestBody ProductDto source) {
        return productUseCase.updateProduct(id, ProductMapper.dtoToDomain(source));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productUseCase.deleteProduct(id);
    }

}
