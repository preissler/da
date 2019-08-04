package com.da.persistance.common.repository;

import com.da.persistance.common.model.redis.ProductRedis;
import org.springframework.data.repository.CrudRepository;

public interface ProductRedisRepository extends CrudRepository<ProductRedis, String> {
}
