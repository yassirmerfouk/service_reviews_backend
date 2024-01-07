package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicesMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesMicroserviceApplication.class, args);
    }

}
