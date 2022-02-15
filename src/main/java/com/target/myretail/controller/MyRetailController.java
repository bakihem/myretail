package com.target.myretail.controller;


import com.target.myretail.manager.RetailServiceManager;
import com.target.myretail.response.CustomResponse;
import com.target.myretail.response.ProductPrice;
import com.target.myretail.response.RetailResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/products")
@RestController
public class MyRetailController {
    private static final Logger LOGGER = LogManager.getLogger(MyRetailController.class);

    @Autowired
    private RetailServiceManager serviceManager;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetailResponse> retrievePriceForItem(@PathVariable Integer id) throws Exception {
        LOGGER.info("retrievePriceForItem ::::: START");
        RetailResponse retailResponse = serviceManager.getProductDetailsWithPrice(id);
        LOGGER.info("retrievePriceForItem ::::: END");
        return new ResponseEntity<>(retailResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse> updatePriceForItem(@RequestBody ProductPrice productPrice, @PathVariable Integer id) throws Exception {
        LOGGER.info("updatePriceForItem ::::: START");
        CustomResponse customResponse = serviceManager.updateRetailPriceForProduct(productPrice, id);
        LOGGER.info("retrievePriceForItem ::::: END");
        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }
}
