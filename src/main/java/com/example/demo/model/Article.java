package com.example.demo.model;

import javax.validation.constraints.NotEmpty;

public class Article {

    @NotEmpty
    private String aurthor;
    @NotEmpty
    private String title;

    private String description;
    private String thumnail;
    int id;

    private Catagory catagory;

    public Article() {
    }

    public Article(String aurthor, String title, String description, String thumnail, Catagory catagory) {
        this.aurthor = aurthor;
        this.title = title;
        this.description = description;
        this.thumnail = thumnail;
        this.catagory = catagory;

    }

    public Catagory getCatagory() {
        return catagory;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }

    public String getAurthor() {
        return aurthor;
    }

    public void setAurthor(String aurthor) {
        this.aurthor = aurthor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "aurthor='" + aurthor + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumnail='" + thumnail + '\'' +
                ", id=" + id +
                '}';
    }
}
