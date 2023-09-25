package com.codesoom.assignment.adapter.in.web;

import com.codesoom.assignment.application.in.*;
import com.codesoom.assignment.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
@RequiredArgsConstructor
class ProductController {

    private final GetProductUseCase getProductUseCase;
    private final GetProductListUseCase getProductListUseCase;
    private final RegisterProductUseCase registerProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    @GetMapping
    List<Product> list() {
        return getProductListUseCase.getProducts();
    }

    @GetMapping("{id}")
    Product detail(@PathVariable Long id) {
        return getProductUseCase.getProduct(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Product create(@RequestBody RegisterProductWebData product) {
        return registerProductUseCase.registerProduct(ProductMapper.registerDtoToDomain(product));
    }

    @PatchMapping("{id}")
    Product patch(@PathVariable Long id, @RequestBody UpdateProductWebData source) {
        return updateProductUseCase.updateProduct(id, ProductMapper.updateDtoToDomain(source));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        deleteProductUseCase.deleteProduct(id);
    }

}


//private default protected public
//
