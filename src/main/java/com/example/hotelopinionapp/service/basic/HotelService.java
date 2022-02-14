package com.example.hotelopinionapp.service.basic;

import com.example.hotelopinionapp.exception.hotel.HotelNotFound;
import com.example.hotelopinionapp.model.Hotel;
import com.example.hotelopinionapp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void remove(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel findById(Integer id) throws HotelNotFound {
        return hotelRepository.findById(id).orElseThrow(HotelNotFound::new);
    }
}
