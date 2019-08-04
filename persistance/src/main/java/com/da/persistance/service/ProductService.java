package com.da.persistance.service;

import com.da.common.model.json.ProductJSON;
import com.da.persistance.common.model.db.Product;
import com.da.persistance.common.model.redis.ProductRedis;
import com.da.persistance.common.repository.ProductRedisRepository;
import com.da.persistance.common.repository.ProductRepository;
import com.da.persistance.common.transformer.ProductCommonTransformer;
import com.da.persistance.transformer.ProductHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private static final Logger log = LogManager.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductHelper productHelper;
    @Autowired
    private ProductRedisRepository redisRepository;

    @Autowired
    private ProductCommonTransformer productCommonTransformer;



    protected Product getProduct(ProductJSON productJSON){
      Product result = new Product();
        try {
            result = productRepository.findById(productJSON.getId()).orElse(new Product());
        } catch (Exception e) {

            log.error("Error not UUID" + productJSON.getId());
        }

      return result;
    }
//TODO refactory it
    public void upDateProduct(ProductJSON productJSON ) {
        Product product = getProduct(productJSON);
        try {
            Product toSave = productHelper.copyValues(product,productJSON);
            Product result = productRepository.save(toSave);
            ProductRedis productRedis = productCommonTransformer.toRedis(result);
            ProductRedis redis = redisRepository.save(productRedis);
            log.info("Product " + product.getId());
        } catch (Exception e) {
            log.error("Not possible to copyValues");
        }
    }
}
