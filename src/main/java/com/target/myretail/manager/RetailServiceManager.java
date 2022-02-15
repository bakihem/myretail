package com.target.myretail.manager;

import com.target.myretail.dao.ItemDao;
import com.target.myretail.dao.beans.ProductInfo;
import com.target.myretail.response.CustomResponse;
import com.target.myretail.response.ProductPrice;
import com.target.myretail.response.RetailResponse;
import com.target.myretail.service.ConsumeRedSkyService;
import com.target.myretail.util.ApplicationConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Component
public class RetailServiceManager {

    private static final Logger LOGGER = LogManager.getLogger(RetailServiceManager.class);
    @Autowired
    private ConsumeRedSkyService consumeRedSkyService;
    @Autowired
    private ItemDao itemDao;

    public RetailResponse getProductDetailsWithPrice(Integer id) throws Exception {
        RetailResponse retailResponse = new RetailResponse();
        LOGGER.info("getProductDetailsWithPrice() :: START");
        String redSkytitle = consumeRedSkyService.getProductName(id);
        ProductInfo productInfo = itemDao.getItemInfo(id);
        retailResponse.setId(id);
        retailResponse.setCurrent_price(productInfo.getCurrency_price());
        retailResponse.setName(redSkytitle);
        LOGGER.info("getProductDetailsWithPrice() :: END");
        return retailResponse;
    }

    @Transactional(rollbackFor = Exception.class)
    public CustomResponse updateRetailPriceForProduct(ProductPrice productPrice, Integer id) throws Exception {
        LOGGER.info("updateProductDetailsWithPrice() :: START");
        CustomResponse customResponse = new CustomResponse();
        Map<String, String> currency_price = new HashMap<>();
        currency_price.put("value", productPrice.getValue());
        currency_price.put("currency_code", productPrice.getCurrency_code());
        ProductInfo productInfo = itemDao.getItemInfo(id);
        productInfo.setCurrency_price(currency_price);
        itemDao.saveProductInfo(productInfo);
        customResponse.setPrice(new BigDecimal(productPrice.getValue()));
        customResponse.setMessage(ApplicationConstants.MESSAGE);
        LOGGER.info("getProductDetailsWithPrice() :: END");
        return customResponse;

    }
}
