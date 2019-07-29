package com.da.common.model.json;

public class MetaDataJSON {
    private String page_title;
    private String site_name;
    private String description;
    private String keywords;
    private String canonical;

    public MetaDataJSON(){}

    public MetaDataJSON(String page_title, String site_name, String description, String keywords, String canonical) {
        this.page_title = page_title;
        this.site_name = site_name;
        this.description = description;
        this.keywords = keywords;
        this.canonical = canonical;
    }

    public String getPage_title() {
        return page_title;
    }

    public String getSite_name() {
        return site_name;
    }

    public String getDescription() {
        return description;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getCanonical() {
        return canonical;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setCanonical(String canonical) {
        this.canonical = canonical;
    }
}
