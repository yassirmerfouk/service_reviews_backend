package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ReviewResponseDTO {
    private Long id;
    private Long serviceId;
    private Long personnelAccountId;
    private int grade;
    private String comment;
    @JsonProperty(value = "personnelAccount")
    private PersonnelAccountResponseDTO personnelAccountResponseDTO;
}
