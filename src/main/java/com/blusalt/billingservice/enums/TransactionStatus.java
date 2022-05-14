package com.blusalt.billingservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionStatus {
    PENDING("pending"),
    SUCCESS("success");

    private final String alias;
}
