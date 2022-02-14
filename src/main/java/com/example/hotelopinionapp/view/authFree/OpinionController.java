package com.example.hotelopinionapp.view.authFree;

import com.example.hotelopinionapp.dto.opinion.OpinionAddDto;
import com.example.hotelopinionapp.exception.opinion.OpinionNotFound;
import com.example.hotelopinionapp.exception.user.UserNotFound;
import com.example.hotelopinionapp.model.OpinionFilter;
import com.example.hotelopinionapp.service.sfc.authFree.OpinionSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("authFree/opinion")
@Scope("session")
public class OpinionController {

    private final OpinionSFC opinionSFC;

    private String hotelName = "";
    private String date = "";
    private String address = "";
    private int stars = -1;
    private OpinionFilter opinionFilter = OpinionFilter.ALL;

    @GetMapping("list")
    public ModelAndView displayList(String hotelName, String date, String address, Integer stars, OpinionFilter opinionFilter, Model model) {
        setFilter(hotelName, date, address, stars, opinionFilter);
        return opinionSFC.displayList(this.hotelName, this.date, this.address, this.stars, this.opinionFilter, model);
    }

    @PostMapping("clearFilter")
    public ModelAndView clearFilter() {
        this.hotelName = "";
        this.date = "";
        this.address = "";
        this.stars = -1;
        this.opinionFilter = OpinionFilter.ALL;
        return new ModelAndView("redirect:list");
    }

    private void setFilter(String hotelName, String date, String address, Integer stars, OpinionFilter opinionFilter) {
        if (hotelName != null)
            this.hotelName = hotelName;

        if (date != null)
            this.date = date;

        if (address != null)
            this.address = address;

        if (stars != null)
            this.stars = stars;

        if (opinionFilter != null)
            this.opinionFilter = opinionFilter;
    }

    @GetMapping("details")
    public ModelAndView displayDetails(Integer id, Model model) throws OpinionNotFound {
        return opinionSFC.displayDetails(id, model);
    }

    @GetMapping("add")
    public ModelAndView displayAddForm(Model model) {
        return opinionSFC.displayAddForm(model);
    }

    @PostMapping("add")
    public ModelAndView add(OpinionAddDto addDto, HttpServletRequest request, Model model) throws UserNotFound {
        return opinionSFC.add(addDto, request, model);
    }
}
