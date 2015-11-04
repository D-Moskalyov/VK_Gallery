package com.android.vk_gallery.app.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.android.vk_gallery.app.MyApplication;
import com.android.vk_gallery.app.R;
import com.android.vk_gallery.app.fragment.FragmentCover;
import com.android.vk_gallery.app.fragment.FragmentPhoto;
import com.android.vk_gallery.app.model.AlbumItem;
import com.android.vk_gallery.app.model.CollectionAlbums;
import com.android.vk_gallery.app.model.CollectionPhotos;
import com.android.vk_gallery.app.model.Photo;
import com.android.vk_gallery.app.service.VKClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AlbumActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_layout);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        this.setTitle(bundle.getString("title"));
        int albumID = bundle.getInt("id");
        VKClient vkClient = ((MyApplication) getApplicationContext()).getVKClient();

        Call<CollectionPhotos> call = vkClient.getPhotos(albumID, 20646473, 1);

        call.enqueue(new Callback<CollectionPhotos>() {
            @Override
            public void onResponse(Response<CollectionPhotos> response, Retrofit retrofit) {
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                for(Photo photo : response.body().getPhotoItems()) {
                    FragmentPhoto fragmentPhoto = new FragmentPhoto();

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("sizes", photo.getSizes());

                    fragmentPhoto.setArguments(bundle);
                    ft.add(R.id.photo_lt, fragmentPhoto);
                }

                ft.commit();
            }

            @Override
            public void onFailure(Throwable t) {
                int y = 0;
            }

        });
    }
}
