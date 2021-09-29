package com.ersubhadip.hackathon;
//Yeh list hai apne homeRv ki
public class HomeRvModel {
    //iska kaam keval ek skeleton uplabdh karana hai
    //buddhi ya mehant wala kaam isse na karwayen
    private String title,caption;
    private int imageAddress;

    public HomeRvModel(String title, String caption, int imageAddress) {
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
