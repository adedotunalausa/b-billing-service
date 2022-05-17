package com.blusalt.billingservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargeAccountRequest {
    private String customerId;
    private String amount;
    private String walletId;
    private String currentWalletBalance;
    private String newWalletBalance;
    private String transactionStatus;
    private String transactionId;
}
