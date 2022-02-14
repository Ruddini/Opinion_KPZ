package com.example.hotelopinionapp.model;

public enum OpinionFilter {

    ALL("Wszystko"),
    TRUE("Pozytywne"),
    FALSE("Negatywne");

    private String polishName;

    OpinionFilter(String polishName) {
        this.polishName = polishName;
    }

    public String getPolishName() {
        return polishName;
    }
}
