package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ReviewRequestDTO {
    private Long serviceId;
    private Long personnelAccountId;
    private int grade;
    private String comment;
}
