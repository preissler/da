package com.da.persistance.model.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class PricingInformation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private BigDecimal standard_price;
    private BigDecimal standard_price_no_vat;
    private BigDecimal currentPrice;

    public PricingInformation(BigDecimal standard_price, BigDecimal standard_price_no_vat, BigDecimal currentPrice) {
        this.standard_price = standard_price;
        this.standard_price_no_vat = standard_price_no_vat;
        this.currentPrice = currentPrice;
    }

    public UUID getId() {
        return id;
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
}
