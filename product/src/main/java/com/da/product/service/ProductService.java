package com.da.product.service;

import com.da.common.model.json.ProductJSON;
import com.da.product.repository.ProductRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRedisRepository redisRepository;
    public List<ProductJSON> getAllProducts(){
        return new ArrayList<ProductJSON>(redisRepository.findAll().values());
    }

}
