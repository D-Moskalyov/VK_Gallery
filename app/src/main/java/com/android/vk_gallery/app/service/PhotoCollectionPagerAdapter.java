package com.android.vk_gallery.app.service;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.android.vk_gallery.app.activity.SwipePhotoActivity;
import com.android.vk_gallery.app.fragment.FragmentSwipePhoto;

import java.util.ArrayList;


public class PhotoCollectionPagerAdapter extends FragmentStatePagerAdapter {
    int startPos;
    //boolean isOffline;
    ArrayList<SizesForSwipe> sizesForSwipes;

    public PhotoCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
        sizesForSwipes = SwipePhotoActivity.getSizes();
        startPos = SwipePhotoActivity.getStartPos();
        //isOffline = SwipePhotoActivity.isOffline();
        //getItem(startPos);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment = new FragmentSwipePhoto();
        Bundle args = new Bundle();
        SizesForSwipe sizesForSwipe = sizesForSwipes.get(position);
        args.putParcelable(FragmentSwipePhoto.ARG_OBJECT, sizesForSwipe);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getCount() {
        return sizesForSwipes.size();
    }

    //    @Override
//    public CharSequence getPageTitle(int position) {
//        return "OBJECT " + (sizesForSwipes.get(position).getMediumSize());
//    }
}
