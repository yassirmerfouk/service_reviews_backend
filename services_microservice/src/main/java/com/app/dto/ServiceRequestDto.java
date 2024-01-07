package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ServiceRequestDto {
    private String name;
    private String shortDescription;
    private String longDescription;
    private String contactEmail;
    private String contactPhone;
    private Long categoryId;
    private Long businessAccountId;
}
