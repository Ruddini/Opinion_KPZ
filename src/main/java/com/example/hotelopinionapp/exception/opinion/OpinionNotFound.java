package com.example.hotelopinionapp.exception.opinion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OpinionNotFound extends Exception {
}
