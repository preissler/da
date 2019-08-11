package com.da.persistence.common.redis;

import com.da.persistence.common.ProjectCommonsTestApplication;
import com.da.persistence.common.model.redis.MetaDataRedis;
import com.da.persistence.common.model.redis.PricingInformationRedis;
import com.da.persistence.common.model.redis.ProductDescriptionRedis;
import com.da.persistence.common.model.redis.ProductRedis;
import com.da.persistence.common.repository.ProductRedisRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProjectCommonsTestApplication.class)
public class ProductRedisRepositoryTests {
    @Autowired
    ProductRedisRepository productRedisRepository;

    MetaDataRedis metaDataRedis = new MetaDataRedis("ID","page_title","site name","description",
            "keywords", "//canonical//");
    PricingInformationRedis pricingInformationRedis = new PricingInformationRedis("ID", new BigDecimal("20.00"),
            new BigDecimal("10.00"), new BigDecimal("20.00") );
    ProductDescriptionRedis productDescriptionRedis = new ProductDescriptionRedis("ID", "title product",
            "subtitle", "text");
    ProductRedis productRedis = new ProductRedis("ID", "ID", "name","Number", "product type",
            metaDataRedis, pricingInformationRedis, productDescriptionRedis);

    @Test
    public void testAdd() {
        ProductRedis productResult = productRedisRepository.save(productRedis);
        Assert.assertNotNull(productResult);
        Assert.assertEquals(productResult.getId(), productRedis.getId());

    }

    @Test
    public void testFindById(){
        ProductRedis productResult = productRedisRepository.save(productRedis);
        Assert.assertNotNull(productResult);
        ProductRedis found = productRedisRepository.findById(productRedis.getId()).get();
        Assert.assertEquals(found.getId(), productRedis.getId());
    }

    @Test
    public void testFindByIdNotNull(){
        ProductRedis productResult = productRedisRepository.save(productRedis);
        Assert.assertNotNull(productResult);
        ProductRedis found = productRedisRepository.findById(productRedis.getId()).get();
        Assert.assertEquals(found.getId(), productRedis.getId());
        Assert.assertNotNull(found.getPricing_information());
        Assert.assertNotNull(found.getMetaData());
        Assert.assertNotNull(found.getProduct_description());
    }

    @Test
    public void testFindByIdMetaData(){
        ProductRedis productResult = productRedisRepository.save(productRedis);
        Assert.assertNotNull(productResult);
        ProductRedis found = productRedisRepository.findById(productRedis.getId()).get();
        Assert.assertEquals(found.getMetaData().getId(),productRedis.getMetaData().getId() );
    }

    @Test
    public void testFindByIdPricing_information(){
        ProductRedis productResult = productRedisRepository.save(productRedis);
        Assert.assertNotNull(productResult);
        ProductRedis found = productRedisRepository.findById(productRedis.getId()).get();
        Assert.assertEquals(found.getPricing_information().getId(),productRedis.getPricing_information().getId() );
    }

    @Test
    public void testFindByIdProduct_description(){
        ProductRedis productResult = productRedisRepository.save(productRedis);
        Assert.assertNotNull(productResult);
        ProductRedis found = productRedisRepository.findById(productRedis.getId()).get();
        Assert.assertEquals(found.getProduct_description().getId(),productRedis.getProduct_description().getId() );
    }


}
