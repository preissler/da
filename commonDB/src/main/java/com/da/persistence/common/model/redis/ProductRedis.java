package com.da.persistence.common.model.redis;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@RedisHash("ProductRedis")
public class ProductRedis {
    @Id
    private String id;
    @Indexed
    private String externalId;
    private String name;
    private String model_number;
    private String product_type;
    private MetaDataRedis metaData;
    private PricingInformationRedis pricing_information;
    private ProductDescriptionRedis product_description;

    public ProductRedis(String id, String externalId, String name, String model_number, String product_type, MetaDataRedis metaData, PricingInformationRedis pricing_information, ProductDescriptionRedis product_description) {
        this.id = id;
        this.externalId = externalId;
        this.name = name;
        this.model_number = model_number;
        this.product_type = product_type;
        this.metaData = metaData;
        this.pricing_information = pricing_information;
        this.product_description = product_description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel_number() {
        return model_number;
    }

    public void setModel_number(String model_number) {
        this.model_number = model_number;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public MetaDataRedis getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaDataRedis metaData) {
        this.metaData = metaData;
    }

    public PricingInformationRedis getPricing_information() {
        return pricing_information;
    }

    public void setPricing_information(PricingInformationRedis pricing_information) {
        this.pricing_information = pricing_information;
    }

    public ProductDescriptionRedis getProduct_description() {
        return product_description;
    }

    public void setProduct_description(ProductDescriptionRedis product_description) {
        this.product_description = product_description;
    }
}
