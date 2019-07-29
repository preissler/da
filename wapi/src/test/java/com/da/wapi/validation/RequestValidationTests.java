package com.da.wapi.validation;

import com.da.common.model.json.MetaDataJSON;
import com.da.common.model.json.PricingInformationJSON;
import com.da.common.model.json.ProductDescriptionJSON;
import com.da.common.model.json.ProductJSON;
import com.da.wapi.exception.EmptyFieldError;
import com.da.wapi.exception.PriceIsZeroOrNegativeError;
import com.da.wapi.exception.WrongIdFormatError;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestValidationTests {
    @Autowired
    RequestValidator requestValidator;

    private ProductJSON getProduct(){
        MetaDataJSON metaDataJSON = new MetaDataJSON("title","site_name","description",
                "keywords", "canonical");
        PricingInformationJSON pricingInformationJSON = new PricingInformationJSON( new BigDecimal("10.00"),
                new BigDecimal("9.00"), new BigDecimal("10.00"));
        ProductDescriptionJSON productDescriptionJSON = new ProductDescriptionJSON("title","subtitle",
                "text");


        return new ProductJSON(UUID.randomUUID().toString(),"name","model","product_type",metaDataJSON,
                pricingInformationJSON, productDescriptionJSON);
    }


    @Test(expected = EmptyFieldError.class)
    public void productIsNullTest(){
        requestValidator.validateEmptyFields(null);
    }

    @Test(expected = EmptyFieldError.class)
    public void productHasNullTest(){
        ProductJSON productJSON = new ProductJSON();
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productNameIsNullTest(){
        ProductJSON productJSON = getProduct();
        productJSON.setName(null);
        requestValidator.validateEmptyFields(productJSON);
    }


    @Test(expected = EmptyFieldError.class)
    public void productNameIsEmptyTest(){
        ProductJSON productJSON = getProduct();
        productJSON.setName("");
        requestValidator.validateEmptyFields(productJSON);
    }
    @Test(expected = EmptyFieldError.class)
    public void productModelNumberIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.setModel_number(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productModelNumberIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.setModel_number("");
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productProductTypeIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.setProduct_type(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productProductTypeIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.setProduct_type("");
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productMetaDataIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.setMeta_data(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productPageTitleIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setPage_title(null);
        requestValidator.validateEmptyFields(productJSON);
    }
    @Test(expected = EmptyFieldError.class)
    public void productPageTitleIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setPage_title("");
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productSiteNameIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setSite_name(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productSiteNameIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setSite_name("");
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setDescription(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setDescription("");
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productKeywordsIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setKeywords(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productKeywordsIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setKeywords("");
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productCanonicalIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setCanonical(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productCanonicalIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setCanonical("");
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productPricingInformationIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.setPricing_information(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productStandardPriceIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productStandardPriceNoVatIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price_no_vat(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productCurrentPriceIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setCurrentPrice(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescription_detailIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.setProduct_description(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionTitleIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setTitle(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionTitleIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setTitle("");
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionSubtitleIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setSubtitle(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionSubtitleIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setSubtitle("");
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionTextIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setText(null);
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionTExtIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setText("");
        requestValidator.validateEmptyFields(productJSON);
    }
    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productStandardPriceIsZero(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price(new BigDecimal("0.00"));
        requestValidator.validateEmptyFields(productJSON);
    }
    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productStandardPriceIsNegative(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price(new BigDecimal("-100.00"));
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productStandardPriceNoVatIsZero(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price_no_vat(new BigDecimal("0.00"));
        requestValidator.validateEmptyFields(productJSON);
    }
    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productStandardPriceNoVatIsNegative(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price_no_vat(new BigDecimal("-100.00"));
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productCurrentPriceIsZero(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setCurrentPrice(new BigDecimal("0.00"));
        requestValidator.validateEmptyFields(productJSON);
    }
    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productCurrentPriceIsNegative(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setCurrentPrice(new BigDecimal("-100.00"));
        requestValidator.validateEmptyFields(productJSON);
    }

    @Test(expected = WrongIdFormatError.class)
    public void idIsNotUUID(){
        ProductJSON productJSON = getProduct();
        productJSON.setId("12232");
        requestValidator.validateEmptyFields(productJSON);
    }




}
