package com.ym.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ReviewResponseDTO {
    private Long id;
    private Long serviceId;
    private Long personnelAccountId;
    private int grade;
    private String comment;
}
