package com.da.persistance.model.db;

import com.da.persistance.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductTest {
    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testInitial()throws Exception{
        MetaData metaData = new MetaData("page_title","site name","description","keywords", "//canonical//");
        PricingInformation pricingInformation = new PricingInformation(new BigDecimal("20.00"),new BigDecimal("10.00"), new BigDecimal("20.00") );
        ProductDescription productDescription = new ProductDescription("title product", "subtitle", "text");

        Product product = new Product("name","Number", "product type",metaData, pricingInformation, productDescription);
        Product result = productRepository.save(product);
        Assert.assertFalse(result == null);
    }




}
