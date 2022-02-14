package com.example.hotelopinionapp.service.sfc.user;

import com.example.hotelopinionapp.exception.opinion.OpinionNotFound;
import com.example.hotelopinionapp.exception.user.UserNotFound;
import com.example.hotelopinionapp.model.Opinion;
import com.example.hotelopinionapp.model.User;
import com.example.hotelopinionapp.service.basic.OpinionService;
import com.example.hotelopinionapp.service.basic.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class MyAccountSFC {

    private final UserService userService;
    private final OpinionService opinionService;

    public ModelAndView displayMyAccount(HttpServletRequest request, Model model) throws UserNotFound {
        User user = userService.findBySession(request);
        model.addAttribute("user", user);
        return new ModelAndView("user/myAccount/view");
    }

    public ModelAndView displayDetails(Integer id, HttpServletRequest request, Model model) throws UserNotFound, OpinionNotFound {
        User user = userService.findBySession(request);
        Opinion opinion = opinionService.findById(user.getOpinionList(), id);
        model.addAttribute("opinion", opinion);
        return new ModelAndView("user/myAccount/details");
    }
}
