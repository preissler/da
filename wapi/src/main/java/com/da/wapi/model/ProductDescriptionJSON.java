package com.da.wapi.model;

public class ProductDescriptionJSON {
    private String title;
    private String subtitle;
    private String text;

    public ProductDescriptionJSON(String title, String subtitle, String text) {
        this.title = title;
        this.subtitle = subtitle;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getText() {
        return text;
    }
}
