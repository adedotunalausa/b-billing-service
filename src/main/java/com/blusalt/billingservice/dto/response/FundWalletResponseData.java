package com.blusalt.billingservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundWalletResponseData {

    private String customerId;
    private Double amount;
    private String transactionStatus;

}
