package com.codesoom.assignment.adapter.in.web;

import com.codesoom.assignment.application.in.*;
import com.codesoom.assignment.domain.Product;
import com.codesoom.assignment.exception.ProductNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    GetProductUseCase getProductUseCase;

    @MockBean
    GetProductListUseCase getProductListUseCase;

    @MockBean
    UpdateProductUseCase updateProductUseCase;

    @MockBean
    DeleteProductUseCase deleteProductUseCase;

    @MockBean
    RegisterProductUseCase registerProductUseCase;

    //fixture
    private static final String PRODUCT_NAME = "test name";
    private static final String PRODUCT_MAKER = "test maker";
    private static final int PRODUCT_PRICE = 1000;
    private static final String PRODUCT_IMAGE_URL = "test image url";
    private static final String UPDATED_PRODUCT_NAME = "updated test name";
    private static final String UPDATED_PRODUCT_MAKER = "updated test maker";
    private static final int UPDATED_PRODUCT_PRICE = 2000;
    private static final String UPDATED_PRODUCT_IMAGE_URL = "updated test image url";
    private static final long EXISTING_ID = 1L;
    private static final long NOT_EXISTING_ID = 100L;
    private static final Product PRODUCT = new Product(EXISTING_ID, PRODUCT_NAME, PRODUCT_MAKER, PRODUCT_PRICE, PRODUCT_IMAGE_URL);
    private static final Product SOURCE = new Product(EXISTING_ID, UPDATED_PRODUCT_NAME, UPDATED_PRODUCT_MAKER, UPDATED_PRODUCT_PRICE, UPDATED_PRODUCT_IMAGE_URL);
    private static final RegisterProductWebData REGISTER_WEB_DATA = new RegisterProductWebData(PRODUCT_NAME, PRODUCT_MAKER, PRODUCT_PRICE, PRODUCT_IMAGE_URL);

    private static final UpdateProductWebData UPDATE_WEB_DATA = new UpdateProductWebData(UPDATED_PRODUCT_NAME, UPDATED_PRODUCT_MAKER, UPDATED_PRODUCT_PRICE, UPDATED_PRODUCT_IMAGE_URL);

    @Nested
    @DisplayName("GET /products")
    class Describe_get_list_request {

        @Nested
        @DisplayName("장난감이 있으면")
        class Context_with_products {

            @BeforeEach
            void setup() {
                List<Product> products = new ArrayList<>();
                products.add(PRODUCT);
                given(getProductListUseCase.getProducts()).willReturn(products);
            }

            @Test
            @DisplayName("상태코드 200을 응답하고, 장난감 리스트를 리턴한다.")
            void it_returns_200_and_products() throws Exception {
                mockMvc.perform(get("/products"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].name").value(PRODUCT_NAME))
                        .andExpect(jsonPath("$[0].maker").value(PRODUCT_MAKER))
                        .andExpect(jsonPath("$[0].imageUrl").value(PRODUCT_IMAGE_URL))
                        .andExpect(jsonPath("$[0].price").value(PRODUCT_PRICE))
                        .andExpect(jsonPath("$[0].id").value(EXISTING_ID));
            }
        }

    }

    @Nested
    @DisplayName("GET /products/{id}")
    class Describe_get_detail_request {

        @Nested
        @DisplayName("장난감을 찾을 수 있으면")
        class Context_with_existing_product_id {

            @BeforeEach
            void setup() {
                given(getProductUseCase.getProduct(EXISTING_ID)).willReturn(PRODUCT);
            }

            @Test
            @DisplayName("상태코드 200을 응답하고, id로 찾은 장난감을 리턴한다.")
            void it_returns_200_and_product_found_by_id() throws Exception {
                mockMvc.perform(get("/products/" + EXISTING_ID))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("name").value(PRODUCT_NAME))
                        .andExpect(jsonPath("maker").value(PRODUCT_MAKER))
                        .andExpect(jsonPath("imageUrl").value(PRODUCT_IMAGE_URL))
                        .andExpect(jsonPath("price").value(PRODUCT_PRICE))
                        .andExpect(jsonPath("id").value(EXISTING_ID));

            }

        }

        @Nested
        @DisplayName("장남감을 찾을 수 없으면")
        class Context_with_not_existing_product_id {

            @BeforeEach
            void setup() {
                given(getProductUseCase.getProduct(NOT_EXISTING_ID))
                        .willThrow(ProductNotFoundException.class);
            }

            @Test
            @DisplayName("상태코드 404를 응답한다.")
            void it_returns_404() throws Exception {
                mockMvc.perform(get("/products/" + NOT_EXISTING_ID))
                        .andExpect(status().isNotFound());
            }
        }

    }

    @Nested
    @DisplayName("POST /products")
    class Describe_post_request {

        @Nested
        @DisplayName("새로운 장난감을 추가하면")
        class Context_when_add_product {

            @BeforeEach
            void setup() {
                given(registerProductUseCase.registerProduct(any(Product.class))).willReturn(PRODUCT);
            }

            @Test
            @DisplayName("상태코드 201를 응답하고, 추가된 장난감을 리턴한다.")
            void it_returns_201_and_created_product() throws Exception {
                mockMvc.perform(post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(REGISTER_WEB_DATA)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("id").value(EXISTING_ID));
            }

        }
    }

    @Nested
    @DisplayName("PATCH /products/{id}")
    class Describe_patch_request {

        @Nested
        @DisplayName("장난감을 찾을 수 있으면")
        class Context_with_existing_id {

            @BeforeEach
            void setup() {
                given(updateProductUseCase.updateProduct(eq(EXISTING_ID), any(Product.class))).willReturn(SOURCE);
            }

            @Test
            @DisplayName("상태코드 200을 응답하고, 수정된 장난감을 리턴한다.")
            void it_returns_200_and_updated_product() throws Exception {
                mockMvc.perform(patch("/products/" + EXISTING_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(UPDATE_WEB_DATA)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("id").value(EXISTING_ID))
                        .andExpect(jsonPath("name").value(UPDATED_PRODUCT_NAME));
            }

        }

        @Nested
        @DisplayName("장난감을 찾을 수 없으면")
        class Context_with_not_existing_id {

            @BeforeEach
            void setup() {
                given(updateProductUseCase.updateProduct(eq(NOT_EXISTING_ID), any(Product.class)))
                        .willThrow(ProductNotFoundException.class);
            }

            @Test
            @DisplayName("상태코드 404를 응답한다.")
            void it_returns_404() throws Exception {
                mockMvc.perform(patch("/products/" + NOT_EXISTING_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(SOURCE)))
                        .andExpect(status().isNotFound());
            }

        }

    }

    @Nested
    @DisplayName("DELETE /products/{id}")
    class Describe_delete_request {

        @Nested
        @DisplayName("장난감을 찾을 수 있으면")
        class Context_with_existing_id {

            @Test
            @DisplayName("상태코드 204를 응답하고, 다시 DELETE를 요청하면 404를 응답한다.")
            void it_returns_204() throws Exception {
                mockMvc.perform(delete("/products/" + EXISTING_ID))
                        .andExpect(status().isNoContent());

                Mockito.doThrow(ProductNotFoundException.class).when(deleteProductUseCase).deleteProduct(EXISTING_ID);

                mockMvc.perform(delete("/products/" + EXISTING_ID))
                        .andExpect(status().isNotFound());
            }

        }

        @Nested
        @DisplayName("장난감을 찾을 수 없으면")
        class Context_with_not_existing_id {

            @BeforeEach
            void setup() {
                //given(deleteProductUseCase.deleteProduct( NOT_EXISTING_ID)).willThrow(ProductNotFoundException.class);
                Mockito.doThrow(ProductNotFoundException.class).when(deleteProductUseCase).deleteProduct(NOT_EXISTING_ID);
            }

            @Test
            @DisplayName("상태코드 404를 응답한다.")
            void it_returns_404() throws Exception {
                mockMvc.perform(delete("/products/" + NOT_EXISTING_ID))
                        .andExpect(status().isNotFound());
            }

        }

    }


}



