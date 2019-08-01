package com.da.persistance.service;

import com.da.common.model.json.ProductJSON;
import com.da.persistance.common.model.db.Product;
import com.da.persistance.common.repository.ProductRepository;
import com.da.persistance.helper.ProductHelper;

import com.da.persistance.repository.ProductRedisRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
    private static final Logger log = LogManager.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductHelper productHelper;

    private ProductRedisRepository redisRepository;

    protected Product getProduct(ProductJSON productJSON){
      Product result = new Product();
        try {
            result = productRepository.findById(productJSON.getId()).orElse(new Product());
        } catch (Exception e) {

            log.error("Error not UUID" + productJSON.getId());
        }

      return result;
    }

    public void upDateProduct(ProductJSON productJSON ) {
        Product product = getProduct(productJSON);
        try {
            Product toSave = productHelper.copyValues(product,productJSON);
            Product result = productRepository.save(toSave);
            if(redisRepository.findByid(productJSON.getId())!=null){
                log.info("Update in cache " + productJSON.getId());
                redisRepository.update(productJSON);
            }else{
                log.info("Save in cache " + productJSON.getId());
                redisRepository.save(productJSON);
            }

            log.info("Product " + product.getId());
        } catch (Exception e) {
            log.error("Not possible to copyValues");
        }


    }
}
