package com.android.vk_gallery.app.modelRealm;


import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import java.util.StringTokenizer;


public class Album extends RealmObject {
    @PrimaryKey
    @SerializedName("aid")
    private int aid;
    @SerializedName("owner_id")
    private int owner_id;
    @SerializedName("title")
    private String title;
    @SerializedName("thumb_src")
    private String thumb_src;
//    @SerializedName("thumb_id")
//    int mThumb_id;
//    @SerializedName("owner_id")
//    int mOwner_id;
//    @SerializedName("description")
//    String mDescription;
//    @SerializedName("created")
//    int mCreated;
//    @SerializedName("updated")
//    int mUpdated;
//    @SerializedName("size")
//    int mSize;
//    @SerializedName("thumb_is_last")
//    int mThumb_is_last;
//    @SerializedName("privacy_view")
//    List<TypePrivacy> mPrivacy_view;
//    @SerializedName("privacy_comment")
//    List<TypePrivacy> mPrivacy_comment;

    public Album(){

    }

    public Album(int owner_id, int aid, String title, String thumb_src){
        this.owner_id = owner_id;
        this.aid = aid;
        this.title = title;
        this.thumb_src = thumb_src;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getThumb_src() {
        return thumb_src;
    }

    public void setThumb_src(String thumb_src) {
        this.thumb_src = thumb_src;
    }

//    @Override
//    public int compareTo(Album another) {
//        if(aid == another.aid && owner_id == another.owner_id && title == another.title && thumb_src == another)
//            return 0;
//        return 1;
//    }
//    public void setmThumb_src(String thumb_src) {
//        this.mThumb_src = thumb_src;
//    }
//
//    public void setmId(int id) {
//        this.mId = id;
//    }
//
//    public void setmTitle(String title) {
//        this.mTitle = title;
//    }
//
//    public int getmOwner_id() {
//        return mOwner_id;
//    }
//
//    public void setmOwner_id(int mOwner_id) {
//        this.mOwner_id = mOwner_id;
//    }
//
//    public List<TypePrivacy>  getmPrivacy_comment() {
//        return mPrivacy_comment;
//    }
//
//    public void setmPrivacy_comment(List<TypePrivacy>  mPrivacy_comment) {
//        this.mPrivacy_comment = mPrivacy_comment;
//    }
//
//    public String getmDescription() {
//        return mDescription;
//    }
//
//    public void setmDescription(String mDescription) {
//        this.mDescription = mDescription;
//    }
//
//    public int getmCreated() {
//        return mCreated;
//    }
//
//    public void setmCreated(int mCreated) {
//        this.mCreated = mCreated;
//    }
//
//    public List<TypePrivacy>  getmPrivacy_view() {
//        return mPrivacy_view;
//    }
//
//    public void setmPrivacy_view(List<TypePrivacy>  mPrivacy_view) {
//        this.mPrivacy_view = mPrivacy_view;
//    }
//
//    public int getmThumb_is_last() {
//        return mThumb_is_last;
//    }
//
//    public void setmThumb_is_last(int mThumb_is_last) {
//        this.mThumb_is_last = mThumb_is_last;
//    }
//
//    public int getmUpdated() {
//        return mUpdated;
//    }
//
//    public void setmUpdated(int mUpdated) {
//        this.mUpdated = mUpdated;
//    }
//
//    public int getmSize() {
//        return mSize;
//    }
//
//    public void setmSize(int mSize) {
//        this.mSize = mSize;
//    }
//
//    public int getmThumb_id() {
//        return mThumb_id;
//    }
//
//    public void setmThumb_id(int mThumb_id) {
//        this.mThumb_id = mThumb_id;
//    }

    //    public Album(int id, String title, String thumb_src, int thumb_id, int owner_id,
//                     String description, int created, int updated, int size, int thumb_is_last,
//                     TypePrivacy privacy_view, TypePrivacy privacy_comment) {
//        this.mId = id;
//        this.mTitle = title;
//        this.mThumb_src = thumb_src;
//        this.mThumb_id = thumb_id;
//        this.mTitle = description;
//        this.mCreated = created;
//        this.mUpdated = updated;
//        this.mSize = size;
//        this.mThumb_is_last = thumb_is_last;
//        this.mPrivacy_view = privacy_view;
//        this.mPrivacy_comment = privacy_comment;
//    }

//

}
