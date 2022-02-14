package com.example.hotelopinionapp.dto.opinion;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OpinionAddDto {

    private boolean isPositive;
    private int cleanliness;
    private int serviceQuality;
    private int qualityToPrice;
    private String description;
    private String changes;
    private String attractions;
    private String verificationCode;

    public OpinionAddDto() {
        this.isPositive = true;
    }
}
