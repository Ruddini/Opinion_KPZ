package com.example.hotelopinionapp.service.basic;

import com.example.hotelopinionapp.model.User;
import com.example.hotelopinionapp.model.UserRole;
import com.example.hotelopinionapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class InitDataLoader {

    private final UserRepository userRepository;

    @PostConstruct
    public void initialize() {
        if (userRepository.findAll().isEmpty()) {
            User admin = new User(null, "admin", "admin", "admin", LocalDate.now(), "a@a", "aaa", UserRole.ADMIN, 0, Collections.emptyList(), Collections.emptyList());
            userRepository.save(admin);
        }
    }
}
