package com.learning.zoro.concepts.Controller;

import com.learning.zoro.concepts.Constant.BankingServiceValidationException;
import com.learning.zoro.concepts.Constant.ErrorMessage;
import com.learning.zoro.concepts.Constant.ErrorMessageHandler;
import com.learning.zoro.concepts.Domain.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class ErrorControllerAdvice {

    @Autowired
    private ErrorMessageHandler errorMessageHandler;

    @ExceptionHandler(BankingServiceValidationException.class)
    public ResponseEntity<ErrorMessageHandler> handleValidationException(BankingServiceValidationException e){
        String msg = e.getMessage();
        if(msg.isEmpty())
            msg = ErrorMessage.EMAIL_ERROR_MSG;
        errorMessageHandler.setErrorMessage(msg);
        errorMessageHandler.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<ErrorMessageHandler>(errorMessageHandler,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageHandler> handleGeneralException(Exception e) {
        String msg = e.getMessage();
        if(msg.isEmpty())
            msg = ErrorMessage.GENERIC_ERROR_MSG;
        errorMessageHandler.setErrorMessage(msg);
        errorMessageHandler.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<ErrorMessageHandler>(errorMessageHandler,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorMessageHandler> handleMaxSizeException(MaxUploadSizeExceededException e) {
        String msg = e.getMessage();
        if(msg.isEmpty())
            msg = ErrorMessage.FILE_TOO_LARGE_ERROR_MSG;
        errorMessageHandler.setErrorMessage(msg);
        return new ResponseEntity<ErrorMessageHandler>(errorMessageHandler,HttpStatus.EXPECTATION_FAILED);
    }


}
