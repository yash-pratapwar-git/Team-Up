package com.example.hp.myapplication;

public class product {

    private String id;
    private String title;
    private String shortdesc;
    private String rating;
    private String price;
    private int image;

    public product(String id, String title, String shortdesc, String rating) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public String getRating() {
        return rating;
    }

    public String getPrice() {
        return price;
    }
}

