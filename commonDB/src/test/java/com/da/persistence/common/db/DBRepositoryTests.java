package com.da.persistence.common.db;

import com.da.persistence.common.ProjectCommonsTestApplication;
import com.da.persistence.common.model.db.MetaData;
import com.da.persistence.common.model.db.PricingInformation;
import com.da.persistence.common.model.db.Product;
import com.da.persistence.common.model.db.ProductDescription;
import com.da.persistence.common.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProjectCommonsTestApplication.class)
public class DBRepositoryTests {
    @Autowired
    ProductRepository productRepository;

    MetaData metaData = new MetaData("ID","page_title","site name","description",
            "keywords", "//canonical//");
    PricingInformation pricingInformation = new PricingInformation("ID", new BigDecimal("20.00"),
            new BigDecimal("10.00"), new BigDecimal("20.00") );
    ProductDescription productDescription = new ProductDescription("ID", "title product",
            "subtitle", "text");
    Product product = new Product("ID", "name","Number", "product type",
            metaData, pricingInformation, productDescription);

    @Test
    public void testSave(){
        Product result = productRepository.save(product);
        Assert.assertEquals(result.getId(), product.getId());
    }
    @Test
    public void testFind(){
        Product result = productRepository.save(product);
        Assert.assertEquals(result.getId(), product.getId());
        Product found =  productRepository.findById(product.getId()).get();
        Assert.assertEquals(found.getId(), product.getId());
    }

    @Test
    public void testFindMetaData(){
        Product result = productRepository.save(product);
        Assert.assertEquals(result.getId(), product.getId());
        Product found =  productRepository.findById(product.getId()).get();
        Assert.assertNotNull(found.getMetaData());
        Assert.assertEquals(found.getMetaData().getId(), product.getMetaData().getId());
    }

    @Test
    public void testFindPriceInfo(){
        Product result = productRepository.save(product);
        Assert.assertEquals(result.getId(), product.getId());
        Product found =  productRepository.findById(product.getId()).get();
        Assert.assertNotNull(found.getPricing_information());
        Assert.assertEquals(found.getPricing_information().getId(), product.getPricing_information().getId());
    }

    @Test
    public void testFindProductDescription(){
        Product result = productRepository.save(product);
        Assert.assertEquals(result.getId(), product.getId());
        Product found =  productRepository.findById(product.getId()).get();
        Assert.assertNotNull(found.getProduct_description());
        Assert.assertEquals(found.getProduct_description().getId(), product.getProduct_description().getId());
    }
}
