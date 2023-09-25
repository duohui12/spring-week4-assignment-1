package com.codesoom.assignment.application;

import com.codesoom.assignment.domain.Product;

public class ProductFixture {

    public static final String PRODUCT_NAME = "test name";
    public static final String PRODUCT_MAKER = "test maker";
    public static final int PRODUCT_PRICE = 1000;
    public static final String PRODUCT_IMAGE_URL = "test image url";

    public static final String UPDATED_PRODUCT_NAME = "updated test name";
    public static final String UPDATED_PRODUCT_MAKER = "updated test maker";
    public static final int UPDATED_PRODUCT_PRICE = 2000;
    public static final String UPDATED_PRODUCT_IMAGE_URL = "updated test image url";

    public static final long EXISTING_ID = 1L;
    public static final long NOT_EXISTING_ID = 100L;

    public static final Product PRODUCT = new Product(EXISTING_ID, PRODUCT_NAME, PRODUCT_MAKER, PRODUCT_PRICE, PRODUCT_IMAGE_URL);
    public static final Product SOURCE = new Product(EXISTING_ID, UPDATED_PRODUCT_NAME, UPDATED_PRODUCT_MAKER, UPDATED_PRODUCT_PRICE, UPDATED_PRODUCT_IMAGE_URL);

}
