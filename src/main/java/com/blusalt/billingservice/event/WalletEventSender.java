package com.blusalt.billingservice.event;

import com.blusalt.billingservice.dto.request.ChargeAccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletEventSender {

    private final RabbitMessagingTemplate rabbitMessagingTemplate;

    private final MappingJackson2MessageConverter messageConverter;

    @Value("${queue.fund_wallet_request}")
    private String FUND_WALLET_REQUEST_QUEUE;

    public void pushFundWalletRequestToQueue(ChargeAccountRequest chargeAccountRequest) {

        Map message = new ObjectMapper().convertValue(chargeAccountRequest, Map.class);
        rabbitMessagingTemplate.setMessageConverter(messageConverter);
        rabbitMessagingTemplate.convertAndSend(FUND_WALLET_REQUEST_QUEUE, message);
        log.info("pushed new transaction request: {} to queue", message);
    }

}
