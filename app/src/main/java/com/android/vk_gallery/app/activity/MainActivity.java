package com.android.vk_gallery.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.android.vk_gallery.app.fragment.FragmentCover;
import com.android.vk_gallery.app.MyApplication;
import com.android.vk_gallery.app.R;
import com.android.vk_gallery.app.modelRealm.Album;
import com.android.vk_gallery.app.service.VKClient;
import com.android.vk_gallery.app.model.CollectionAlbums;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.*;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    //protected MyApplication app;
    private static String[] sMyScope = new String[]{VKScope.PHOTOS};
    RealmResults<Album> result;
    Realm realm;
    int ID;
    VKClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        InitializeUser();
        //VKSdk.login(this, sMyScope);
        realm = Realm.getDefaultInstance();
        client = ((MyApplication)getApplicationContext()).getVKClient();

        RealmQuery<Album> query = realm.where(Album.class);
        result = query.findAll();

        Call<CollectionAlbums> call = client.getAlbums(1, 6129318);

        call.enqueue(new Callback<CollectionAlbums>() {
            @Override
            public void onResponse(Response<CollectionAlbums> response, Retrofit retrofit) {
                UpdateRealmAlbums(response.body().getAlbums());
                CreateFragments(response.body().getAlbums());
            }

            @Override
            public void onFailure(Throwable t) {
                CreateFragments(result);
            }

        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    @Override
//    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
//        InitializeUser();
//
//        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
//            @Override
//            public void onResult(VKAccessToken res) {
//
//                VKClient client = ((MyApplication)getApplicationContext()).getVKClient();
//
//                RealmQuery<Album> query = realm.where(Album.class);
//                result = query.findAll();
//
//                Call<CollectionAlbums> call = client.getAlbums(1, 6129318);
//
//                call.enqueue(new Callback<CollectionAlbums>() {
//                    @Override
//                    public void onResponse(Response<CollectionAlbums> response, Retrofit retrofit) {
//                        UpdateRealmAlbums(response.body().getAlbums());
//                        CreateFragments(response.body().getAlbums());
//                    }
//                    @Override
//                    public void onFailure(Throwable t) {
//                        CreateFragments(result);
//                    }
//
//                });
//            }
//
//            @Override
//            public void onError(VKError error) {
//                int y = 4;
//            }
//        })) {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }

    private void InitializeUser() {
        VKRequest request = new VKRequest("users.get");
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                String jSONString = response.json.toString();
                int start = jSONString.indexOf("\"id\":") + 5;
                int fin = jSONString.indexOf(",", start);
                String idStr = jSONString.substring(start, fin);
                ID = Integer.parseInt(idStr);
            }

            @Override
            public void onError(VKError error) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                InitializeUser();
            }
        });
    }

    void UpdateRealmAlbums(ArrayList<Album> albums){

        realm.beginTransaction();
        for(int i = 0; i < result.size(); i++){
            boolean contains = false;
            Album album = result.get(i);

            for (Album a : albums) {
                if(album.getAid() == a.getAid()){
                    contains = true;
                    break;
                }
            }
            if(!contains) {
                album.removeFromRealm();//does not existed: removeFromRealm
                i--;
            }
        }
        realm.commitTransaction();

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(albums);
        realm.commitTransaction();
    }

    void CreateFragments(List<Album> albums){
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        for(Album album : albums){

            FragmentCover fragmentCover = new FragmentCover();

            Bundle bundle = new Bundle();
            bundle.putInt("id", album.getAid());
            bundle.putString("Title", album.getTitle());
            bundle.putString("Thumb_src", album.getThumb_src());

            fragmentCover.setArguments(bundle);
            ft.add(R.id.main_layout, fragmentCover);
        }
        ft.commit();
    }
}
