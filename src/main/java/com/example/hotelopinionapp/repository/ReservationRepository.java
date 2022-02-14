package com.example.hotelopinionapp.repository;

import com.example.hotelopinionapp.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    Optional<Reservation> findByVerificationCode(String verificationCode);
}
