package com.example.hotelopinionapp.view.user;

import com.example.hotelopinionapp.exception.opinion.OpinionNotFound;
import com.example.hotelopinionapp.exception.user.UserNotFound;
import com.example.hotelopinionapp.service.sfc.user.MyAccountSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("user/myAccount")
public class MyAccountController {

    private final MyAccountSFC myAccountSFC;

    @GetMapping
    public ModelAndView displayMyAccount(HttpServletRequest request, Model model) throws UserNotFound {
        return myAccountSFC.displayMyAccount(request, model);
    }

    @GetMapping("details")
    public ModelAndView displayDetails(Integer id, HttpServletRequest request, Model model) throws UserNotFound, OpinionNotFound {
        return myAccountSFC.displayDetails(id, request, model);
    }
}
