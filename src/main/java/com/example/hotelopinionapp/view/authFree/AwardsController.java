package com.example.hotelopinionapp.view.authFree;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("authFree/awards")
public class AwardsController {

    @GetMapping("list")
    public ModelAndView displayAwards(){
        return new ModelAndView("authFree/awards/list");
    }
}
