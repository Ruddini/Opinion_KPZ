package com.example.hotelopinionapp.mapper;

import com.example.hotelopinionapp.dto.opinion.OpinionAddDto;
import com.example.hotelopinionapp.model.Hotel;
import com.example.hotelopinionapp.model.Opinion;
import com.example.hotelopinionapp.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OpinionMapper {

    public Opinion toNewModel(OpinionAddDto addDto, Hotel hotel, User user) {
        int stars = (addDto.getCleanliness() + addDto.getServiceQuality() + addDto.getQualityToPrice()) / 3;
        return new Opinion(
                null,
                addDto.isPositive(),
                addDto.getCleanliness(),
                addDto.getServiceQuality(),
                addDto.getQualityToPrice(),
                stars,
                LocalDate.now(),
                addDto.getDescription(),
                addDto.getChanges(),
                addDto.getAttractions(),
                hotel,
                user
        );
    }
}
