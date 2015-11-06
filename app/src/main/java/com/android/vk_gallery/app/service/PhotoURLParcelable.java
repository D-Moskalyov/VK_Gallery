package com.android.vk_gallery.app.service;


import android.os.Parcel;
import android.os.Parcelable;
import com.android.vk_gallery.app.modelRealm.PhotoURL;

public class PhotoURLParcelable implements Parcelable, Comparable<PhotoURLParcelable> {

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
        dest.writeString(src);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(type);
    }

    private PhotoURLParcelable(Parcel parcel) {
        src = parcel.readString();
        width = parcel.readInt();
        height = parcel.readInt();
        type = parcel.readString();
    }

    public static final Parcelable.Creator<PhotoURLParcelable> CREATOR = new Parcelable.Creator<PhotoURLParcelable>() {
        public PhotoURLParcelable createFromParcel(Parcel in) {
            return new PhotoURLParcelable(in);
        }

        public PhotoURLParcelable[] newArray(int size) {
            return new PhotoURLParcelable[size];
        }
    };

    @Override
    public int compareTo(PhotoURLParcelable another) {
        if(this.height > another.height)
            return 1;
        if(this.height < another.height)
            return -1;
        return 0;
    }
}
