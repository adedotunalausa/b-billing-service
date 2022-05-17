package com.blusalt.billingservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class RabbitMqConfig {

    @Value("${queue.fund_wallet_request}")
    private String FUND_WALLET_REQUEST_QUEUE;

    @Value("${queue.fund_wallet_response}")
    private String FUND_WALLET_RESPONSE_QUEUE;

    @Bean
    public Queue fundWalletRequestQueue() {
        return new Queue(FUND_WALLET_REQUEST_QUEUE);
    }

    @Bean
    public Queue fundWalletResponseQueue() {
        return new Queue(FUND_WALLET_RESPONSE_QUEUE);
    }

    @Bean
    public MappingJackson2MessageConverter jackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

}
