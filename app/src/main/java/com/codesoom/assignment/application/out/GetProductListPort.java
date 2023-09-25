package com.codesoom.assignment.application.out;

import com.codesoom.assignment.domain.Product;

import java.util.List;

public interface GetProductListPort {
    List<Product> findAll();
}
