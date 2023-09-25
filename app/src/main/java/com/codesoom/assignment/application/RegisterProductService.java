package com.codesoom.assignment.application;

import com.codesoom.assignment.application.in.RegisterProductUseCase;
import com.codesoom.assignment.application.out.SaveProductPort;
import com.codesoom.assignment.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RegisterProductService implements RegisterProductUseCase {

    private final SaveProductPort saveProductPort;

    @Override
    public Product registerProduct(Product product) {
        return saveProductPort.save(product);
    }
}
