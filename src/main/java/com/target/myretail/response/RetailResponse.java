package com.target.myretail.response;

import java.util.Map;

public class RetailResponse {

    private Integer id;
    private String name;
    private Map<String,String> current_price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Map<String, String> current_price) {
        this.current_price = current_price;
    }
}
