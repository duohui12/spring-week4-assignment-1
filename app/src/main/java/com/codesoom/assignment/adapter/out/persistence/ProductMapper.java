package com.codesoom.assignment.adapter.out.persistence;

import com.codesoom.assignment.domain.Product;
import org.springframework.stereotype.Component;

@Component
class ProductMapper {

    static Product entityToDomain(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .maker(productEntity.getMaker())
                .price(productEntity.getPrice())
                .imageUrl(productEntity.getImageUrl())
                .build();
    }

    static ProductEntity domainToEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .maker(product.getMaker())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }

}
