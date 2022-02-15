package com.target.myretail.dao.repository;


import com.target.myretail.dao.beans.ProductInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<ProductInfo, String> {

    ProductInfo findProductInfoByProductId(Integer productId);


}
