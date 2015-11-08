package com.android.vk_gallery.app.activity;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.android.vk_gallery.app.MyApplication;
import com.android.vk_gallery.app.R;
import com.android.vk_gallery.app.modelRealm.Photo;
import com.android.vk_gallery.app.service.PhotoCollectionPagerAdapter;
import com.android.vk_gallery.app.service.PhotoParcelable;
import com.android.vk_gallery.app.service.SizesForSwipe;
import com.android.vk_gallery.app.service.VKClient;
import io.realm.Realm;

import java.util.ArrayList;
import java.util.List;

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

        VKClient vkClient = ((MyApplication) getApplicationContext()).getVKClient();

        pCollPagerAdapter = new PhotoCollectionPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pCollPagerAdapter);

        viewPager.setCurrentItem(startPos);


//        viewPager.setOnPageChangeListener(
//                new ViewPager.SimpleOnPageChangeListener() {
//                    @Override
//                    public void onPageSelected(int position) {
//                        getActionBar().setSelectedNavigationItem(position);
//                    }
//                });
//
//        final ActionBar actionBar = getActionBar();
//        // Specify that tabs should be displayed in the action bar.
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//
//        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
//            @Override
//            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
//                int t = 0;
//            }
//
//            @Override
//            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
//                int t = 0;
//            }
//        };
//
//        for (int i = 0; i < 3; i++) {
//            actionBar.addTab(
//                    actionBar.newTab()
//                            .setText("Tab " + (i + 1))
//                            .setTabListener(tabListener));
//        }
    }
}
