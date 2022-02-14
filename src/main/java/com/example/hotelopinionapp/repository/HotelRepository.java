package com.example.hotelopinionapp.repository;

import com.example.hotelopinionapp.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {

    List<Hotel> findAll();
}
