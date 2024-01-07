package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter
public class ServiceResponseDTO {
    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String contactEmail;
    private String contactPhone;
    private double reviewsAverage;
    private int reviewsNumbers;
    private List<String> imagesUrls;
    @JsonProperty(value = "category")
    private CategoryResponseDTO categoryResponseDTO;
    @JsonProperty(value = "businessAccount")
    private BusinessAccountResponseDTO businessAccountResponseDTO;
}
