package com.android.vk_gallery.app.model;


import com.android.vk_gallery.app.modelRealm.Photo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CollectionPhotos {
    @SerializedName("items")
    ArrayList<Photo> photos;

    public CollectionPhotos() {
        photos = new ArrayList<Photo>();
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photoItems) {
        this.photos = photoItems;
    }


}
