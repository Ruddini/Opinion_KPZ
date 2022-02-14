package com.example.hotelopinionapp.dto.user;

import com.example.hotelopinionapp.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoggedUserDto {

    private int id;
    private String email;
    private UserRole role;
}
