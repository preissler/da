package com.da.persistance.model.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class MetaData {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String page_title;
    private String site_name;
    private String description;
    private String keywords;
    private String canonical;

    public MetaData(String page_title, String site_name, String description, String keywords, String canonical) {
        this.page_title = page_title;
        this.site_name = site_name;
        this.description = description;
        this.keywords = keywords;
        this.canonical = canonical;
    }

    public UUID getId() {
        return id;
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
}
