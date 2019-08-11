package com.da.persistence.configuration;

import com.da.persistence.common.model.redis.ProductRedis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {
    @Value("${spring.redis.host}")
    private String REDIS_HOSTNAME;
    @Value("${spring.redis.port}")
    private int REDIS_PORT;



    @Bean
    public RedisTemplate<String, ProductRedis> redisTemplate() {
        RedisTemplate<String, ProductRedis> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }


    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT));
    }


}

