package com.da.persistence.common.validator;

import com.da.persistence.common.ProjectCommonsTestApplication;
import com.da.persistence.common.model.db.MetaData;
import com.da.persistence.common.model.db.PricingInformation;
import com.da.persistence.common.model.db.Product;
import com.da.persistence.common.model.db.ProductDescription;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProjectCommonsTestApplication.class)
public class ProductValidatorTests {
    @Autowired
    ProductValidator productValidator;


    MetaData metaData = new MetaData("ID","page_title","site name","description",
            "keywords", "//canonical//");
    PricingInformation pricingInformation = new PricingInformation("ID", new BigDecimal("20.00"),
            new BigDecimal("10.00"), new BigDecimal("20.00") );
    ProductDescription productDescription = new ProductDescription("ID", "title product",
            "subtitle", "text");
    Product product = new Product("ID", "name","Number", "product type",
            metaData, pricingInformation, productDescription);

    @Test
    public void testValid(){
        Assert.assertTrue(productValidator.isValid(product));
    }
    @Test
    public void testIdNull(){
        product.setId(null);
        Assert.assertFalse(productValidator.isValid(product));
    }
    @Test
    public void testIdEmpty(){
        product.setId("");
        Assert.assertFalse(productValidator.isValid(product));
    }
    @Test
    public void testNameEmpty(){
        product.setName("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testNameNull(){
        product.setName(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testProductEmpty(){
        product.setProduct_type("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testProductNull(){
        product.setProduct_type(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testModel_numberEmpty(){
        product.setModel_number("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testModel_numberNull(){
        product.setModel_number(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testPricing_informationNull(){
        product.setPricing_information(null);
        Assert.assertFalse(productValidator.isValid(product));
    }
    @Test
    public void testPricing_informationIdNull(){
        product.getPricing_information().setId(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testPricing_informationIdEmpty(){
        product.getPricing_information().setId("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testPricing_informationStandard_price_no_vatNull(){
        product.getPricing_information().setStandard_price_no_vat(null);
        Assert.assertFalse(productValidator.isValid(product));
    }
    @Test
    public void testPricing_informationStandard_priceNull(){
        product.getPricing_information().setStandard_price(null);
        Assert.assertFalse(productValidator.isValid(product));
    }
    @Test
    public void testPricing_informationCurrentPriceNull(){
        product.getPricing_information().setCurrentPrice(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataNull(){
        product.setMetaData(null);
        Assert.assertFalse(productValidator.isValid(product));
    }
    @Test
    public void testMetaDataIDNull(){
        product.getMetaData().setId(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataIDEmpty(){
        product.getMetaData().setId("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataPage_titleNull(){
        product.getMetaData().setPage_title(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataPage_titleEmpty(){
        product.getMetaData().setPage_title("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataSite_nameNull(){
        product.getMetaData().setSite_name(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataSite_nameEmpty(){
        product.getMetaData().setSite_name("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataKeywordsNull(){
        product.getMetaData().setKeywords(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataKeywordsEmpty(){
        product.getMetaData().setKeywords("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataDescriptionNull(){
        product.getMetaData().setDescription(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataDescriptionEmpty(){
        product.getMetaData().setDescription("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataCanonicalNull(){
        product.getMetaData().setCanonical(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testMetaDataCanonicalEmpty(){
        product.getMetaData().setCanonical("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testProduct_descriptionNull(){
        product.setProduct_description(null);
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testProduct_descriptionIdNull(){
        product.getProduct_description().setId(null);
        Assert.assertFalse(productValidator.isValid(product));
    }
    @Test
    public void testProduct_descriptionIdEmpty(){
        product.getProduct_description().setId("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testProduct_descriptionTitleNull(){
        product.getProduct_description().setTitle(null);
        Assert.assertFalse(productValidator.isValid(product));
    }
    @Test
    public void testProduct_descriptionTitleEmpty(){
        product.getProduct_description().setTitle("");
        Assert.assertFalse(productValidator.isValid(product));
    }

    @Test
    public void testProduct_descriptionTextNull(){
        product.getProduct_description().setText(null);
        Assert.assertFalse(productValidator.isValid(product));
    }
    @Test
    public void testProduct_descriptionTextEmpty(){
        product.getProduct_description().setText("");
        Assert.assertFalse(productValidator.isValid(product));
    }
    @Test
    public void testProduct_descriptionSubtitleNull(){
        product.getProduct_description().setSubtitle(null);
        Assert.assertFalse(productValidator.isValid(product));
    }
    @Test
    public void testProduct_descriptionSubtitleEmpty(){
        product.getProduct_description().setSubtitle("");
        Assert.assertFalse(productValidator.isValid(product));
    }


}

