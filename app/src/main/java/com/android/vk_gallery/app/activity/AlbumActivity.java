package com.android.vk_gallery.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import com.android.vk_gallery.app.MyApplication;
import com.android.vk_gallery.app.modelRealm.Album;
import com.android.vk_gallery.app.service.PhotoURLParcelable;
import com.android.vk_gallery.app.R;
import com.android.vk_gallery.app.fragment.FragmentPhoto;
import com.android.vk_gallery.app.model.*;
import com.android.vk_gallery.app.modelRealm.Photo;
import com.android.vk_gallery.app.modelRealm.PhotoURL;
import com.android.vk_gallery.app.service.VKClient;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import java.util.ArrayList;
import java.util.List;

public class AlbumActivity extends FragmentActivity {

    Realm realm;
    RealmResults<Photo> result;
    boolean isOffline;
    int albumID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_layout);

        realm = Realm.getDefaultInstance();

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        this.setTitle(bundle.getString("title"));
        albumID = bundle.getInt("id");
        isOffline = bundle.getBoolean("isOffline");

        VKClient vkClient = ((MyApplication) getApplicationContext()).getVKClient();

        RealmQuery<Photo> query = realm.where(Photo.class).equalTo("aid", albumID);
        result = query.findAll();

        if(!isOffline){
            Call<CollectionPhotos> call = vkClient.getPhotos(albumID, MainActivity.getID(), 1);
            //Call<CollectionPhotos> call = vkClient.getPhotos(albumID, 6129318, 1);

            call.enqueue(new Callback<CollectionPhotos>() {
                @Override
                public void onResponse(Response<CollectionPhotos> response, Retrofit retrofit) {
                    UpdateRealmPhoto(response.body().getPhotos());
                    CreateFragments(response.body().getPhotos());
                }

                @Override
                public void onFailure(Throwable t) {
                    CreateFragments(result);
                }

            });
        }
        else{
            CreateFragments(result);
        }
    }

    void UpdateRealmPhoto(ArrayList<Photo> photos){
        realm.beginTransaction();
        for(int i = 0; i < result.size(); i++){
            boolean contains = false;
            Photo photo = result.get(i);

            for (Photo p : photos) {
                if(photo.getPid() == p.getPid()){
                    contains = true;
                    break;
                }
            }
            if(!contains) {
                photo.removeFromRealm();//does not existed: removeFromRealm
                i--;
            }
        }
        realm.commitTransaction();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(photos);
        realm.commitTransaction();

        RealmQuery<Photo> query = realm.where(Photo.class).equalTo("aid", albumID);//again
        result = query.findAll();
    }

    void CreateFragments(List<Photo> photos){

        LinearLayout photo_lt = (LinearLayout) findViewById(R.id.photo_lt);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        LinearLayout layout = new LinearLayout(this);

        int i = 0;
        int j;
        if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE)
            j = 6;
        else
            j = 3;

        for(Photo photo : photos) {
            if (i % j == 0) {
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layout.setLayoutParams(layoutParams);
                layout.setId(View.generateViewId());

                photo_lt.addView(layout);
            }

            FragmentPhoto fragmentPhoto = new FragmentPhoto();

            Bundle bundle = new Bundle();
            ArrayList<PhotoURLParcelable> photoURLParcelables = new  ArrayList<PhotoURLParcelable>();
            for(PhotoURL photoURL : photo.getSizes()){
                photoURLParcelables.add(new PhotoURLParcelable(photoURL));
            }
            bundle.putParcelableArrayList("sizes", photoURLParcelables);
            bundle.putBoolean("isOffline", isOffline);
            bundle.putInt("pid", photo.getPid());

            fragmentPhoto.setArguments(bundle);
            ft.add(layout.getId(), fragmentPhoto);

            i++;
        }
        ft.commit();
    }

    public List<Photo> getPhoto(){
        return result;
    }

    public boolean getIsOffline(){
        return isOffline;
    }
}
