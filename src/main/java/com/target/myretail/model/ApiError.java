package com.target.myretail.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;


import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private int statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private Date timestamp;
    private String status;
    private String message;

    public ApiError(){
        timestamp = new Date();
    }
    public ApiError(HttpStatus httpStatus , String message){
        this();
        this.statusCode = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
