package com.android.vk_gallery.app.service;


import android.os.Parcel;
import android.os.Parcelable;

public class SizesForSwipe implements Parcelable {
    String mediumSizeSrc;
    String bigSizeSrc;

    public  SizesForSwipe(String mediumSizeSrc, String bigSizeSrc){
        this.mediumSizeSrc = mediumSizeSrc;
        this.bigSizeSrc = bigSizeSrc;
    }

    public String getBigSize() {
        return bigSizeSrc;
    }

    public void setBigSize(String bigSize) {
        this.bigSizeSrc = bigSize;
    }

    public String getMediumSize() {
        return mediumSizeSrc;
    }

    public void setMediumSize(String mediumSize) {
        this.mediumSizeSrc = mediumSize;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mediumSizeSrc);
        parcel.writeString(bigSizeSrc);
    }

    public static final Parcelable.Creator<SizesForSwipe> CREATOR = new Parcelable.Creator<SizesForSwipe>() {
        public SizesForSwipe createFromParcel(Parcel in) {
            return new SizesForSwipe(in);
        }

        public SizesForSwipe[] newArray(int size) {
            return new SizesForSwipe[size];
        }
    };

    private SizesForSwipe(Parcel parcel) {
        mediumSizeSrc = parcel.readString();
        bigSizeSrc = parcel.readString();
    }

}
