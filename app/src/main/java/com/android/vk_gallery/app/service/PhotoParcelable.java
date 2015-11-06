package com.android.vk_gallery.app.service;


import android.os.Parcel;
import android.os.Parcelable;
import com.android.vk_gallery.app.modelRealm.Photo;
import com.android.vk_gallery.app.modelRealm.PhotoURL;
import io.realm.RealmList;

import java.util.ArrayList;
import java.util.List;

public class PhotoParcelable implements Parcelable{
    private int pid;
    private int aid;
    private int owner_id;
    private ArrayList<PhotoURLParcelable> sizes;

    public PhotoParcelable(Photo photo) {
        this.setAid(photo.getAid());
        this.setPid(photo.getPid());
        this.setOwner_id(photo.getOwner_id());
        List<PhotoURL> photoURLs = photo.getSizes();
        ArrayList<PhotoURLParcelable> photoURLParcelables = new ArrayList<PhotoURLParcelable>();
        for(PhotoURL photoURL : photoURLs){
            photoURLParcelables.add(new PhotoURLParcelable(photoURL));
        }
        this.setSizes(photoURLParcelables);
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

    public ArrayList<PhotoURLParcelable> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<PhotoURLParcelable> sizes) {
        this.sizes = sizes;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public int describeContents() {
        return 0;
    }
}
