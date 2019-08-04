package com.da.product.controller;

import com.da.common.model.json.ProductJSON;
import com.da.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;



    @GetMapping(path = "/api/v1/products")
    WebAsyncTask<List<ProductJSON>> allProducts(@RequestParam(defaultValue="5") long time){
        Callable<List<ProductJSON>> callable = () -> {return productService.getAllProducts();};

        WebAsyncTask<List<ProductJSON>> webAsyncTask = new WebAsyncTask<List<ProductJSON>>(time * 6000,
                callable);
        webAsyncTask.onTimeout(() -> {
            throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT);
        });
        return webAsyncTask;
    }

}
