package com.da.persistance.helper;




import com.da.common.model.json.ProductJSON;


import com.da.persistance.common.model.db.MetaData;
import com.da.persistance.common.model.db.PricingInformation;
import com.da.persistance.common.model.db.Product;
import com.da.persistance.common.model.db.ProductDescription;
import org.springframework.stereotype.Component;

@Component
public class ProductHelper {

    //TODO refactoring
    public Product copyValues(Product product, ProductJSON origin) throws Exception{
        if(product == null){
            throw new Exception("product null");
        }
        if (origin == null ){
            throw new Exception("origin null");
        }
        product.setId(origin.getId());
        product.setModel_number(origin.getModel_number());
        product.setName(origin.getName());
        product.setProduct_type(origin.getProduct_type());
        MetaData metaData = product.getMetaData();
        if(metaData == null) metaData = new MetaData();
        metaData.setId(origin.getId());
        metaData.setCanonical(origin.getMeta_data().getCanonical());
        metaData.setDescription(origin.getMeta_data().getDescription());
        metaData.setKeywords(origin.getMeta_data().getKeywords());
        metaData.setSite_name(origin.getMeta_data().getSite_name());
        metaData.setPage_title(origin.getMeta_data().getPage_title());
        ProductDescription productDescription =  product.getProduct_description();
        if(productDescription==null) productDescription = new ProductDescription();
        productDescription.setId(origin.getId());
        productDescription.setSubtitle(origin.getProduct_description().getSubtitle());
        productDescription.setText(origin.getProduct_description().getText());
        productDescription.setTitle(origin.getProduct_description().getTitle());
        PricingInformation pricingInformation = product.getPricing_information();
        if(pricingInformation == null) pricingInformation = new PricingInformation();
        pricingInformation.setId(origin.getId());
        pricingInformation.setCurrentPrice(origin.getPricing_information().getCurrentPrice());
        pricingInformation.setStandard_price(origin.getPricing_information().getStandard_price());
        pricingInformation.setStandard_price_no_vat(origin.getPricing_information().getStandard_price_no_vat());
        return product;
    }
}
