package com.da.wapi.validation;

import com.da.common.model.json.PricingInformationJSON;
import com.da.common.model.json.ProductJSON;
import com.da.wapi.exception.EmptyFieldError;
import com.da.wapi.exception.PriceIsZeroOrNegativeError;
import com.da.wapi.exception.WrongIdFormatError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class RequestValidator {
    private static final Logger log = LoggerFactory.getLogger(RequestValidator.class);
    protected  void validatePrices(PricingInformationJSON pricingInformationJSON){

        if(pricingInformationJSON.getStandard_price_no_vat().compareTo(BigDecimal.ZERO)==0){
            log.error("Standard Price No Vat is ZERO");
            throw new PriceIsZeroOrNegativeError("Standard Price No Vat is ZERO");
        }
        if(pricingInformationJSON.getStandard_price().compareTo(BigDecimal.ZERO)==0){
            log.error("Standard Price is ZERO");
            throw new PriceIsZeroOrNegativeError("Standard Price is ZERO");
        }
        if(pricingInformationJSON.getCurrentPrice().compareTo(BigDecimal.ZERO)==0){
            log.error("Current Price No Vat is ZERO");
            throw new PriceIsZeroOrNegativeError("Current No Vat is ZERO");
        }
        if(pricingInformationJSON.getStandard_price_no_vat().compareTo(BigDecimal.ZERO)==-1){
            log.error("Standard Price No Vat is Negative");
            throw new PriceIsZeroOrNegativeError("Standard Price No Vat is Negative");
        }
        if(pricingInformationJSON.getStandard_price().compareTo(BigDecimal.ZERO)==-1){
            log.error("Standard Price is Negative");
            throw new PriceIsZeroOrNegativeError("Standard Price is Negative");
        }
        if(pricingInformationJSON.getCurrentPrice().compareTo(BigDecimal.ZERO)==-1){
            log.error("Current Price No Vat is Negative");
            throw new PriceIsZeroOrNegativeError("Current No Vat is Negative");
        }

    }

    protected void validateID(ProductJSON product){
        if(product !=null && (product.getId()!= null && !product.getId().isEmpty())){
            try{
                UUID.fromString(product.getId());
            }catch (Exception ex){
                throw new WrongIdFormatError("Id is not UUID format");
            }
        }

    }

    public void validateFields(ProductJSON product){
        if(product == null){
            log.error("Product is null");
            throw new EmptyFieldError("Product is null");
        }
        validateID(product);
        if(product.getName()== null || product.getName().isEmpty()){
            log.error("Product name is null or empty");
            throw new EmptyFieldError("Product name is null or empty");
        }
        if(product.getModel_number()==null || product.getModel_number().isEmpty()){
            log.error("Product Model Number is null or empty");
            throw new EmptyFieldError("Product Model Numeber is null or empty");
        }
        if(product.getProduct_type()== null ||product.getProduct_type().isEmpty() ){
            log.error("Product Type is null or empty");
            throw new EmptyFieldError("Product Type is null or empty");
        }
        if(product.getMeta_data() == null){
            log.error("Product Meta Data is null");
            throw new EmptyFieldError("Product Meta Data is null");
        }
        if(product.getMeta_data().getCanonical() == null || product.getMeta_data().getCanonical().isEmpty()){
            log.error("Canonical is null or empty");
            throw new EmptyFieldError("Canonical is null or empty");
        }
        if(product.getMeta_data().getDescription() == null || product.getMeta_data().getDescription() .isEmpty()){
            log.error("Description is null or empty");
            throw new EmptyFieldError("Description is null or empty");
        }
        if(product.getMeta_data().getKeywords() == null || product.getMeta_data().getKeywords().isEmpty()){
            log.error("Keywords is null or empty");
            throw new EmptyFieldError("Keywords is null or empty");
        }
        if(product.getMeta_data().getPage_title() == null || product.getMeta_data().getPage_title().isEmpty()){
            log.error("Page Title is null or empty");
            throw new EmptyFieldError("Page Title is null or empty");
        }
        if(product.getMeta_data().getSite_name() == null || product.getMeta_data().getSite_name().isEmpty()){
            log.error("Site Name is null or empty");
            throw new EmptyFieldError("Site Name is null or empty");
        }
        if(product.getPricing_information()== null){
            log.error("Product Price Information is null");
            throw new EmptyFieldError("Product Price Information is null");
        }
        if(product.getPricing_information().getCurrentPrice() == null){
            log.error("CurrentPrice is null");
            throw new EmptyFieldError("CurrentPrice is null");
        }
        if(product.getPricing_information().getStandard_price() == null){
            log.error("Standard price is null");
            throw new EmptyFieldError("Standard price  is null");
        }
        if(product.getPricing_information().getStandard_price_no_vat()== null){
            log.error("Standard Price No Vat is null");
            throw new EmptyFieldError("Standard Price No Vat is null");
        }
        if(product.getProduct_description()== null){
            log.error("Product Description is null");
            throw new EmptyFieldError("Product Description is null");
        }
        if(product.getProduct_description().getTitle() == null || product.getProduct_description().getTitle().isEmpty()){
            log.error("Product Description Title is null or empty");
            throw new EmptyFieldError("Product Description Title  is null or empty");
        }
        if(product.getProduct_description().getSubtitle() == null || product.getProduct_description().getSubtitle().isEmpty()){
            log.error("Product Description Subtitle is null or empty");
            throw new EmptyFieldError("Product Description Subtitle  is null or empty");
        }
        if(product.getProduct_description().getText() == null || product.getProduct_description().getText().isEmpty()){
            log.error("Product Description Text is null or empty");
            throw new EmptyFieldError("Product Description Text is null or empty");
        }
        validatePrices(product.getPricing_information());
    }
}
