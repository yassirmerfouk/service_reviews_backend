package com.app.http;

import com.app.dto.BusinessAccountResponseDTO;
import com.app.dto.PersonnelAccountResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ACCOUNTS-MICROSERVICE"
)
public interface AccountClient {
    @GetMapping("/accounts/business/{accountId}")
    BusinessAccountResponseDTO getBusinessAccount(@PathVariable Long accountId);

    @GetMapping("/accounts/personnel/{accountId}")
    PersonnelAccountResponseDTO getPersonnelAccount(@PathVariable Long accountId);
}
