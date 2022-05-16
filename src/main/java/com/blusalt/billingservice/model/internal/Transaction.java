package com.blusalt.billingservice.model.internal;

import com.blusalt.billingservice.model.Base;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "transaction")
public class Transaction extends Base {

    private String transactionId;

    private String customerId;

    private String walletId;

    private String amount;

    private String status;

    private String currentWalletBalance;

    private String newWalletBalance;

}
