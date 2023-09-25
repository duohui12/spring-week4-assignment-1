package com.codesoom.assignment.application;

import com.codesoom.assignment.application.out.GetProductPort;
import com.codesoom.assignment.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetProductServiceTest {

    GetProductService getProductService;
    GetProductPort getProductPort;

    @BeforeEach
    void setup() {
        getProductPort = mock(GetProductPort.class);
        getProductService = new GetProductService(getProductPort);

        given(getProductPort.findById(ProductFixture.EXISTING_ID)).willReturn(ProductFixture.PRODUCT);
        given(getProductPort.findById(ProductFixture.NOT_EXISTING_ID)).willThrow(ProductNotFoundException.class);
    }

    @Test
    void getProductWithExistingId() {
        assertThat(getProductService.getProduct(ProductFixture.EXISTING_ID))
                .isEqualTo(ProductFixture.PRODUCT);

        verify(getProductPort).findById(ProductFixture.EXISTING_ID);
    }

    @Test
    void getProductWithNotExistingId() {
        assertThatThrownBy(() -> getProductService.getProduct(ProductFixture.NOT_EXISTING_ID))
                .isInstanceOf(ProductNotFoundException.class);

        verify(getProductPort).findById(ProductFixture.NOT_EXISTING_ID);
    }
}
