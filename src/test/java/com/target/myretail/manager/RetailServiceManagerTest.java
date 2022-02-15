package com.target.myretail.manager;

import com.target.myretail.dao.ItemDao;
import com.target.myretail.dao.beans.ProductInfo;
import com.target.myretail.response.RetailResponse;
import com.target.myretail.service.ConsumeRedSkyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RetailServiceManagerTest {

    @InjectMocks
    RetailServiceManager retailServiceManager;

    @Mock
    ConsumeRedSkyService consumeRedSkyService;

    @Mock
    ItemDao itemDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProductDetailsWithPrice() throws Exception {
        Integer productId = 132435667;
        Map<String, String> currency = new HashMap<>();
        currency.put("value", "123");
        currency.put("currency_code", "USD");
        ProductInfo productInfo = new ProductInfo(productId, Mockito.anyString(), currency);
        when(consumeRedSkyService.getProductName(productId)).thenReturn("title");
        when(itemDao.getItemInfo(productId)).thenReturn(productInfo);
        RetailResponse retailResponse = retailServiceManager.getProductDetailsWithPrice(productId);
        assertEquals(retailResponse.getId(), productId);
        assertEquals(retailResponse.getCurrent_price(), currency);
    }


}