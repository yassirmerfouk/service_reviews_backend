package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdateAverageRequestDTO {
    private double average;
    private int total;
}
