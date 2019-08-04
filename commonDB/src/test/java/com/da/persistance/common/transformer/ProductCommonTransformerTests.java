package com.da.persistance.common.transformer;


import com.da.persistance.common.ProjectCommonsTestApplication;
import com.da.persistance.common.exception.ProductDBTransformationError;
import com.da.persistance.common.model.db.MetaData;
import com.da.persistance.common.model.db.PricingInformation;
import com.da.persistance.common.model.db.Product;
import com.da.persistance.common.model.db.ProductDescription;
import com.da.persistance.common.model.redis.ProductRedis;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProjectCommonsTestApplication.class)
public class ProductCommonTransformerTests {
    @Autowired
    ProductCommonTransformer productCommonTransformer;

    MetaData metaData = new MetaData("ID","page_title","site name","description",
            "keywords", "//canonical//");
    PricingInformation pricingInformation = new PricingInformation("ID", new BigDecimal("20.00"),
            new BigDecimal("10.00"), new BigDecimal("20.00") );
    ProductDescription productDescription = new ProductDescription("ID", "title product",
            "subtitle", "text");
    Product product = new Product("ID", "name","Number", "product type",
            metaData, pricingInformation, productDescription);


    @Test
    public void testTransformer() throws Exception{

        ProductRedis productRedis = productCommonTransformer.toRedis(product);

        Assert.assertEquals(productRedis.getName(), product.getName());
        Assert.assertEquals(productRedis.getId(), product.getId());
        Assert.assertEquals(productRedis.getExternalId(), product.getId());
        Assert.assertEquals(productRedis.getModel_number(), product.getModel_number());

        Assert.assertEquals(productRedis.getProduct_type(), product.getProduct_type());
        Assert.assertEquals(productRedis.getMetaData().getId(), product.getMetaData().getId());
        Assert.assertEquals(productRedis.getMetaData().getCanonical(), product.getMetaData().getCanonical());
        Assert.assertEquals(productRedis.getMetaData().getDescription(), product.getMetaData().getDescription());
        Assert.assertEquals(productRedis.getMetaData().getKeywords(), product.getMetaData().getKeywords());
        Assert.assertEquals(productRedis.getMetaData().getPage_title(), product.getMetaData().getPage_title());
        Assert.assertEquals(productRedis.getMetaData().getSite_name(), product.getMetaData().getSite_name());

        Assert.assertEquals(productRedis.getPricing_information().getId(), product.getPricing_information().getId());
        Assert.assertEquals(productRedis.getPricing_information().getCurrentPrice(), product.getPricing_information().getCurrentPrice());
        Assert.assertEquals(productRedis.getPricing_information().getStandard_price(), product.getPricing_information().getStandard_price());
        Assert.assertEquals(productRedis.getPricing_information().getStandard_price_no_vat(), product.getPricing_information().getStandard_price_no_vat());


        Assert.assertEquals(productRedis.getProduct_description().getSubtitle(), product.getProduct_description().getSubtitle());
        Assert.assertEquals(productRedis.getProduct_description().getText(), product.getProduct_description().getText());
        Assert.assertEquals(productRedis.getProduct_description().getSubtitle(), product.getProduct_description().getSubtitle());
        Assert.assertEquals(productRedis.getProduct_description().getTitle(), product.getProduct_description().getTitle());
    }

    @Test(expected = ProductDBTransformationError.class)
    public void testProductInvalid() throws Exception{
        ProductRedis productRedis = productCommonTransformer.toRedis(null);
    }

    @Test(expected = ProductDBTransformationError.class)
    public void testProductInvalidIdEmpty() throws Exception{
        product.setId("");
        ProductRedis productRedis = productCommonTransformer.toRedis(product);
    }
}
