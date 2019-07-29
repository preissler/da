package com.da.common.model.json;


public class ProductJSON {
    private String id;
    private String name;
    private String model_number;
    private String product_type;
    private MetaDataJSON meta_data;
    private PricingInformationJSON pricing_information;
    private ProductDescriptionJSON product_description;

    public ProductJSON(){}

    public ProductJSON(String id, String name, String model_number, String product_type, MetaDataJSON meta_data,
                       PricingInformationJSON pricing_information, ProductDescriptionJSON product_description) {
        this.id = id;
        this.name = name;
        this.model_number = model_number;
        this.product_type = product_type;
        this.meta_data = meta_data;
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

    public MetaDataJSON getMeta_data() {
        return meta_data;
    }

    public PricingInformationJSON getPricing_information() {
        return pricing_information;
    }

    public ProductDescriptionJSON getProduct_description() {
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

    public void setMeta_data(MetaDataJSON meta_data) {
        this.meta_data = meta_data;
    }

    public void setPricing_information(PricingInformationJSON pricing_information) {
        this.pricing_information = pricing_information;
    }

    public void setProduct_description(ProductDescriptionJSON product_description) {
        this.product_description = product_description;
    }
}
