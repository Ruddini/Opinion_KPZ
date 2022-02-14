package com.example.hotelopinionapp.service.sfc.authFree;

import com.example.hotelopinionapp.dto.opinion.OpinionAddDto;
import com.example.hotelopinionapp.exception.opinion.OpinionAlreadyInUse;
import com.example.hotelopinionapp.exception.opinion.OpinionMoreThanMonth;
import com.example.hotelopinionapp.exception.opinion.OpinionNotFound;
import com.example.hotelopinionapp.exception.reservation.ReservationNotFound;
import com.example.hotelopinionapp.exception.user.UserNotFound;
import com.example.hotelopinionapp.mapper.OpinionMapper;
import com.example.hotelopinionapp.model.Opinion;
import com.example.hotelopinionapp.model.OpinionFilter;
import com.example.hotelopinionapp.model.Reservation;
import com.example.hotelopinionapp.model.User;
import com.example.hotelopinionapp.service.basic.MessageService;
import com.example.hotelopinionapp.service.basic.OpinionService;
import com.example.hotelopinionapp.service.basic.ReservationService;
import com.example.hotelopinionapp.service.basic.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpinionSFC {

    private final OpinionService opinionService;
    private final ReservationService reservationService;
    private final UserService userService;
    private final MessageService messageService;
    private final OpinionMapper opinionMapper;

    public ModelAndView displayList(String hotelName, String date, String address, int stars, OpinionFilter opinionFilter, Model model) {
        List<Opinion> filteredList = opinionService.filter(opinionService.findAll(), hotelName, date, address, stars, opinionFilter);
        model.addAttribute("opinionList", filteredList);
        model.addAttribute("opinionFilterTable", OpinionFilter.values());
        setFilterModel(hotelName, date, address, stars, opinionFilter, model);
        return new ModelAndView("authFree/opinion/list");
    }

    private void setFilterModel(String hotelName, String date, String address, int stars, OpinionFilter opinionFilter, Model model) {
        model.addAttribute("hotelName", hotelName);
        model.addAttribute("date", date);
        model.addAttribute("address", address);
        model.addAttribute("stars", stars);
        model.addAttribute("opinionFilter", opinionFilter);
    }

    public ModelAndView displayDetails(Integer id, Model model) throws OpinionNotFound {
        Opinion opinion = opinionService.findById(id);
        model.addAttribute("opinion", opinion);
        return new ModelAndView("authFree/opinion/details");
    }

    public ModelAndView displayAddForm(Model model) {
        model.addAttribute("addDto", new OpinionAddDto());
        return new ModelAndView("authFree/opinion/add");
    }

    public ModelAndView displayAddForm(OpinionAddDto addDto, Model model) {
        model.addAttribute("addDto", addDto);
        return new ModelAndView("authFree/opinion/add");
    }

    public ModelAndView add(OpinionAddDto addDto, HttpServletRequest request, Model model) throws UserNotFound {
        try {
            Reservation reservation = reservationService.findByVerificationCode(addDto.getVerificationCode());
            if (reservation.isInUse()) throw new OpinionAlreadyInUse();

            User user = userService.findBySession(request);
            Opinion opinion = opinionMapper.toNewModel(addDto, reservation.getHotel(), user);
            reservation.setInUse(true);
            setPointsToUser(user, reservation, opinion);
            opinionService.save(opinion);
            messageService.responseMessage(true, "Dodano opinie", model);
        } catch (ReservationNotFound e) {
            messageService.responseMessage(false, "Kod nieznaleziony", model);
            return displayAddForm(addDto, model);
        } catch (OpinionAlreadyInUse opinionAlreadyInUse) {
            messageService.responseMessage(false, "Opinia została już wystawiona", model);
            return displayAddForm(addDto, model);
        } catch (OpinionMoreThanMonth opinionMoreThanMonth) {
            messageService.responseMessage(false, "Opinię można wystawić do miesiąca od zakończonego pobytu", model);
            return displayAddForm(addDto, model);
        }
        return displayAddForm(model);
    }

    public void setPointsToUser(User user, Reservation reservation, Opinion opinion) throws OpinionMoreThanMonth {
        LocalDate reservationDate = reservation.getEndDate();
        LocalDate opinionDate = opinion.getDate();

        if (reservationDate.plusMonths(1).isBefore(opinionDate)) throw new OpinionMoreThanMonth();

        int points = reservationDate.equals(opinionDate) || reservationDate.plusDays(1).equals(opinionDate) ? 3 : 1;
        if (reservation.getHotel().isHighlighted())
            points *= 2;

        user.setPoints(user.getPoints() + points);
    }
}
