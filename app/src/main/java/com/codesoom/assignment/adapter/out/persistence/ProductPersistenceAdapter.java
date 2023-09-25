package com.codesoom.assignment.adapter.out.persistence;

import com.codesoom.assignment.application.out.*;
import com.codesoom.assignment.domain.Product;
import com.codesoom.assignment.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
class ProductPersistenceAdapter implements GetProductPort
        , GetProductListPort
        , SaveProductPort
        , DeleteProductPort {

    private final ProductSpringDataRepository productSpringDataRepository;

    @Override
    public void delete(Product product) {
        productSpringDataRepository.delete(ProductMapper.domainToEntity(product));
    }

    @Override
    public List<Product> findAll() {
        return productSpringDataRepository.findAll().stream()
                .map(p -> ProductMapper.entityToDomain(p)).collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        ProductEntity productEntity = productSpringDataRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return ProductMapper.entityToDomain(productEntity);
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productSpringDataRepository.save(ProductMapper.domainToEntity(product));
        return ProductMapper.entityToDomain(productEntity);
    }

}

