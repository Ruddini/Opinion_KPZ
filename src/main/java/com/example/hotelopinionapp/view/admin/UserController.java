package com.example.hotelopinionapp.view.admin;

import com.example.hotelopinionapp.exception.user.UserNotFound;
import com.example.hotelopinionapp.service.sfc.admin.UserSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("adminUserController")
@RequiredArgsConstructor
@RequestMapping("admin/user")
public class UserController {

    private final UserSFC userSFC;

    @GetMapping("list")
    public ModelAndView displayList(Model model) {
        return userSFC.displayUsList(model);
    }

    @PostMapping("remove")
    public ModelAndView remove(Integer id) throws UserNotFound {
        return userSFC.remove(id);
    }
}
