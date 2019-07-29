package com.da.common.model.json;

public class ProductDescriptionJSON {
    private String title;
    private String subtitle;
    private String text;

    public ProductDescriptionJSON(){}

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setText(String text) {
        this.text = text;
    }
}
