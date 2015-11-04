package com.android.vk_gallery.app;


import com.google.gson.annotations.SerializedName;

public class PhotoURL {
    @SerializedName("src")
    String src;
    @SerializedName("width")
    int width;
    @SerializedName("height")
    int height;
    @SerializedName("type")
    String type;

    public PhotoURL() {
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
}
