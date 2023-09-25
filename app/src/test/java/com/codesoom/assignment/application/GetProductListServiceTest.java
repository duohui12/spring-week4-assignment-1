package com.codesoom.assignment.application;

import com.codesoom.assignment.application.out.GetProductListPort;
import com.codesoom.assignment.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetProductListServiceTest {

    GetProductListService getProductListService;
    GetProductListPort getProductListPort;

    private static final List<Product> list = new ArrayList<>();

    @BeforeEach
    void setup() {
        getProductListPort = mock(GetProductListPort.class);
        getProductListService = new GetProductListService(getProductListPort);

        list.add(ProductFixture.PRODUCT);

        given(getProductListPort.findAll()).willReturn(list);
    }

    @Test
    void getProducts() {
        assertThat(getProductListService.getProducts()).hasSize(1);
        verify(getProductListPort).findAll();
    }
}
