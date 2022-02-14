package com.example.hotelopinionapp.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelEditDto {

    private int id;
    private String name;
    private String description;
    private String address;
    private boolean isHighlighted;
}
