package com.codesoom.assignment.application;

import com.codesoom.assignment.application.out.GetProductPort;
import com.codesoom.assignment.application.out.SaveProductPort;
import com.codesoom.assignment.domain.Product;
import com.codesoom.assignment.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UpdateProductServiceTest {

    UpdateProductService updateProductService;
    GetProductPort getProductPort;
    SaveProductPort saveProductPort;

    @BeforeEach
    void setup() {
        saveProductPort = mock(SaveProductPort.class);
        getProductPort = mock(GetProductPort.class);
        updateProductService = new UpdateProductService(saveProductPort, getProductPort);

        given(getProductPort.findById(ProductFixture.EXISTING_ID)).willReturn(ProductFixture.PRODUCT);
        given(getProductPort.findById(ProductFixture.NOT_EXISTING_ID)).willThrow(ProductNotFoundException.class);
        given(saveProductPort.save(any(Product.class))).willReturn(ProductFixture.SOURCE);
    }

    @Test
    void updateProductWithExistingId() {
        Product updatedProduct = updateProductService.updateProduct(ProductFixture.EXISTING_ID, ProductFixture.SOURCE);

        assertThat(updatedProduct.getName()).isEqualTo(ProductFixture.UPDATED_PRODUCT_NAME);
        assertThat(updatedProduct.getMaker()).isEqualTo(ProductFixture.UPDATED_PRODUCT_MAKER);
        assertThat(updatedProduct.getPrice()).isEqualTo(ProductFixture.UPDATED_PRODUCT_PRICE);
        assertThat(updatedProduct.getImageUrl()).isEqualTo(ProductFixture.UPDATED_PRODUCT_IMAGE_URL);

        verify(getProductPort).findById(ProductFixture.EXISTING_ID);
        verify(saveProductPort).save(any(Product.class));
    }

    @Test
    void updateProductWithNotExistingId() {
        assertThatThrownBy(() -> updateProductService.updateProduct(ProductFixture.NOT_EXISTING_ID, ProductFixture.SOURCE))
                .isInstanceOf(ProductNotFoundException.class);

        verify(getProductPort).findById(ProductFixture.NOT_EXISTING_ID);
    }
}
