package com.target.myretail.service;

import com.target.myretail.dao.beans.ProductInfo;
import com.target.myretail.dao.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataFeedService {
    @Autowired
    private ItemRepository itemRepository;

    @PostConstruct
    public void init(){
        createItemInformation();
    }

    private void createItemInformation(){
        if(itemRepository != null){
            Map<String, String> currency_price = new HashMap<>();
            currency_price.put("value", "13.49");
            currency_price.put("currency_code", "USD");
            ProductInfo productRecordOne = new ProductInfo(13860428, "The Big Lebowski (Blu-ray)", currency_price);

            Map<String, String> currency_price1 = new HashMap<>();
            currency_price1.put("value", "15.29");
            currency_price1.put("currency_code", "USD");
            ProductInfo productRecordTwo = new ProductInfo(54456119, "Creamy Peanut Butter 40oz - Good &#38; Gather&#8482;", currency_price1);

            Map<String, String> currency_price2 = new HashMap<>();
            currency_price2.put("value", "10.76");
            currency_price2.put("currency_code", "USD");
            ProductInfo productRecordThree = new ProductInfo(13264003, "Jif Natural Creamy Peanut Butter - 40oz", currency_price2);

            Map<String, String> currency_price3 = new HashMap<>();
            currency_price3.put("value", "17.98");
            currency_price3.put("currency_code", "USD");
            ProductInfo productRecordFour = new ProductInfo(12954218, "Kraft Macaroni &#38; Cheese Dinner Original - 7.25oz", currency_price3);

            List<ProductInfo> productInfoList = new ArrayList<>();
            productInfoList.add(productRecordOne);
            productInfoList.add(productRecordTwo);
            productInfoList.add(productRecordThree);
            productInfoList.add(productRecordFour);

            this.itemRepository.saveAll(productInfoList);
        }
    }
}
