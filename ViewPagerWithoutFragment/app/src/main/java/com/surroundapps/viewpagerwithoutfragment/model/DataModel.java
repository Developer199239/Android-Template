package com.surroundapps.viewpagerwithoutfragment.model;

public class DataModel {
    private String text;
    private int image;

    public DataModel() {
    }

    public DataModel(String text, int image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
