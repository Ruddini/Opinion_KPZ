package com.example.hotelopinionapp.service.basic;

import com.example.hotelopinionapp.dto.user.LoggedUserDto;
import com.example.hotelopinionapp.exception.user.UserNotFound;
import com.example.hotelopinionapp.model.User;
import com.example.hotelopinionapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public void remove(User user) {
        userRepository.delete(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) throws UserNotFound {
        return userRepository.findById(id).orElseThrow(UserNotFound::new);
    }

    public User findBySession(HttpServletRequest request) throws UserNotFound {
        LoggedUserDto loggedUser = (LoggedUserDto) request.getSession().getAttribute("loggedUser");
        return findById(loggedUser.getId());
    }

    public User findByEmail(String email) throws UserNotFound {
        return userRepository.findByEmail(email).orElseThrow(UserNotFound::new);
    }

    public boolean isEmailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
