package com.codesoom.assignment.application.out;

import com.codesoom.assignment.domain.Product;

public interface SaveProductPort {
    Product save(Product product);
}
