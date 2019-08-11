package com.da.persistence.service;

import com.da.common.model.json.ProductJSON;
import com.da.persistence.common.model.redis.ProductRedis;
import com.da.persistence.common.repository.ProductRedisRepository;
import com.da.persistence.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    @Autowired
    private ProductRedisRepository redisRepository;
    @Autowired
    private ProductTransformer productTransformer;

    public List<ProductJSON> getAllProducts(Pageable pageable){
        Iterable<ProductRedis> products = redisRepository.findAll(pageable);
        return StreamSupport.stream(products.spliterator(),false)
                .map(r -> productTransformer.toProductJSON(r)).collect(Collectors.toList());
    }

}
