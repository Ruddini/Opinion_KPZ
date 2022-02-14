package com.example.hotelopinionapp.view.authFree;

import com.example.hotelopinionapp.dto.user.UserAddDto;
import com.example.hotelopinionapp.service.sfc.authFree.RegisterSFC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class RegisterController {

    private final RegisterSFC registerSFC;

    @GetMapping("register")
    public ModelAndView displayRegisterForm(Model model) {
        return registerSFC.displayRegisterForm(model);
    }

    @PostMapping("register")
    public ModelAndView register(UserAddDto addDto, Model model) {
        return registerSFC.register(addDto, model);
    }
}
