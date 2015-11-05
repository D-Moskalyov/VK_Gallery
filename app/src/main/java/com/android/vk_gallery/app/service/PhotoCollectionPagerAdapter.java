package com.android.vk_gallery.app.service;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.android.vk_gallery.app.fragment.FragmentSwipePhoto;


public class PhotoCollectionPagerAdapter extends FragmentStatePagerAdapter {

    public PhotoCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        Fragment fragment = new FragmentSwipePhoto();
        Bundle args = new Bundle();
        args.putInt(FragmentSwipePhoto.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
