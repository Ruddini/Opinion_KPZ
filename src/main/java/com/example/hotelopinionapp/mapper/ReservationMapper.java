package com.example.hotelopinionapp.mapper;

import com.example.hotelopinionapp.dto.reservation.ReservationAddDto;
import com.example.hotelopinionapp.model.Hotel;
import com.example.hotelopinionapp.model.Reservation;
import com.example.hotelopinionapp.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ReservationMapper {

    public Reservation toNewModel(ReservationAddDto addDto, User user, Hotel hotel) throws Exception {
        LocalDate start = LocalDate.parse(addDto.getStartDate());
        LocalDate end = LocalDate.parse(addDto.getEndDate());

        if (start.isAfter(end)) throw new Exception();

        return new Reservation(
                null,
                start,
                end,
                UUID.randomUUID().toString(),
                false,
                user,
                hotel
        );
    }
}
