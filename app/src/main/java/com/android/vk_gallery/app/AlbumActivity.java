package com.android.vk_gallery.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class AlbumActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        this.setTitle(bundle.getString("title"));
        int albumID = bundle.getInt("id");
        VKClient vkClient = ((MyApplication) getApplicationContext()).getVKClient();


    }
}
