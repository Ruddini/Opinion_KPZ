package com.example.hotelopinionapp.view.user;

import com.example.hotelopinionapp.dto.reservation.ReservationAddDto;
import com.example.hotelopinionapp.exception.hotel.HotelNotFound;
import com.example.hotelopinionapp.exception.user.UserNotFound;
import com.example.hotelopinionapp.service.sfc.user.ReservationSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("user/reservation")
public class ReservationController {

    private final ReservationSFC reservationSFC;

    @GetMapping("add")
    public ModelAndView displayReservationForm(Model model) {
        return reservationSFC.displayReservationForm(model);
    }

    @PostMapping("add")
    public ModelAndView add(ReservationAddDto addDto, HttpServletRequest request, Model model) throws UserNotFound, HotelNotFound {
        return reservationSFC.add(addDto, request, model);
    }
}
