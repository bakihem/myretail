package com.target.myretail.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.exception.CustomException;
import com.target.myretail.util.ApplicationConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumeRedSkyService {

    private static final Logger LOGGER = LogManager.getLogger(ConsumeRedSkyService.class);

    @Value("${endpoint.redskyUrl}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public String getProductName(Integer productId) throws Exception {

        String title = null;
        ResponseEntity<String> response = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Accept", "application/json");
            String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("key", "{key}")
                    .queryParam("tcin", "{tcin}")
                    .encode()
                    .toUriString();
            HttpEntity entity = new HttpEntity(httpHeaders);
            Map<String, String> urlParams = new HashMap<String, String>();
            urlParams.put("key", ApplicationConstants.KEY);
            urlParams.put("tcin", String.valueOf(productId));
            response = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, String.class, urlParams);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Map> redSkyMapper = objectMapper.readValue(response.getBody(), Map.class);
                Map<String, Map> dataMapper = redSkyMapper.get("data");
                Map<String, Map> productData = dataMapper.get("product");
                Map<String, Map> itemData = productData.get("item");
                Map<String, String> productDesc = itemData.get("product_description");
                title = productDesc.get("title");

            }
        } catch (Exception e) {
            String cause = e.getMessage();
            if (cause.contains("UnknownHostException") || cause.contains("connect timed out") || cause.contains("Read timed out")) {
                LOGGER.error("Error occured while connecting to redSky service " + e.getMessage());
                throw new CustomException(ConsumeRedSkyService.class, e, "getProductName | SERVICE NOT AVAILABLE | redSky", null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (cause.contains("No product found")) {
                throw new CustomException(ConsumeRedSkyService.class, "Product Details not found with product Id:  " + productId, HttpStatus.NOT_FOUND);
            }
        }
        return title;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
