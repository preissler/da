package com.da.persistence.controller;

import com.da.common.model.json.ProductJSON;
import com.da.persistence.exception.TimeoutError;
import com.da.persistence.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;



    @GetMapping(path = "/api/v1/products")
    @PreAuthorize("hasRole('ROLE_USER')")
    WebAsyncTask<List<ProductJSON>> allProducts(@RequestParam(defaultValue="1") long time, Pageable pageable){
        Callable<List<ProductJSON>> callable = () -> {return productService.getAllProducts(pageable);};

        WebAsyncTask<List<ProductJSON>> webAsyncTask = new WebAsyncTask<List<ProductJSON>>(time * 6000,
                callable);
        webAsyncTask.onTimeout(() -> {
            throw new TimeoutError("Request timeout");
        });
        return webAsyncTask;
    }

}
