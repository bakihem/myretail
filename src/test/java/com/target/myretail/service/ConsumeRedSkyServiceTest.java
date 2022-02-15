package com.target.myretail.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
@PropertySource({"classpath:application.yml"})
public class ConsumeRedSkyServiceTest {

    @InjectMocks
    ConsumeRedSkyService consumeRedSkyService;

    @Mock
    RestTemplate restTemplate;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getProductName() throws Exception {
        Integer productId = 54456119;
        ResponseEntity<String> response = new ResponseEntity<>("title", HttpStatus.OK);
        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.eq(String.class), ArgumentMatchers.any(Map.class))).thenReturn(response);
        consumeRedSkyService.getProductName(productId);
    }
}