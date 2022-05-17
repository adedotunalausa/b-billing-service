package com.blusalt.billingservice.client;

import com.blusalt.billingservice.config.Routes;
import com.blusalt.billingservice.config.customer.CustomerFeignClientConfig;
import com.blusalt.billingservice.dto.response.BasicResponse;
import com.blusalt.billingservice.dto.response.FundWalletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "billing-service", url = "${blusalt.customer.base.url}", configuration = CustomerFeignClientConfig.class)
public interface CustomerFeignClient {

    @PostMapping(Routes.Customer.Wallet.FUND_WALLET_COMPLETION_WEBHOOK)
    BasicResponse updateCustomerWallet(FundWalletResponse fundWalletResponse);

}
