package com.codesoom.assignment.application;

import com.codesoom.assignment.application.out.DeleteProductPort;
import com.codesoom.assignment.application.out.GetProductPort;
import com.codesoom.assignment.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteProductServiceTest {

    DeleteProductService deleteProductService;
    DeleteProductPort deleteProductPort;
    GetProductPort getProductPort;

    @BeforeEach
    void setup() {
        deleteProductPort = mock(DeleteProductPort.class);
        getProductPort = mock(GetProductPort.class);

        deleteProductService = new DeleteProductService(deleteProductPort, getProductPort);

        given(getProductPort.findById(ProductFixture.EXISTING_ID)).willReturn(ProductFixture.PRODUCT);
        given(getProductPort.findById(ProductFixture.NOT_EXISTING_ID)).willThrow(ProductNotFoundException.class);
    }


    @Test
    void deleteProductWithExistingId() {
        deleteProductService.deleteProduct(ProductFixture.EXISTING_ID);

        verify(getProductPort).findById(ProductFixture.EXISTING_ID);
        verify(deleteProductPort).delete(ProductFixture.PRODUCT);
    }

    @Test
    void deleteProductWithNotExistingId() {
        assertThatThrownBy(() -> deleteProductService.deleteProduct(ProductFixture.NOT_EXISTING_ID))
                .isInstanceOf(ProductNotFoundException.class);

        verify(getProductPort).findById(ProductFixture.NOT_EXISTING_ID);
    }
}
