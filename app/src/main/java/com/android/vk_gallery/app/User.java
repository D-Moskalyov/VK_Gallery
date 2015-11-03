package com.android.vk_gallery.app;


import com.google.gson.annotations.SerializedName;


public class User {
    @SerializedName("id")
    int mId;
    @SerializedName("first_name")
    String mFirst_name;
    @SerializedName("last_name")
    String mLast_name;

    public User(){

    }

    public String getmFirst_name() {
        return mFirst_name;
    }

    public void setmFirst_name(String mFirst_name) {
        this.mFirst_name = mFirst_name;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmLast_name() {
        return mLast_name;
    }

    public void setmLast_name(String mLast_name) {
        this.mLast_name = mLast_name;
    }
}
