package com.blusalt.billingservice.service;

import com.blusalt.billingservice.dto.request.FundWalletRequest;
import com.blusalt.billingservice.dto.response.BasicResponse;
import com.blusalt.billingservice.dto.response.FundWalletResponse;

public interface TransactionService {

    BasicResponse fundWallet(FundWalletRequest fundWalletRequest);
    void completeWalletFunding(FundWalletResponse fundWalletResponse);

}
