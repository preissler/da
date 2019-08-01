package com.da.persistance.repository;

import com.da.common.model.json.ProductJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ProductRedisRepositoryImp implements ProductRedisRepository {
    private static String PROD ="PRODUCT";
    @Autowired
    private RedisTemplate<String, ProductJSON> redisTemplate;
    //private HashOperations hashOperations;

    @Autowired
    HashOperations<String, String, ProductJSON> hashOperations;

    public ProductRedisRepositoryImp(RedisTemplate<String, ProductJSON> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(ProductJSON productJSON) {
        hashOperations.put(PROD,productJSON.getId(), productJSON);

    }

    @Override
    public Map<String, ProductJSON> findAll() {
        return hashOperations.entries(PROD);
    }

    @Override
    public ProductJSON findByid(String id) {
        return (ProductJSON)hashOperations.get(PROD,id);
    }

    @Override
    public void update(ProductJSON productJSON) {
        save(productJSON);
    }
}
