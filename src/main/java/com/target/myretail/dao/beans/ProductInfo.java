package com.target.myretail.dao.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;


@Document(collection = "productinfo")
public class ProductInfo {

    @Id
    private Integer productId;

    private String productTitle;

    private Map<String, String> currency_price;

    /**
     * Default Constructor
     */
    public ProductInfo() {
    }

    /**
     * Parameterized Constructor
     *
     * @param productId
     * @param productTitle
     * @param currency_price
     */
    public ProductInfo(Integer productId, String productTitle, Map<String, String> currency_price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.currency_price = currency_price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Map<String, String> getCurrency_price() {
        return currency_price;
    }

    public void setCurrency_price(Map<String, String> currency_price) {
        this.currency_price = currency_price;
    }
}
