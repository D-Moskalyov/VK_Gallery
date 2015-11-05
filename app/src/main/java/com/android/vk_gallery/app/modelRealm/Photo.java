package com.android.vk_gallery.app.modelRealm;


import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Photo extends RealmObject {
    @PrimaryKey
    @SerializedName("pid")
    private int pid;
    @SerializedName("aid")
    private int aid;
    @SerializedName("owner_id")
    private int owner_id;
    @SerializedName("sizes")
    private RealmList<PhotoURL> sizes;

    public Photo(){
        sizes = new RealmList<PhotoURL>();
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

    public RealmList<PhotoURL> getSizes() {
        return sizes;
    }

    public void setSizes(RealmList<PhotoURL> sizes) {
        this.sizes = sizes;
    }
}
