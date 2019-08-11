package com.da.persistence.common.validator;

import com.da.persistence.common.model.db.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {
    public Boolean isValid(Product product){
        if(product == null) return false;
        if(product.getId()==null ||product.getId().isEmpty()) return false;
        if(product.getName()==null ||product.getName().isEmpty()) return false;
        if(product.getModel_number()==null ||product.getModel_number().isEmpty()) return false;
        if(product.getProduct_type()==null ||product.getProduct_type().isEmpty()) return false;
        if(product.getMetaData() == null) return false;
        if(product.getMetaData().getId() == null || product.getMetaData().getId().isEmpty()) return false;
        if(product.getMetaData().getCanonical() == null || product.getMetaData().getCanonical() .isEmpty()) return false;
        if(product.getMetaData().getDescription() == null || product.getMetaData().getDescription().isEmpty()) return false;
        if(product.getMetaData().getKeywords() == null || product.getMetaData().getKeywords().isEmpty()) return false;
        if(product.getMetaData().getPage_title() == null || product.getMetaData().getPage_title().isEmpty()) return false;
        if(product.getMetaData().getSite_name() == null || product.getMetaData().getSite_name().isEmpty()) return false;
        if(product.getPricing_information() == null) return false;
        if(product.getPricing_information().getId() == null || product.getPricing_information().getId().isEmpty()) return false;
        if(product.getPricing_information().getCurrentPrice() == null) return false;
        if(product.getPricing_information().getStandard_price() == null) return false;
        if(product.getPricing_information().getStandard_price_no_vat() == null) return false;
        if(product.getProduct_description() == null) return false;
        if(product.getProduct_description().getId() == null || product.getProduct_description().getId().isEmpty()) return false;
        if(product.getProduct_description().getSubtitle() == null || product.getProduct_description().getSubtitle().isEmpty()) return false;
        if(product.getProduct_description().getText() == null || product.getProduct_description().getText().isEmpty()) return false;
        if(product.getProduct_description().getTitle() == null || product.getProduct_description().getTitle() .isEmpty()) return false;
        return true;
    }
}
