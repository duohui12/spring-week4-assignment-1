package com.codesoom.assignment.application;

import com.codesoom.assignment.application.in.UpdateProductUseCase;
import com.codesoom.assignment.application.out.GetProductPort;
import com.codesoom.assignment.application.out.SaveProductPort;
import com.codesoom.assignment.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UpdateProductService implements UpdateProductUseCase {

    private final SaveProductPort saveProductPort;
    private final GetProductPort getProductPort;

    @Override
    public Product updateProduct(Long id, Product source) {
        Product product = getProductPort.findById(id);

        product.change(source.getName(), source.getMaker(), source.getPrice(), source.getImageUrl());

        //도메인 모델과 엔티티 모델을 분리해서 사용할 경우 변경감지가 되지 않음.
        return saveProductPort.save(product);
    }
}
