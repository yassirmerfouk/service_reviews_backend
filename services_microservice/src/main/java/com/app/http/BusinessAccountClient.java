package com.app.http;

import com.app.dto.BusinessAccountResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        url = "http://localhost:8081",
        name = "ACCOUNTS-MICROSERVICE"
)
public interface BusinessAccountClient {

    @GetMapping("/accounts/business/{accountId}")
    BusinessAccountResponseDTO getBusinessAccount(@PathVariable Long accountId);
}
