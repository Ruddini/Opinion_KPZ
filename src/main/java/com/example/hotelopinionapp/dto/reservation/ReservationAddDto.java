package com.example.hotelopinionapp.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationAddDto {

    private String startDate;
    private String endDate;
    private int hotelId;
}
