package com.codesoom.assignment.adapter.in.web;

import com.codesoom.assignment.domain.Product;
import org.springframework.stereotype.Component;

@Component
class ProductMapper {

    static Product registerDtoToDomain(RegisterProductWebData productWebData) {
        return Product.builder()
                .name(productWebData.getName())
                .maker(productWebData.getMaker())
                .price(productWebData.getPrice())
                .imageUrl(productWebData.getImageUrl())
                .build();
    }

    static Product updateDtoToDomain(UpdateProductWebData productWebData) {
        return Product.builder()
                .name(productWebData.getName())
                .maker(productWebData.getMaker())
                .price(productWebData.getPrice())
                .imageUrl(productWebData.getImageUrl())
                .build();
    }


}
