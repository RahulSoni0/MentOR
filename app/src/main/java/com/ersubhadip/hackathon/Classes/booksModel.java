package com.ersubhadip.hackathon.Classes;

public class booksModel {

    private String title,caption;
    private int imageAddress;

    public booksModel(String title, String caption, int imageAddress) {
        this.title = title;
        this.caption = caption;
        this.imageAddress = imageAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(int imageAddress) {
        this.imageAddress = imageAddress;
    }
}

