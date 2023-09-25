package com.codesoom.assignment.application.in;

import com.codesoom.assignment.domain.Product;

public interface GetProductUseCase {
    Product getProduct(Long id);
}
