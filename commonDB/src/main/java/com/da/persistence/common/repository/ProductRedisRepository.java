package com.da.persistence.common.repository;

import com.da.persistence.common.model.redis.ProductRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRedisRepository extends PagingAndSortingRepository<ProductRedis, String> {
}
