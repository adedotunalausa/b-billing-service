package com.blusalt.billingservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "wallet",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "walletId"),
                @UniqueConstraint(columnNames = "customerId")
    }
)
public class Wallet extends Base{

    private String walletId;

    private Double balance;

    private String customerId;

}
