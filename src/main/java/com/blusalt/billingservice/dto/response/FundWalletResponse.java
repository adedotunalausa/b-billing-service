package com.blusalt.billingservice.dto.response;

import com.blusalt.billingservice.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundWalletResponse {

    private Status status;
    private FundWalletResponseData data;

}
