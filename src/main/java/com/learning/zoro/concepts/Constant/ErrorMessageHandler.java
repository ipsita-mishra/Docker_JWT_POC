package com.learning.zoro.concepts.Constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class ErrorMessageHandler {
    private int statusCode;
    private String errorMessage;

    public ErrorMessageHandler(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
}
