package com.da.persistance.common.model.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Product {
    @Id
    private String id;
    private String name;
    private String model_number;
    private String product_type;
    @OneToOne(cascade = CascadeType.ALL)
    private MetaData metaData;
    @OneToOne(cascade = CascadeType.ALL)
    private PricingInformation pricing_information;
    @OneToOne(cascade = CascadeType.ALL)
    private ProductDescription product_description;

    public Product(){}

    public Product(String id, String name, String model_number, String product_type, MetaData metaData,
                   PricingInformation pricing_information, ProductDescription product_description) {
        this.id=id;
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

    public String getName() {
        return name;
    }

    public String getModel_number() {
        return model_number;
    }

    public String getProduct_type() {
        return product_type;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public PricingInformation getPricing_information() {
        return pricing_information;
    }

    public ProductDescription getProduct_description() {
        return product_description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel_number(String model_number) {
        this.model_number = model_number;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public void setPricing_information(PricingInformation pricing_information) {
        this.pricing_information = pricing_information;
    }

    public void setProduct_description(ProductDescription product_description) {
        this.product_description = product_description;
    }
}
