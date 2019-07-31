package com.da.wapi.controller;

import com.da.common.model.json.ProductJSON;
import com.da.wapi.exception.QueueError;

import com.da.wapi.model.ProductUpdateResultJSON;
import com.da.wapi.queue.Producer;
import com.da.wapi.validation.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private Producer producer;
    @Autowired
    private RequestValidator requestValidator;

    @PostMapping(path="/api/v1/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ProductUpdateResultJSON update(@RequestBody ProductJSON product){
        requestValidator.validateFields(product);
        try {
            producer.produce(product);
        } catch (Exception e) {
            log.error("Queue error, for product: " + product.getName() );
            log.error(e.getStackTrace().toString());
            throw new QueueError("Internal Error, message not sent");
        }
        //4 return result
        return new ProductUpdateResultJSON("Ok", LocalDateTime.now());
    }
}
