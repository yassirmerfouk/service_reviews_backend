package com.ym.http;

import com.ym.dto.UpdateAverageRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "SERVICES-MICROSERVICE"
)
public interface ServiceClient {

    @PostMapping("/services/{serviceId}/update")
    void sendAverageAndTotal(@PathVariable Long serviceId, @RequestBody UpdateAverageRequestDTO updateAverageRequestDTO);
}
