package com.blusalt.billingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FundWalletResponse {

    private String customerId;
    private String amount;
    private String transactionStatus;

}
