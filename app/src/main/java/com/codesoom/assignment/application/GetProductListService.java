package com.codesoom.assignment.application;

import com.codesoom.assignment.application.in.GetProductListUseCase;
import com.codesoom.assignment.application.in.GetProductUseCase;
import com.codesoom.assignment.application.out.GetProductListPort;
import com.codesoom.assignment.application.out.GetProductPort;
import com.codesoom.assignment.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class GetProductListService implements GetProductListUseCase {

    private final GetProductListPort getProductListPort;

    @Override
    public List<Product> getProducts() {
        return getProductListPort.findAll();
    }
}
