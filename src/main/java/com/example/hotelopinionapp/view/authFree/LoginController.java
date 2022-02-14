package com.example.hotelopinionapp.view.authFree;

import com.example.hotelopinionapp.service.sfc.authFree.LoginSCF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class LoginController {

    private final LoginSCF loginSFC;

    @GetMapping("login")
    public ModelAndView displayLoginForm(){
        return new ModelAndView("authFree/security/login");
    }

    @PostMapping("login")
    public ModelAndView login(String email, String password, HttpServletRequest request, Model model){
        return loginSFC.login(email, password, request, model);
    }
}
