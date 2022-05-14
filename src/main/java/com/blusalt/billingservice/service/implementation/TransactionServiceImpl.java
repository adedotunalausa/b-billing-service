package com.blusalt.billingservice.service.implementation;


import com.blusalt.billingservice.dto.request.FundWalletRequest;
import com.blusalt.billingservice.dto.response.BasicResponse;
import com.blusalt.billingservice.enums.Status;
import com.blusalt.billingservice.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Override
    public BasicResponse fundWallet(FundWalletRequest fundWalletRequest) {
        System.out.println("Processing wallet funding");
        return new BasicResponse(Status.SUCCESS, "Fundinghjsdhjfiwekfjh Wallet with " + fundWalletRequest.getAmount() + " for customer: " + fundWalletRequest.getCustomerId());
    }

}
