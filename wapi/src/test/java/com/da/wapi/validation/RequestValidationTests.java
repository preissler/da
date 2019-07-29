package com.da.wapi.validation;

import com.da.common.model.json.MetaDataJSON;
import com.da.common.model.json.PricingInformationJSON;
import com.da.common.model.json.ProductDescriptionJSON;
import com.da.common.model.json.ProductJSON;
import com.da.wapi.exception.EmptyFieldError;
import com.da.wapi.exception.PriceIsZeroOrNegativeError;
import com.da.wapi.exception.WrongIdFormatError;
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
        requestValidator.validateFields(null);
    }

    @Test(expected = EmptyFieldError.class)
    public void productHasNullTest(){
        ProductJSON productJSON = new ProductJSON();
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productNameIsNullTest(){
        ProductJSON productJSON = getProduct();
        productJSON.setName(null);
        requestValidator.validateFields(productJSON);
    }


    @Test(expected = EmptyFieldError.class)
    public void productNameIsEmptyTest(){
        ProductJSON productJSON = getProduct();
        productJSON.setName("");
        requestValidator.validateFields(productJSON);
    }
    @Test(expected = EmptyFieldError.class)
    public void productModelNumberIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.setModel_number(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productModelNumberIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.setModel_number("");
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productProductTypeIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.setProduct_type(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productProductTypeIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.setProduct_type("");
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productMetaDataIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.setMeta_data(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productPageTitleIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setPage_title(null);
        requestValidator.validateFields(productJSON);
    }
    @Test(expected = EmptyFieldError.class)
    public void productPageTitleIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setPage_title("");
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productSiteNameIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setSite_name(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productSiteNameIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setSite_name("");
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setDescription(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setDescription("");
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productKeywordsIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setKeywords(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productKeywordsIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setKeywords("");
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productCanonicalIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setCanonical(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productCanonicalIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getMeta_data().setCanonical("");
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productPricingInformationIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.setPricing_information(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productStandardPriceIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productStandardPriceNoVatIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price_no_vat(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productCurrentPriceIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setCurrentPrice(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescription_detailIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.setProduct_description(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionTitleIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setTitle(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionTitleIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setTitle("");
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionSubtitleIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setSubtitle(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionSubtitleIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setSubtitle("");
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionTextIsNull(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setText(null);
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = EmptyFieldError.class)
    public void productDescriptionTExtIsEmpty(){
        ProductJSON productJSON = getProduct();
        productJSON.getProduct_description().setText("");
        requestValidator.validateFields(productJSON);
    }
    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productStandardPriceIsZero(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price(new BigDecimal("0.00"));
        requestValidator.validateFields(productJSON);
    }
    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productStandardPriceIsNegative(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price(new BigDecimal("-100.00"));
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productStandardPriceNoVatIsZero(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price_no_vat(new BigDecimal("0.00"));
        requestValidator.validateFields(productJSON);
    }
    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productStandardPriceNoVatIsNegative(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setStandard_price_no_vat(new BigDecimal("-100.00"));
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productCurrentPriceIsZero(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setCurrentPrice(new BigDecimal("0.00"));
        requestValidator.validateFields(productJSON);
    }
    @Test(expected = PriceIsZeroOrNegativeError.class)
    public void productCurrentPriceIsNegative(){
        ProductJSON productJSON = getProduct();
        productJSON.getPricing_information().setCurrentPrice(new BigDecimal("-100.00"));
        requestValidator.validateFields(productJSON);
    }

    @Test(expected = WrongIdFormatError.class)
    public void idIsNotUUID(){
        ProductJSON productJSON = getProduct();
        productJSON.setId("12232");
        requestValidator.validateFields(productJSON);
    }




}
