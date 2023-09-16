package com.codesoom.assignment.utils;

import com.codesoom.assignment.adapter.in.web.ProductDto;
import com.codesoom.assignment.adapter.out.persistence.ProductEntity;
import com.codesoom.assignment.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product entityToDomain(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .maker(productEntity.getMaker())
                .price(productEntity.getPrice())
                .imageUrl(productEntity.getImageUrl())
                .build();
    }

    public static ProductEntity domainToEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .maker(product.getMaker())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public static Product dtoToDomain(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .maker(productDto.getMaker())
                .price(productDto.getPrice())
                .imageUrl(productDto.getImageUrl())
                .build();
    }

}
