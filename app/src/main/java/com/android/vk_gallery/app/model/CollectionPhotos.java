package com.android.vk_gallery.app.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CollectionPhotos {
    @SerializedName("items")
    ArrayList<Photo> photoItems;

    public CollectionPhotos() {
        photoItems = new ArrayList<Photo>();
    }

    public ArrayList<Photo> getPhotoItems() {
        return photoItems;
    }

    public void setPhotoItems(ArrayList<Photo> photoItems) {
        this.photoItems = photoItems;
    }


}
