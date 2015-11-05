package com.android.vk_gallery.app.service;


import android.os.Parcel;
import android.os.Parcelable;
import com.android.vk_gallery.app.modelRealm.PhotoURL;

public class PhotoURLParcelable implements Parcelable {

    private String src;
    private int width;
    private int height;
    private String type;

    public PhotoURLParcelable(PhotoURL photoURL){
        setType(photoURL.getType());
        setHeight(photoURL.getHeight());
        setWidth(photoURL.getWidth());
        setSrc(photoURL.getSrc());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
