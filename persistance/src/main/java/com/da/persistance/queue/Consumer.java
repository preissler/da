package com.da.persistance.queue;

import com.da.common.model.json.ProductJSON;
import com.da.persistance.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Consumer {
    private static final Logger log = LogManager.getLogger(Consumer.class);

    @Autowired
    private ProductService productService;

    public void handleMessage(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Received <" + message + ">");
        try {
            ProductJSON productJSON = objectMapper.readValue(message, ProductJSON.class);
            log.info("Processing..." + productJSON.getName());
            productService.upDateProduct(productJSON);

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Message processed...");
    }


}
