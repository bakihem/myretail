package com.target.myretail.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

public class CustomException extends Exception{

    private static final Logger LOGGER = LogManager.getLogger(CustomException.class);

    private HttpStatus httpStatus;

    public CustomException(Class clazz, Exception ex, String errorMessage, String customMessage, HttpStatus httpStatus){
        super(errorMessage.concat("@").concat(ex != null && ex.getMessage() != null ? ex.getMessage() : "").concat("@").concat(customMessage != null ? customMessage : ""));
        String className = clazz != null ? StringUtils.capitalize(clazz.getSimpleName()) : "";
        LOGGER.error(className + "--- EXCEPTION OCCURRED WHILE ---- ERROR MESSAGE -- "+ errorMessage + "--- CUSTOME EXCEPTION -- "+ customMessage +
                "-- EXCEPTION DETAILS--" + ex);
        setHttpStatus(httpStatus);
    }

    public CustomException(Class clazz, String customMessage, HttpStatus httpStatus){
        super(customMessage != null ? customMessage : "");
        String className = clazz != null ? StringUtils.capitalize(clazz.getSimpleName()) : "";
        LOGGER.error(className + "--- EXCEPTION OCCURRED WHILE ---- ERROR MESSAGE -- "+ customMessage );
        setHttpStatus(httpStatus);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
