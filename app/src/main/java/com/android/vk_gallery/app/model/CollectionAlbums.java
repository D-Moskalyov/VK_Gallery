package com.android.vk_gallery.app.model;


import com.android.vk_gallery.app.modelRealm.Album;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;

import java.util.ArrayList;
import java.util.List;

public class CollectionAlbums  {

    @SerializedName("items")
    ArrayList<Album> albums;
//    @SerializedName("count")
//    int mCount;

    public CollectionAlbums(){
        albums = new ArrayList<Album>();
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }


//    public int getmCount() {
//        return mCount;
//    }
//
//    public void setmCount(int mCount) {
//        this.mCount = mCount;
//    }

}
