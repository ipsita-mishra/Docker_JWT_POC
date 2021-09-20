package com.learning.zoro.concepts.Constant;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {
    public static final String EMAIL_ERROR_MSG = "Invalid Email Address.";
    public static final String GENERIC_ERROR_MSG = "General Error . Please try after sometime.";
    public static final String USER_EXITS_ERROR_MSG = "User Already Exists.";
    public static final String FILE_TOO_LARGE_ERROR_MSG = "File is too large !! Please check limit .";
    public static final String AUTHENTICATION_ERROR_MSG = "INVALID_CREDENTIALS";
    public static final String USERDISABLED_ERROR_MSG = "USER_DISABLED";
}
