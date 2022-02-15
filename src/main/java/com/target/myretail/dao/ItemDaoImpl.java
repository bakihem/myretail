package com.target.myretail.dao;

import com.target.myretail.dao.beans.ProductInfo;
import com.target.myretail.dao.repository.ItemRepository;
import com.target.myretail.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ItemDaoImpl implements ItemDao {

    private static final Logger LOGGER = LogManager.getLogger(ItemDaoImpl.class);
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ProductInfo getItemInfo(Integer productId) throws Exception {
        ProductInfo itemPrice = null;
        try {
            itemPrice = itemRepository.findProductInfoByProductId(productId);

            if (itemPrice == null) {
                throw new CustomException(ItemDaoImpl.class, "Product Id not found", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            LOGGER.error("Exception received while retrieving price for product ::" + productId);
            String cause = e.getMessage();
            if (cause != null && (cause.contains("ConnectTimedOutException") || cause.contains("UnknownHostException") || cause.contains("Connection refused"))) {
                LOGGER.error("Error Occurred while getting record from collection :  " + e.getMessage());
                throw new CustomException(ItemDaoImpl.class, e, "getItemInfo | TimeOut | Database", null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return itemPrice;
    }

    @Override
    public void saveProductInfo(ProductInfo productInfo) throws Exception {
        try {
            itemRepository.save(productInfo);
        } catch (Exception e) {
            String cause = e.getMessage();
            if (cause != null && (cause.contains("ConnectTimedOutException") || cause.contains("UnknownHostException") || cause.contains("Connection refused"))) {
                LOGGER.error("Error Occurred while connecting to collection:  " + e.getMessage());
                throw new CustomException(ItemDaoImpl.class, e, "getProductInfo | TimeOut | Database", null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                throw new CustomException(ItemDaoImpl.class, e, "getProductInfo | Database", null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

}
