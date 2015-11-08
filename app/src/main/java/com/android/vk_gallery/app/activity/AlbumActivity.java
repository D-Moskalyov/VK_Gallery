package com.android.vk_gallery.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.android.vk_gallery.app.MyApplication;
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

        //realm = ((MyApplication)getApplicationContext()).getRealm();
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
            Call<CollectionPhotos> call = vkClient.getPhotos(albumID, 6129318, 1);

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
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        for(Photo photo : photos) {

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
            ft.add(R.id.photo_lt, fragmentPhoto);
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
