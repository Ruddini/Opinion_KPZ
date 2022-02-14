package com.example.hotelopinionapp.service.basic;

import com.example.hotelopinionapp.exception.reservation.ReservationNotFound;
import com.example.hotelopinionapp.model.Reservation;
import com.example.hotelopinionapp.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation findByVerificationCode(String verificationCode) throws ReservationNotFound, IllegalArgumentException {
        return reservationRepository.findByVerificationCode(verificationCode).orElseThrow(ReservationNotFound::new);
    }
}
