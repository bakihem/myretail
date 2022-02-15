package com.target.myretail.dao;


import com.target.myretail.dao.beans.ProductInfo;

public interface ItemDao {

    ProductInfo getItemInfo(Integer productId) throws Exception;

    void saveProductInfo(ProductInfo productInfo) throws Exception;

}
