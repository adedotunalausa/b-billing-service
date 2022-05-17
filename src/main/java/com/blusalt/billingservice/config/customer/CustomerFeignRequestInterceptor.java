package com.blusalt.billingservice.config.customer;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class CustomerFeignRequestInterceptor implements RequestInterceptor {

    private final String SECRET_KEY;

    public CustomerFeignRequestInterceptor(String apiKey) {
        this.SECRET_KEY = apiKey;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        final String secret = "secret_key";
        requestTemplate.header(secret, SECRET_KEY);
    }
}
