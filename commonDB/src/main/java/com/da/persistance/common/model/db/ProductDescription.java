package com.da.persistance.common.model.db;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductDescription {
    @Id
    private String id;
    private String title;
    private String subtitle;
    private String text;

    public ProductDescription(String id ,String title, String subtitle, String text) {
        this.id=id;
        this.title = title;
        this.subtitle = subtitle;
        this.text = text;
    }

    public ProductDescription() {
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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
