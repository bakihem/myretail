package com.target.myretail.dao;

import com.target.myretail.dao.beans.ProductInfo;
import com.target.myretail.dao.repository.ItemRepository;
import com.target.myretail.exception.CustomException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ItemDaoImplTest {

    @InjectMocks
    ItemDaoImpl itemDaoImpl;

    @Mock
    ItemRepository itemRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getItemInfoForProduct() throws Exception{
        Integer productId = 344667;
        when(itemRepository.findProductInfoByProductId(productId)).thenReturn(new ProductInfo(productId, "", new HashMap<String,String>()));
        assertEquals(itemDaoImpl.getItemInfo(productId).getProductId(), productId);
    }

    @Test(expected = Exception.class)
    public void getItemInfoForProductNotFound() throws Exception{
        Integer productId = 344667;
        when(itemRepository.findProductInfoByProductId(productId)).thenThrow(Exception.class);
        itemDaoImpl.getItemInfo(productId);
    }
}