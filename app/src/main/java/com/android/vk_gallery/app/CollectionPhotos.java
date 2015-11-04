package com.android.vk_gallery.app;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CollectionPhotos {
    @SerializedName("items")
    ArrayList<Photo> albumItems;

    public CollectionPhotos() {
        albumItems = new ArrayList<Photo>();
    }

    public ArrayList<Photo> getAlbumItems() {
        return albumItems;
    }

    public void setAlbumItems(ArrayList<Photo> albumItems) {
        this.albumItems = albumItems;
    }
}
