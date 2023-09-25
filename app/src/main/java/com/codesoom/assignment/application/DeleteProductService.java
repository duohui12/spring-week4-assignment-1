package com.codesoom.assignment.application;

import com.codesoom.assignment.application.in.DeleteProductUseCase;
import com.codesoom.assignment.application.out.DeleteProductPort;
import com.codesoom.assignment.application.out.GetProductPort;
import com.codesoom.assignment.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeleteProductService implements DeleteProductUseCase {

    private final DeleteProductPort deleteProductPort;
    private final GetProductPort getProductPort;

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductPort.findById(id);
        deleteProductPort.delete(product);
    }
}
