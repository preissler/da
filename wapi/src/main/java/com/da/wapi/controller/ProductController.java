package com.da.wapi.controller;

import com.da.wapi.configuration.AuthenticationError;
import com.da.wapi.model.ProductJSON;
import com.da.wapi.model.ProductUpdateResultJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @PostMapping(path="/api/v1/update")
    public ProductUpdateResultJSON update(@RequestBody ProductJSON product){
       //TODO define the security
        //1 validate the quest
        //2 add it to queue
        //3 wait for response? should it be sync?
        //4 return result
        return new ProductUpdateResultJSON("Ok", LocalDateTime.now());
    }
}
