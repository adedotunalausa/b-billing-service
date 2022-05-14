package com.blusalt.billingservice.controller;

import com.blusalt.billingservice.dto.request.FundWalletRequest;
import com.blusalt.billingservice.dto.response.BasicResponse;
import com.blusalt.billingservice.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/transactions")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/fund-wallet")
    public BasicResponse fundWallet(@Valid @RequestBody FundWalletRequest fundWalletRequest) {
        return transactionService.fundWallet(fundWalletRequest);
    }
}
