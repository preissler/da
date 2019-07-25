package com.da.persistance.model.db;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String name;
    private String model_number;
    private String product_type;
    @OneToOne
    private MetaData metaData;
    @OneToOne
    private PricingInformation pricing_information;
    @OneToOne
    private ProductDescription product_description;

    public Product(String name, String model_number, String product_type, MetaData metaData, PricingInformation pricing_information, ProductDescription product_description) {
        this.name = name;
        this.model_number = model_number;
        this.product_type = product_type;
        this.metaData = metaData;
        this.pricing_information = pricing_information;
        this.product_description = product_description;
    }

    public UUID getId() {
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
}
