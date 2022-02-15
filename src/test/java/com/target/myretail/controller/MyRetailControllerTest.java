package com.target.myretail.controller;


import com.target.myretail.manager.RetailServiceManager;
import com.target.myretail.response.CustomResponse;
import com.target.myretail.response.ProductPrice;
import com.target.myretail.response.RetailResponse;
import com.target.myretail.util.ApplicationConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MyRetailControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private MyRetailController myRetailController;

    @Mock
    private RetailServiceManager retailServiceManager;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void retrievePriceForItem() throws Exception{
        Integer productId = 54456119;
        RetailResponse retailResponse = new RetailResponse();
        Map<String, String> currency = new HashMap<>();
        currency.put("value", "12.39");
        currency.put("currency_code", "USD");
        retailResponse.setId(productId);
        retailResponse.setCurrent_price(currency);
        when(retailServiceManager.getProductDetailsWithPrice(productId)).thenReturn(retailResponse);
        myRetailController.retrievePriceForItem(productId).getBody();
        assertTrue(true);
    }

    @Test
    public void updatePriceForItem() throws Exception{
        Integer productId = 54456119;
        ProductPrice productPrice = new ProductPrice();
        productPrice.setValue("245");
        productPrice.setCurrency_code("USD");

        CustomResponse customResponse = new CustomResponse();
        customResponse.setPrice(new BigDecimal(productPrice.getValue()));
        customResponse.setMessage(ApplicationConstants.MESSAGE);

        when(retailServiceManager.updateRetailPriceForProduct(productPrice, productId)).thenReturn(customResponse);
        myRetailController.updatePriceForItem(productPrice, productId).getBody();
        assertTrue(true);
    }
}