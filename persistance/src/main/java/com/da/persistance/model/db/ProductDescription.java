package com.da.persistance.model.db;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class ProductDescription {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String title;
    private String subtitle;
    private String text;

    public ProductDescription(String title, String subtitle, String text) {
        this.title = title;
        this.subtitle = subtitle;
        this.text = text;
    }

    public UUID getId() {
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
}
