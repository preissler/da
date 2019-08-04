package com.da.product.repository;

import com.da.common.model.json.ProductJSON;

import java.util.Map;

public interface ProductRedisRepository {
    Map<String, ProductJSON> findAll();
    ProductJSON findByid(String id);
}
