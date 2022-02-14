package com.example.hotelopinionapp.service.sfc.admin;

import com.example.hotelopinionapp.dto.hotel.HotelAddDto;
import com.example.hotelopinionapp.dto.hotel.HotelEditDto;
import com.example.hotelopinionapp.exception.hotel.HotelNotFound;
import com.example.hotelopinionapp.mapper.HotelMapper;
import com.example.hotelopinionapp.model.Hotel;
import com.example.hotelopinionapp.service.basic.HotelService;
import com.example.hotelopinionapp.service.basic.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Service("adminHotelSFC")
@RequiredArgsConstructor
public class HotelSFC {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;
    private final MessageService messageService;

    public ModelAndView displayList(Model model) {
        model.addAttribute("hotelList", hotelService.findAll());
        return new ModelAndView("admin/hotel/list");
    }

    public ModelAndView displayAddForm(Model model) {
        model.addAttribute("addDto", new HotelAddDto());
        return new ModelAndView("admin/hotel/add");
    }

    public ModelAndView add(HotelAddDto addDto, Model model) {
        Hotel hotel = hotelMapper.toNewModel(addDto);
        hotelService.save(hotel);
        messageService.responseMessage(true, "Zapisano", model);
        return displayAddForm(model);
    }

    public ModelAndView displayEditForm(Integer id, Model model) throws HotelNotFound {
        Hotel hotel = hotelService.findById(id);
        HotelEditDto editDto = hotelMapper.toEditDto(hotel);
        model.addAttribute("editDto", editDto);
        return new ModelAndView("admin/hotel/edit");
    }

    public ModelAndView edit(HotelEditDto editDto, Model model) throws HotelNotFound {
        Hotel hotel = hotelService.findById(editDto.getId());
        hotel = hotelMapper.toEditModel(hotel, editDto);
        hotelService.save(hotel);
        messageService.responseMessage(true, "Zapisano", model);
        return displayEditForm(editDto.getId(), model);
    }

    public ModelAndView remove(Integer id) throws HotelNotFound {
        Hotel hotel = hotelService.findById(id);
        hotelService.remove(hotel);
        return new ModelAndView("redirect:list");
    }
}
