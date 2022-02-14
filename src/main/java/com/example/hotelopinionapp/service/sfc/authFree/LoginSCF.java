package com.example.hotelopinionapp.service.sfc.authFree;

import com.example.hotelopinionapp.dto.user.LoggedUserDto;
import com.example.hotelopinionapp.exception.user.UserInvalidPassword;
import com.example.hotelopinionapp.exception.user.UserNotFound;
import com.example.hotelopinionapp.mapper.UserMapper;
import com.example.hotelopinionapp.model.User;
import com.example.hotelopinionapp.model.UserRole;
import com.example.hotelopinionapp.service.basic.MessageService;
import com.example.hotelopinionapp.service.basic.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class LoginSCF {

    private final UserService userService;
    private final MessageService messageService;
    private final UserMapper userMapper;

    public ModelAndView login(String email, String password, HttpServletRequest request, Model model) {
        try {
            User user = userService.findByEmail(email);
            if (!user.getPassword().equals(password)) throw new UserInvalidPassword();

            LoggedUserDto dto = userMapper.toLoggedUserDto(user);
            request.getSession().setAttribute("loggedUser", dto);

            if (user.getRole() == UserRole.ADMIN) {
                return new ModelAndView("redirect:/admin/hotel/list");
            } else {
                return new ModelAndView("redirect:/authFree/opinion/list");
            }
        } catch (UserNotFound | UserInvalidPassword e) {
            messageService.responseMessage(false, "Nieprawidłowe dane", model);
            return new ModelAndView("authFree/security/login");
        }
    }
}
