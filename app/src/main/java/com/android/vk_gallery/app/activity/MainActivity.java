package com.android.vk_gallery.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.android.vk_gallery.app.fragment.FragmentCover;
import com.android.vk_gallery.app.MyApplication;
import com.android.vk_gallery.app.R;
import com.android.vk_gallery.app.service.VKClient;
import com.android.vk_gallery.app.model.AlbumItem;
import com.android.vk_gallery.app.model.CollectionAlbums;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.*;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends FragmentActivity {

    //protected MyApplication app;
    private static String[] sMyScope = new String[]{VKScope.PHOTOS};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        //VKSdk.initialize(getApplicationContext());
        //app = (MyApplication)getApplication();
        //app.customAppMethod();

        VKSdk.login(this, sMyScope);
        //VKSdk.login(Fragment runningFragment, String... scope);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {

//                VKParameters params = new VKParameters();
//                params.put("need_covers", 1);
//                VKRequest request = new VKRequest("photos.getAlbums", params);
//                request.executeWithListener(new VKRequest.VKRequestListener() {
//                    @Override
//                    public void onComplete(VKResponse response) {
//                        int r = 1;
//                        //Do complete stuff
//                    }
//
//                    @Override
//                    public void onError(VKError error) {
//                        int r = 1;
//                        //Do error stuff
//                    }
//
//                    @Override
//                    public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
//                        int r = 1;
//                        //I don't really believe in progress
//                    }
//                });


//                VKRequest request = new VKRequest("users.get", null);
//                request.executeWithListener(new VKRequest.VKRequestListener() {
//                    @Override
//                    public void onComplete(VKResponse response) {
//                        int r = 1;
//                        //Do complete stuff
//                    }
//
//                    @Override
//                    public void onError(VKError error) {
//                        int r = 1;
//                        //Do error stuff
//                    }
//
//                    @Override
//                    public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
//                        int r = 1;
//                        //I don't really believe in progress
//                    }
//                });


                //VKClient client = ServiceGenerator.createService(VKClient.class);
                VKClient client = ((MyApplication)getApplicationContext()).getVKClient();
//                Call<User> call = client.getUser(20646473);
//
//                call.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Response<User> response, Retrofit retrofit) {
//                        int d = 4;
//                    }
//
//                    @Override
//                    public void onFailure(Throwable t) {
//                        int d = 4;
//                    }
//
//                });


                Call<CollectionAlbums> call = client.getAlbums(1, 20646473);

                call.enqueue(new Callback<CollectionAlbums>() {
                    @Override
                    public void onResponse(Response<CollectionAlbums> response, Retrofit retrofit) {
                        //LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_layout);
                        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

                        for(AlbumItem albumItem : response.body().getAlbumItems()){
                            FragmentCover fragmentCover = new FragmentCover();

                            Bundle bundle = new Bundle();
                            bundle.putInt("id", albumItem.getmId());
                            bundle.putString("Title", albumItem.getmTitle());
                            bundle.putString("Thumb_src", albumItem.getmThumb_src());

                            fragmentCover.setArguments(bundle);
                            ft.add(R.id.main_layout, fragmentCover);

//                            Uri uri = Uri.parse(albumItem.getmThumb_src());
//                            SimpleDraweeView simpleDraweeView =
//                                    (SimpleDraweeView) fragmentCover.getView().findViewById(R.id.my_image_view);
//                            //simpleDraweeView.setLayoutParams(new LinearLayout.LayoutParams(700, 700));
//                            simpleDraweeView.setImageURI(uri);
//                            String titleCover = albumItem.getmTitle();
//                            ((TextView) fragmentCover.getView().findViewById(R.id.titleCover)).setText(titleCover);
//                            simpleDraweeView.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    Toast.makeText(getApplicationContext(), "onClick", Toast.LENGTH_SHORT).show();
//                                }
//                            });

                        }

                        ft.commit();
//                        String address = response.body().getAlbumItems().get(0).getmThumb_src();
//                        Uri uri = Uri.parse(address);
//                        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
//                        draweeView.setImageURI(uri);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        int d = 4;
                    }

                });
            }

            @Override
            public void onError(VKError error) {
                //
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
