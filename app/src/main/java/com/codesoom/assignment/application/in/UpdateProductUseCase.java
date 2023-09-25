package com.codesoom.assignment.application.in;

import com.codesoom.assignment.domain.Product;

public interface UpdateProductUseCase {
    Product updateProduct(Long id, Product source);
}
