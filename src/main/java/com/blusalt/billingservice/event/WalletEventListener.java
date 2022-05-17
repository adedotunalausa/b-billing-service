package com.blusalt.billingservice.event;

import com.blusalt.billingservice.dto.response.FundWalletResponse;
import com.blusalt.billingservice.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletEventListener {

    private final TransactionService transactionService;

    @Transactional
    @RabbitListener(queues = "${queue.fund_wallet_response}")
    public void listen(Message message) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String rawMessageString = new String(message.getBody());
            FundWalletResponse transactionCompletionResponse = objectMapper.readValue(rawMessageString, FundWalletResponse.class);
            log.info("Completed Fund wallet transaction for customer: {}, status: {}, newBalance{}",
                    transactionCompletionResponse.getCustomerId(), transactionCompletionResponse.getTransactionStatus(),
                    transactionCompletionResponse.getNewWalletBalance());
            transactionService.completeWalletFunding(transactionCompletionResponse);
        } catch (Exception exception) {
            log.error("There was an error in fund wallet response listener: {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

}
