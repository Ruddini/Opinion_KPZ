package com.example.hotelopinionapp.view.admin;

import com.example.hotelopinionapp.dto.hotel.HotelAddDto;
import com.example.hotelopinionapp.dto.hotel.HotelEditDto;
import com.example.hotelopinionapp.exception.hotel.HotelNotFound;
import com.example.hotelopinionapp.service.sfc.admin.HotelSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("adminHotelController")
@RequiredArgsConstructor
@RequestMapping("admin/hotel")
public class HotelController {

    private final HotelSFC hotelSFC;

    @GetMapping("list")
    public ModelAndView displayList(Model model) {
        return hotelSFC.displayList(model);
    }

    @GetMapping("add")
    public ModelAndView displayAddForm(Model model) {
        return hotelSFC.displayAddForm(model);
    }

    @PostMapping("add")
    public ModelAndView add(HotelAddDto addDto, Model model) {
        return hotelSFC.add(addDto, model);
    }

    @GetMapping("edit")
    public ModelAndView displayEditForm(Integer id, Model model) throws HotelNotFound {
        return hotelSFC.displayEditForm(id, model);
    }

    @PostMapping("edit")
    public ModelAndView edit(HotelEditDto editDto, Model model) throws HotelNotFound {
        return hotelSFC.edit(editDto, model);
    }

    @PostMapping("remove")
    public ModelAndView remove(Integer id) throws HotelNotFound {
        return hotelSFC.remove(id);
    }
}
