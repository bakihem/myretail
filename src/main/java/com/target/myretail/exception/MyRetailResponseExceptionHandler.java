package com.target.myretail.exception;

import com.target.myretail.model.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyRetailResponseExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(Exception ex){
        CustomException customException = (CustomException) ex;
        return new ResponseEntity<>(new ApiError(customException.getHttpStatus(), ex.getMessage()), customException.getHttpStatus());
    }


}
