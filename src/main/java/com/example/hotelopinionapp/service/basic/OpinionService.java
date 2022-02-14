package com.example.hotelopinionapp.service.basic;

import com.example.hotelopinionapp.exception.opinion.OpinionNotFound;
import com.example.hotelopinionapp.model.Opinion;
import com.example.hotelopinionapp.model.OpinionFilter;
import com.example.hotelopinionapp.repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;

    public Opinion save(Opinion opinion) {
        return opinionRepository.save(opinion);
    }

    public List<Opinion> findAll() {
        return opinionRepository.findAll();
    }

    public Opinion findById(Integer id) throws OpinionNotFound {
        return opinionRepository.findById(id).orElseThrow(OpinionNotFound::new);
    }

    public Opinion findById(List<Opinion> opinionList, Integer id) throws OpinionNotFound {
        return opinionList.stream().filter(e -> e.getId().equals(id)).findFirst().orElseThrow(OpinionNotFound::new);
    }

    public List<Opinion> filter(List<Opinion> opinionList, String hotelName, String date, String address, int stars, OpinionFilter opinionFilter) {

        if (!hotelName.isEmpty())
            opinionList = opinionList.stream().filter(e -> e.getHotel().getName().contains(hotelName)).collect(Collectors.toList());

        if (!date.isEmpty()) {
            LocalDate localDate = LocalDate.parse(date);
            opinionList = opinionList.stream().filter(e -> e.getDate().equals(localDate)).collect(Collectors.toList());
        }

        if (!address.isEmpty())
            opinionList = opinionList.stream().filter(e -> e.getHotel().getAddress().contains(address)).collect(Collectors.toList());

        if (stars != -1) {
            opinionList = opinionList
                    .stream()
                    .filter(
                            e -> ((e.getCleanliness() + e.getServiceQuality() + e.getQualityToPrice()) / 3) == stars
                    ).collect(Collectors.toList());
        }

        if (opinionFilter != OpinionFilter.ALL)
            if (opinionFilter == OpinionFilter.TRUE)
                opinionList = opinionList.stream().filter(Opinion::isPositive).collect(Collectors.toList());
            else
                opinionList = opinionList.stream().filter(e -> !e.isPositive()).collect(Collectors.toList());

        return opinionList;
    }
}
