package com.blusalt.billingservice.service.implementation;


import com.blusalt.billingservice.client.CustomerFeignClient;
import com.blusalt.billingservice.dto.request.ChargeAccountRequest;
import com.blusalt.billingservice.dto.request.FundWalletRequest;
import com.blusalt.billingservice.dto.response.BasicResponse;
import com.blusalt.billingservice.dto.response.FundWalletResponse;
import com.blusalt.billingservice.enums.Status;
import com.blusalt.billingservice.enums.TransactionStatus;
import com.blusalt.billingservice.event.WalletEventSender;
import com.blusalt.billingservice.exception.ResourceNotFoundException;
import com.blusalt.billingservice.model.internal.Transaction;
import com.blusalt.billingservice.repository.internal.TransactionRepository;
import com.blusalt.billingservice.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final WalletEventSender walletEventSender;
    private final TransactionRepository transactionRepository;
    private final CustomerFeignClient customerFeignClient;

    @Override
    public BasicResponse fundWallet(FundWalletRequest fundWalletRequest) {
        log.info("Processing wallet funding");
        Transaction transaction = saveTransaction(fundWalletRequest);
        FundWalletResponse fundWalletResponse = getFundWalletResponse(fundWalletRequest, transaction);
        ChargeAccountRequest chargeAccountRequest = getChargeAccountRequest(fundWalletResponse);
        walletEventSender.pushFundWalletRequestToQueue(chargeAccountRequest);

        return new BasicResponse(Status.SUCCESS, fundWalletResponse);
    }

    @Override
    public void completeWalletFunding(FundWalletResponse fundWalletResponse) {
        try {
            log.info("Completing wallet funding for customer: {}", fundWalletResponse.getCustomerId());
            BasicResponse response = customerFeignClient.updateCustomerWallet(fundWalletResponse);
            if (response.getStatus().equals(Status.SUCCESS)) {
                updateTransactionStatus(fundWalletResponse.getTransactionId(), fundWalletResponse.getNewWalletBalance());
            }
        } catch (Exception exception) {
            log.error("There was an error while completing wallet funding: {}", exception.getMessage());
        }
    }

    private void updateTransactionStatus(String transactionId, String currentWalletBalance) {
        Transaction transaction = getTransaction(transactionId);
        transaction.setStatus(TransactionStatus.SUCCESS.getAlias());
        transaction.setCurrentWalletBalance(currentWalletBalance);
        transactionRepository.save(transaction);
    }

    private Transaction getTransaction(String transactionId) {
        return transactionRepository.findByTransactionId(transactionId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Transaction not found!");
        });
    }

    private Transaction saveTransaction(FundWalletRequest fundWalletRequest) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setCustomerId(fundWalletRequest.getCustomerId());
        transaction.setWalletId(fundWalletRequest.getWalletId());
        transaction.setAmount(fundWalletRequest.getAmount());
        transaction.setStatus(TransactionStatus.PENDING.getAlias());
        transaction.setCurrentWalletBalance(fundWalletRequest.getCurrentWalletBalance());
        return transactionRepository.save(transaction);
    }

    private FundWalletResponse getFundWalletResponse(FundWalletRequest fundWalletRequest, Transaction transaction) {
        return new FundWalletResponse(
                fundWalletRequest.getCustomerId(),
                fundWalletRequest.getAmount(),
                fundWalletRequest.getWalletId(),
                fundWalletRequest.getCurrentWalletBalance(),
                fundWalletRequest.getNewWalletBalance(),
                transaction.getStatus(),
                transaction.getTransactionId()
        );
    }

    private ChargeAccountRequest getChargeAccountRequest(FundWalletResponse fundWalletResponse) {
        return new ChargeAccountRequest(
                fundWalletResponse.getCustomerId(),
                fundWalletResponse.getAmount(),
                fundWalletResponse.getWalletId(),
                fundWalletResponse.getCurrentWalletBalance(),
                fundWalletResponse.getNewWalletBalance(),
                fundWalletResponse.getTransactionStatus(),
                fundWalletResponse.getTransactionId()
        );
    }

}
