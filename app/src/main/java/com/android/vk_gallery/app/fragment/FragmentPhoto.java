package com.android.vk_gallery.app.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vk_gallery.app.service.PhotoURLParcelable;
import com.android.vk_gallery.app.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class FragmentPhoto extends Fragment {

    Bundle bundle;
    ArrayList<PhotoURLParcelable> photoURLParcelables;
    boolean isOffline;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bundle = getArguments();
        return inflater.inflate(R.layout.photo_fragment_layout, null);

    }

    @Override
    public void onResume() {
        super.onResume();
        photoURLParcelables = bundle.getParcelableArrayList("sizes");
        isOffline = bundle.getBoolean("isOffline");
        Uri uri = Uri.parse(photoURLParcelables.get(0).getSrc());
//        for(PhotoURL photoURL : photoURLs){
            SimpleDraweeView simpleDraweeView =
                    (SimpleDraweeView) this.getView().findViewById(R.id.image_view);
            simpleDraweeView.setImageURI(uri);
            simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    photoURLParcelables = null;
                    //start swipe
                }
            });

        //}

    }
}
