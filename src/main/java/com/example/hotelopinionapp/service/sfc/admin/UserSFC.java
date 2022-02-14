package com.example.hotelopinionapp.service.sfc.admin;

import com.example.hotelopinionapp.exception.user.UserNotFound;
import com.example.hotelopinionapp.model.User;
import com.example.hotelopinionapp.service.basic.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Service("adminUserSFC")
@RequiredArgsConstructor
public class UserSFC {

    private final UserService userService;

    public ModelAndView displayUsList(Model model) {
        model.addAttribute("userList", userService.findAll());
        return new ModelAndView("admin/user/list");
    }

    public ModelAndView remove(Integer id) throws UserNotFound {
        User user = userService.findById(id);
        userService.remove(user);
        return new ModelAndView("redirect:list");
    }
}
