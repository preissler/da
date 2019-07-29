package com.da.persistance.transformer;




import com.da.common.model.json.MetaDataJSON;
import com.da.common.model.json.PricingInformationJSON;
import com.da.common.model.json.ProductDescriptionJSON;
import com.da.common.model.json.ProductJSON;


import com.da.persistance.model.db.MetaData;
import com.da.persistance.model.db.PricingInformation;
import com.da.persistance.model.db.Product;
import com.da.persistance.model.db.ProductDescription;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformer {

    protected MetaDataJSON toMetaDataJSON(MetaData metaData){
        MetaDataJSON metaDataJSON = new MetaDataJSON();
        metaDataJSON.setCanonical(metaData.getCanonical());
        metaDataJSON.setDescription(metaData.getDescription());
        metaDataJSON.setKeywords(metaData.getKeywords());
        metaDataJSON.setPage_title(metaData.getPage_title());
        metaDataJSON.setSite_name(metaData.getSite_name());
        return metaDataJSON;
    }

    protected PricingInformationJSON toPricingInformationJSON(PricingInformation pricingInformation){
        PricingInformationJSON pricingInformationJSON = new PricingInformationJSON();
        pricingInformationJSON.setCurrentPrice(pricingInformation.getCurrentPrice());
        pricingInformationJSON.setStandard_price(pricingInformation.getStandard_price());
        pricingInformationJSON.setStandard_price_no_vat(pricingInformation.getStandard_price_no_vat());
        return pricingInformationJSON;
    }

    protected ProductDescriptionJSON toProductDescriptionJSON(ProductDescription productDescription){
        ProductDescriptionJSON productDescriptionJSON = new ProductDescriptionJSON();
        productDescriptionJSON.setSubtitle(productDescription.getSubtitle());
        productDescriptionJSON.setText(productDescription.getText());
        productDescriptionJSON.setTitle(productDescription.getTitle());
        return productDescriptionJSON;
    }


    public ProductJSON toProductJSON(Product product){
        ProductJSON productJSON = new ProductJSON();
        productJSON.setId(product.getId().toString());
        productJSON.setName(product.getName());
        productJSON.setModel_number(product.getModel_number());
        productJSON.setProduct_type(product.getProduct_type());
        productJSON.setMeta_data(toMetaDataJSON(product.getMetaData()));
        productJSON.setPricing_information(toPricingInformationJSON(product.getPricing_information()));
        productJSON.setProduct_description(toProductDescriptionJSON(product.getProduct_description()));
        return productJSON;
    }
}
