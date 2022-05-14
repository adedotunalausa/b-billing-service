package com.blusalt.billingservice.service;

import com.blusalt.billingservice.dto.request.FundWalletRequest;
import com.blusalt.billingservice.dto.response.BasicResponse;

public interface TransactionService {

    BasicResponse fundWallet(FundWalletRequest fundWalletRequest);

}
