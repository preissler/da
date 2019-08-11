package com.da.persistence.model.db;




import com.da.persistence.common.model.db.MetaData;
import com.da.persistence.common.model.db.PricingInformation;
import com.da.persistence.common.model.db.Product;
import com.da.persistence.common.model.db.ProductDescription;
import com.da.persistence.common.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductTests {
    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testInitial()throws Exception{
        MetaData metaData = new MetaData("IDw","page_title","site name","description","keywords", "//canonical//");
        PricingInformation pricingInformation = new PricingInformation("IDw", new BigDecimal("20.00"),new BigDecimal("10.00"), new BigDecimal("20.00") );
        ProductDescription productDescription = new ProductDescription("IDw","title product", "subtitle", "text");

        Product product = new Product("IDw", "name","Number", "product type",metaData, pricingInformation, productDescription);
        Product result = productRepository.save(product);
        Assert.assertFalse(result == null);
    }




}
