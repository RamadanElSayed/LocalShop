package com.example.ramadanel_sayed.localshop.MyClasses;

import java.io.Serializable;

/**
 * Created by Ramadan El-Sayed on 12/26/2016.
 */
public class ClassforsellPhone implements Serializable {

    String publishDate;
    String NameofPhone;
    String FirstImage;

    public ClassforsellPhone() {
    }

    public ClassforsellPhone(String publishDate, String nameofPhone, String firstImage) {
        this.publishDate = publishDate;
        NameofPhone = nameofPhone;
        FirstImage = firstImage;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getNameofPhone() {
        return NameofPhone;
    }

    public void setNameofPhone(String nameofPhone) {
        NameofPhone = nameofPhone;
    }

    public String getFirstImage() {
        return FirstImage;
    }

    public void setFirstImage(String firstImage) {
        FirstImage = firstImage;
    }
}