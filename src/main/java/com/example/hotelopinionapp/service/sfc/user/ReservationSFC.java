package com.example.hotelopinionapp.service.sfc.user;

import com.example.hotelopinionapp.dto.reservation.ReservationAddDto;
import com.example.hotelopinionapp.exception.hotel.HotelNotFound;
import com.example.hotelopinionapp.exception.user.UserNotFound;
import com.example.hotelopinionapp.mapper.ReservationMapper;
import com.example.hotelopinionapp.model.Hotel;
import com.example.hotelopinionapp.model.Reservation;
import com.example.hotelopinionapp.model.User;
import com.example.hotelopinionapp.service.basic.HotelService;
import com.example.hotelopinionapp.service.basic.MessageService;
import com.example.hotelopinionapp.service.basic.ReservationService;
import com.example.hotelopinionapp.service.basic.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class ReservationSFC {

    private final ReservationService reservationService;
    private final HotelService hotelService;
    private final UserService userService;
    private final MessageService messageService;
    private final ReservationMapper reservationMapper;

    public ModelAndView displayReservationForm(Model model) {
        model.addAttribute("addDto", new ReservationAddDto());
        model.addAttribute("hotelList", hotelService.findAll());
        return new ModelAndView("user/reservation/add");
    }

    public ModelAndView add(ReservationAddDto addDto, HttpServletRequest request, Model model) throws UserNotFound, HotelNotFound {
        User user = userService.findBySession(request);
        Hotel hotel = hotelService.findById(addDto.getHotelId());

        try {
            Reservation reservation = reservationMapper.toNewModel(addDto, user, hotel);
            reservationService.save(reservation);
            messageService.responseMessage(true, "Zapisano, twój kod weryfikacyjny - " + reservation.getVerificationCode(), model);
        } catch (Exception e) {
            messageService.responseMessage(false, "Błąd daty", model);
        }
        return displayReservationForm(model);
    }
}
