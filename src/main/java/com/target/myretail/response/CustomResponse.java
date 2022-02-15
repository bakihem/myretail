package com.target.myretail.response;

import java.math.BigDecimal;

public class CustomResponse {
    private BigDecimal price;
    private String message;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
