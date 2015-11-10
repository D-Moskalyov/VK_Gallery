package com.android.vk_gallery.app.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import com.android.vk_gallery.app.fragment.FragmentCover;
import com.android.vk_gallery.app.MyApplication;
import com.android.vk_gallery.app.R;
import com.android.vk_gallery.app.modelRealm.Album;
import com.android.vk_gallery.app.modelRealm.Photo;
import com.android.vk_gallery.app.modelRealm.PhotoURL;
import com.android.vk_gallery.app.service.VKClient;
import com.android.vk_gallery.app.model.CollectionAlbums;
import com.vk.sdk.VKScope;
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

    RealmResults<Album> result;
    Realm realm;

    public static int getID() {
        return ID;
    }

    static int ID;
    VKClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        InitializeUser();
        realm = Realm.getDefaultInstance();

        client = ((MyApplication)getApplicationContext()).getVKClient();

        RealmQuery<Album> query = realm.where(Album.class);
        result = query.findAll();

        //Call<CollectionAlbums> call = client.getAlbums(1, 6129318);
        Call<CollectionAlbums> call = client.getAlbums(1, getID());

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

    private void InitializeUser() {
        VKRequest request = new VKRequest("users.get");
        request.executeSyncWithListener(new VKRequest.VKRequestListener() {
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

    void CreateFragments(List<Album> albums) {

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        LinearLayout layout = new LinearLayout(this);

        int i = 0;
        int j;
        if (getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE)
            j = 4;
        else
            j = 2;

        for (Album album : albums) {
            if (i % j == 0) {
                layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.HORIZONTAL);
                layout.setLayoutParams(layoutParams);
                layout.setId(View.generateViewId());

                mainLayout.addView(layout);
            }

            FragmentCover fragmentCover = new FragmentCover();

            Bundle bundle = new Bundle();
            bundle.putInt("id", album.getAid());
            bundle.putString("Title", album.getTitle());
            bundle.putString("Thumb_src", album.getThumb_src());

            fragmentCover.setArguments(bundle);
            ft.add(layout.getId(), fragmentCover);

            i++;
        }
        ft.commit();
    }
}
