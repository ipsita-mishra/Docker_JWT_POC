package com.learning.zoro.concepts.Constant;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class BankingServiceValidationException extends Exception {
    private static final long serialVersionUID = 1L;
    public BankingServiceValidationException(String errorMessage) {
        super(errorMessage);
    }
}

