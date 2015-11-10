package com.android.vk_gallery.app.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.android.vk_gallery.app.R;
import com.android.vk_gallery.app.service.PhotoCollectionPagerAdapter;
import com.android.vk_gallery.app.service.SizesForSwipe;

import java.util.ArrayList;

public class SwipePhotoActivity extends FragmentActivity{
    PhotoCollectionPagerAdapter pCollPagerAdapter;
    ViewPager viewPager;
    public static boolean isOffline;
    static int startPos;
    static ArrayList<SizesForSwipe> sizes;

    static public int getStartPos() {
        return startPos;
    }

    public static ArrayList<SizesForSwipe> getSizes() {
        return sizes;
    }

    public static boolean isOffline() {
        return isOffline;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_photo_layout);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        startPos = bundle.getInt("positionPhoto");
        isOffline = bundle.getBoolean("isOffline");
        sizes = bundle.getParcelableArrayList(SizesForSwipe.class.getCanonicalName());

        pCollPagerAdapter = new PhotoCollectionPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pCollPagerAdapter);

        viewPager.setCurrentItem(startPos);
    }
}
