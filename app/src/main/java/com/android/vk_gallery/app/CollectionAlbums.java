package com.android.vk_gallery.app;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionAlbums {

    @SerializedName("items")
    List<AlbumItem> albumItems;
//    @SerializedName("count")
//    int mCount;

    public CollectionAlbums(){
        albumItems = new ArrayList<AlbumItem>();
    }

    public List<AlbumItem> getAlbumItems() {
        return albumItems;
    }

    public void setAlbumItems(List<AlbumItem> albumItems) {
        this.albumItems = albumItems;
    }


//    public int getmCount() {
//        return mCount;
//    }
//
//    public void setmCount(int mCount) {
//        this.mCount = mCount;
//    }

}
