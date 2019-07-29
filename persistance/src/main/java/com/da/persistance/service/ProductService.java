package com.da.persistance.service;

import com.da.common.model.json.ProductJSON;
import com.da.persistance.model.db.Product;
import com.da.persistance.helper.ProductHelper;
import com.da.persistance.repository.ProductRepository;
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
    protected Product getProduct(ProductJSON productJSON){
      Product result = new Product();
        try {
            UUID id = UUID.fromString(productJSON.getId());
            result = productRepository.findById(id).orElse(new Product());
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
            log.info("Product " + product.getId());
        } catch (Exception e) {
            log.error("Not possible to copyValues");
        }


    }
}
