package com.blusalt.billingservice.model.internal;

import com.blusalt.billingservice.model.Base;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "transaction")
public class Transaction extends Base {

    @NotNull
    private String customerId;

    @NotBlank
    private String amount;

    @NotNull
    private String status;

}
