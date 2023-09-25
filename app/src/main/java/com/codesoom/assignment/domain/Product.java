package com.codesoom.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Long id;

    private String name;

    private String maker;

    private Integer price;

    private String imageUrl;

    public void change(String name, String maker, Integer price, String imageUrl) {
        if (name != null) this.name = name;
        if (maker != null) this.maker = maker;
        if (price != null) this.price = price;
        if (imageUrl != null) this.imageUrl = imageUrl;
    }
}
