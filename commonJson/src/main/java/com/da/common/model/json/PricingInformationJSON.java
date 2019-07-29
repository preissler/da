package com.da.common.model.json;

import java.math.BigDecimal;

public class PricingInformationJSON {
    private BigDecimal standard_price;
    private BigDecimal standard_price_no_vat;
    private BigDecimal currentPrice;

    public PricingInformationJSON(){}

    public PricingInformationJSON(BigDecimal standard_price, BigDecimal standard_price_no_vat, BigDecimal currentPrice) {
        this.standard_price = standard_price;
        this.standard_price_no_vat = standard_price_no_vat;
        this.currentPrice = currentPrice;
    }

    public BigDecimal getStandard_price() {
        return standard_price;
    }

    public BigDecimal getStandard_price_no_vat() {
        return standard_price_no_vat;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setStandard_price(BigDecimal standard_price) {
        this.standard_price = standard_price;
    }

    public void setStandard_price_no_vat(BigDecimal standard_price_no_vat) {
        this.standard_price_no_vat = standard_price_no_vat;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
}
