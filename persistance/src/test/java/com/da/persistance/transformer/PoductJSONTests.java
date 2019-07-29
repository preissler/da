package com.da.persistance.transformer;


import com.da.common.model.json.ProductJSON;

import com.da.persistance.model.db.MetaData;
import com.da.persistance.model.db.PricingInformation;
import com.da.persistance.model.db.Product;
import com.da.persistance.model.db.ProductDescription;
import com.da.persistance.repository.ProductRepository;
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
public class PoductJSONTests {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTransformer productTransformer;
    @Test
    public void testTransformToJSON() throws Exception{
        MetaData metaData = new MetaData("page_title","site name","description","keywords", "//canonical//");
        PricingInformation pricingInformation = new PricingInformation(new BigDecimal("20.00"),new BigDecimal("10.00"), new BigDecimal("20.00") );
        ProductDescription productDescription = new ProductDescription("title product", "subtitle", "text");
        Product product = new Product("name","Number", "product type",metaData, pricingInformation, productDescription);
        Product result = productRepository.save(product);
        ProductJSON productJSON = productTransformer.toProductJSON(result);
        Assert.assertEquals(productJSON.getId(), result.getId().toString());
        Assert.assertFalse(productJSON.getId() ==null);
        Assert.assertEquals(productJSON.getName(), result.getName());
        Assert.assertFalse(productJSON.getName() == null);
        Assert.assertEquals(productJSON.getModel_number(), result.getModel_number());
        Assert.assertFalse(productJSON.getModel_number() == null);
        Assert.assertEquals(productJSON.getProduct_type(), result.getProduct_type());
        Assert.assertFalse(productJSON.getProduct_type() ==null);
        Assert.assertEquals(productJSON.getMeta_data().getCanonical(), result.getMetaData().getCanonical());
        Assert.assertFalse(productJSON.getMeta_data().getCanonical() == null);
        Assert.assertEquals(productJSON.getMeta_data().getDescription(), result.getMetaData().getDescription());
        Assert.assertFalse(productJSON.getMeta_data().getDescription() == null);
        Assert.assertEquals(productJSON.getMeta_data().getKeywords(), result.getMetaData().getKeywords());
        Assert.assertFalse(productJSON.getMeta_data().getKeywords() == null);
        Assert.assertEquals(productJSON.getMeta_data().getPage_title(), result.getMetaData().getPage_title());
        Assert.assertFalse(productJSON.getMeta_data().getPage_title() == null);
        Assert.assertEquals(productJSON.getMeta_data().getSite_name(), result.getMetaData().getSite_name());
        Assert.assertFalse(productJSON.getMeta_data().getSite_name() == null);
        Assert.assertEquals(productJSON.getPricing_information().getCurrentPrice(), result.getPricing_information().getCurrentPrice());
        Assert.assertFalse(productJSON.getPricing_information().getCurrentPrice() == null);
        Assert.assertEquals(productJSON.getPricing_information().getStandard_price(), result.getPricing_information().getStandard_price());
        Assert.assertFalse(productJSON.getPricing_information().getStandard_price() == null);
        Assert.assertEquals(productJSON.getPricing_information().getStandard_price_no_vat(), result.getPricing_information().getStandard_price_no_vat());
        Assert.assertFalse(productJSON.getPricing_information().getStandard_price_no_vat() == null);
        Assert.assertEquals(productJSON.getProduct_description().getSubtitle(), result.getProduct_description().getSubtitle());
        Assert.assertFalse(productJSON.getProduct_description().getSubtitle() == null);
        Assert.assertEquals(productJSON.getProduct_description().getText(), result.getProduct_description().getText());
        Assert.assertFalse(productJSON.getProduct_description().getText() == null);
        Assert.assertEquals(productJSON.getProduct_description().getSubtitle(), result.getProduct_description().getSubtitle());
        Assert.assertFalse(productJSON.getProduct_description().getSubtitle() == null);
        Assert.assertEquals(productJSON.getProduct_description().getTitle(), result.getProduct_description().getTitle());
        Assert.assertFalse(productJSON.getProduct_description().getTitle()== null);
    }
}
