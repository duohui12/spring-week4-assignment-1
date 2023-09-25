package com.codesoom.assignment.application;

import com.codesoom.assignment.application.out.SaveProductPort;
import com.codesoom.assignment.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class RegisterProductServiceTest {

    RegisterProductService registerProductService;
    SaveProductPort saveProductPort;

    @BeforeEach
    void setup() {
        saveProductPort = mock(SaveProductPort.class);
        
        registerProductService = new RegisterProductService(saveProductPort);
        given(saveProductPort.save(any(Product.class))).willReturn(ProductFixture.PRODUCT);
    }

    @Test
    void registerProduct() {
        Product product = registerProductService.registerProduct(ProductFixture.PRODUCT);

        System.out.println(product.getName());
        System.out.println(ProductFixture.PRODUCT_NAME);
        assertThat(product.getName()).isEqualTo(ProductFixture.PRODUCT_NAME);
        assertThat(product.getMaker()).isEqualTo(ProductFixture.PRODUCT_MAKER);
        assertThat(product.getPrice()).isEqualTo(ProductFixture.PRODUCT_PRICE);
        assertThat(product.getImageUrl()).isEqualTo(ProductFixture.PRODUCT_IMAGE_URL);

        verify(saveProductPort).save(ProductFixture.PRODUCT);
    }
}
