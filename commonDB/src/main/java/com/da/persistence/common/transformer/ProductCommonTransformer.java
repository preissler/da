package com.da.persistence.common.transformer;

import com.da.persistence.common.exception.ProductDBTransformationError;
import com.da.persistence.common.model.db.Product;
import com.da.persistence.common.model.redis.MetaDataRedis;
import com.da.persistence.common.model.redis.PricingInformationRedis;
import com.da.persistence.common.model.redis.ProductDescriptionRedis;
import com.da.persistence.common.model.redis.ProductRedis;
import com.da.persistence.common.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductCommonTransformer {
    @Autowired
    ProductValidator productValidator;


    public ProductRedis toRedis(Product product) throws ProductDBTransformationError {
        if(!productValidator.isValid(product)) {
            throw new ProductDBTransformationError("Product is null or empty");
        }
        MetaDataRedis metaDataRedis = new MetaDataRedis(product.getMetaData().getId(),product.getMetaData().getPage_title(),
                product.getMetaData().getSite_name(), product.getMetaData().getDescription(),
                product.getMetaData().getKeywords(), product.getMetaData().getCanonical());
        PricingInformationRedis pricingInformationRedis = new PricingInformationRedis(product.getPricing_information().getId(),
                product.getPricing_information().getStandard_price(), product.getPricing_information().getStandard_price_no_vat(),
                product.getPricing_information().getCurrentPrice());
        ProductDescriptionRedis productDescriptionRedis = new ProductDescriptionRedis(product.getProduct_description().getId(),
                product.getProduct_description().getTitle(), product.getProduct_description().getSubtitle(),
                product.getProduct_description().getText() );

        return new ProductRedis(product.getId(), product.getId(), product.getName(),product.getModel_number(),
                product.getProduct_type(), metaDataRedis, pricingInformationRedis, productDescriptionRedis);
    }
}
