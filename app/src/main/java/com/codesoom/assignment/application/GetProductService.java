package com.codesoom.assignment.application;

import com.codesoom.assignment.application.in.GetProductUseCase;
import com.codesoom.assignment.application.out.GetProductPort;
import com.codesoom.assignment.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetProductService implements GetProductUseCase {

    private final GetProductPort getProductPort;

    public Product getProduct(Long id) {
        return getProductPort.findById(id);
    }

}
