package com.android.vk_gallery.app.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vk_gallery.app.R;

public class FragmentSwipePhoto extends Fragment{

    public static final String ARG_OBJECT = "object";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.swipe_photo_fragment_layout, container, false);
        Bundle args = getArguments();
        ((TextView) rootView.findViewById(R.id.text)).setText(
                Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }
}
