package com.blusalt.billingservice.service.implementation;


import com.blusalt.billingservice.dto.request.FundWalletRequest;
import com.blusalt.billingservice.dto.response.BasicResponse;
import com.blusalt.billingservice.dto.response.FundWalletResponse;
import com.blusalt.billingservice.enums.Status;
import com.blusalt.billingservice.enums.TransactionStatus;
import com.blusalt.billingservice.event.WalletEventService;
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

    private final WalletEventService walletEventService;
    private final TransactionRepository transactionRepository;

    @Override
    public BasicResponse fundWallet(FundWalletRequest fundWalletRequest) {
        log.info("Processing wallet funding");

        Transaction transaction = saveTransaction(fundWalletRequest);

        FundWalletResponse fundWalletResponse = getFundWalletResponse(fundWalletRequest, transaction.getStatus());

        walletEventService.pushFundWalletRequestToQueue(fundWalletResponse);

        return new BasicResponse(Status.SUCCESS, fundWalletResponse);
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

    private FundWalletResponse getFundWalletResponse(FundWalletRequest fundWalletRequest, String transactionStatus) {
        return new FundWalletResponse(
                fundWalletRequest.getCustomerId(),
                fundWalletRequest.getAmount(),
                transactionStatus
        );
    }

}
