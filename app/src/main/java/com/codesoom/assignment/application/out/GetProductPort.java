package com.codesoom.assignment.application.out;

import com.codesoom.assignment.domain.Product;

public interface GetProductPort {
    Product findById(Long id);
}
