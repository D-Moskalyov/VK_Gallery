package com.android.vk_gallery.app.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Photo {
    @SerializedName("pid")
    int pid;
    @SerializedName("aid")
    int aid;
    @SerializedName("owner_id")
    int owner_id;
    @SerializedName("sizes")
    ArrayList<PhotoURL> sizes;

    public Photo(){
        sizes = new ArrayList<PhotoURL>();
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public ArrayList<PhotoURL> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<PhotoURL> sizes) {
        this.sizes = sizes;
    }
}
