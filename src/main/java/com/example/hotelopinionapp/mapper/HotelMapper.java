package com.example.hotelopinionapp.mapper;

import com.example.hotelopinionapp.dto.hotel.HotelAddDto;
import com.example.hotelopinionapp.dto.hotel.HotelEditDto;
import com.example.hotelopinionapp.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class HotelMapper {

    public Hotel toNewModel(HotelAddDto addDto) {
        return new Hotel(
                null,
                addDto.getName(),
                addDto.getAddress(),
                addDto.isHighlighted(),
                addDto.getDescription(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    public HotelEditDto toEditDto(Hotel hotel) {
        return new HotelEditDto(
                hotel.getId(),
                hotel.getName(),
                hotel.getDescription(),
                hotel.getAddress(),
                hotel.isHighlighted()
        );
    }

    public Hotel toEditModel(Hotel hotel, HotelEditDto editDto) {
        hotel.setName(editDto.getName());
        hotel.setDescription(editDto.getDescription());
        hotel.setAddress(editDto.getAddress());
        hotel.setHighlighted(editDto.isHighlighted());
        return hotel;
    }
}
