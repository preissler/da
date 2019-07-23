package com.da.wapi.model;
import java.time.LocalDateTime;


public class ProductUpdateResultJSON {
    private String result;
    private LocalDateTime dateTime;

    public ProductUpdateResultJSON(String result, LocalDateTime dateTime) {
        this.result = result;
        this.dateTime = dateTime;
    }

    public String getResult() {
        return result;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
