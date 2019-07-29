package com.da.wapi.controller;


import com.da.common.model.json.MetaDataJSON;
import com.da.common.model.json.PricingInformationJSON;
import com.da.common.model.json.ProductDescriptionJSON;
import com.da.common.model.json.ProductJSON;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ProductControllerTest {
    @LocalServerPort
    public int port;
    @Autowired
    public TestRestTemplate restTemplate;
    String productUpdateURL = "/api/v1/update";



    private ProductJSON fakeRequest(){
        MetaDataJSON metaDataJSON = new MetaDataJSON("title","site_name","description",
                "keywords", "canonical");
        PricingInformationJSON pricingInformationJSON = new PricingInformationJSON( new BigDecimal("10.00"),
                new BigDecimal("9.00"), new BigDecimal("10.00"));
        ProductDescriptionJSON productDescriptionJSON = new ProductDescriptionJSON("title","subtitle",
                "text");

        return new ProductJSON("1","name","model","product_type",metaDataJSON,
                pricingInformationJSON, productDescriptionJSON);
    }

    private ProductJSON fakeRequestNegative(){
        MetaDataJSON metaDataJSON = new MetaDataJSON("title","site_name","description",
                "keywords", "canonical");
        PricingInformationJSON pricingInformationJSON = new PricingInformationJSON( new BigDecimal("10.00"),
                new BigDecimal("-9.00"), new BigDecimal("10.00"));
        ProductDescriptionJSON productDescriptionJSON = new ProductDescriptionJSON("title","subtitle",
                "text");

        return new ProductJSON("1","name","model","product_type",metaDataJSON,
                pricingInformationJSON, productDescriptionJSON);
    }
    @Ignore
    @Test
    public void testForbidden() throws Exception{
        ProductJSON product = fakeRequest();
        ResponseEntity<String> entity = restTemplate.postForEntity(productUpdateURL, product, String.class);
        System.out.println(entity.getBody());
        assertEquals(HttpStatus.FORBIDDEN, entity.getStatusCode());
    }

    @Test
    public void getOk() throws Exception{
        ProductJSON product = fakeRequest();
        ResponseEntity<String> entity = restTemplate.postForEntity(productUpdateURL, product, String.class);
        System.out.println(entity.getBody());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    @Ignore
    @Test
    public void negativePrice() throws Exception{
        ProductJSON product = fakeRequestNegative();
        ResponseEntity<String> entity = restTemplate.postForEntity(productUpdateURL, product, String.class);
        System.out.println(entity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }

}
