package com.example.hotelopinionapp.mapper;

import com.example.hotelopinionapp.dto.user.LoggedUserDto;
import com.example.hotelopinionapp.dto.user.UserAddDto;
import com.example.hotelopinionapp.model.User;
import com.example.hotelopinionapp.model.UserRole;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

@Service
public class UserMapper {

    public LoggedUserDto toLoggedUserDto(User user) {
        return new LoggedUserDto(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

    public User toNewModel(UserAddDto addDto) {
        return new User(
                null,
                addDto.getFirstName(),
                addDto.getLastName(),
                addDto.getUserName(),
                LocalDate.parse(addDto.getBirthDate()),
                addDto.getEmail(),
                addDto.getPassword(),
                UserRole.USER,
                0,
                Collections.emptyList(),
                Collections.emptyList()
        );
    }
}
