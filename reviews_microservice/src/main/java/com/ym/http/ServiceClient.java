package com.ym.http;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "SERVICES-MICROSERVICE"
)
public interface ServiceClient {

}
