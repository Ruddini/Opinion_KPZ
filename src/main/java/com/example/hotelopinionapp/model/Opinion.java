package com.example.hotelopinionapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean isPositive;
    private int cleanliness;
    private int serviceQuality;
    private int qualityToPrice;
    private int stars;
    private LocalDate date;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String changes;

    @Column(length = 1000)
    private String attractions;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}