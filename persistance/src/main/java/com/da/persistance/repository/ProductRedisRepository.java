package com.da.persistance.repository;

import com.da.common.model.json.ProductJSON;

import java.util.Map;

public interface ProductRedisRepository {
    void save(ProductJSON productJSON);
    Map<String, ProductJSON> findAll();
    ProductJSON findByid(String id);
    void update(ProductJSON productJSON);
}
