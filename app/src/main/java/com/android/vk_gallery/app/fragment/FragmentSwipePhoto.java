package com.android.vk_gallery.app.fragment;


import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.vk_gallery.app.R;
import com.android.vk_gallery.app.service.SizesForSwipe;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.*;

public class FragmentSwipePhoto extends Fragment{

    public static final String ARG_OBJECT = "object";
    Uri uriBig;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.swipe_photo_fragment_layout, container, false);
        Bundle args = getArguments();
        SizesForSwipe sizesForSwipe = args.getParcelable(ARG_OBJECT);
        uriBig = Uri.parse(sizesForSwipe.getBigSize());
        Uri medium = Uri.parse(sizesForSwipe.getMediumSize());
        SimpleDraweeView simpleDraweeView = ((SimpleDraweeView) rootView.findViewById(R.id.image_view));
        simpleDraweeView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

//                String sourceFilename= uriBig.getPath();
//                String destinationFilename = android.os.Environment.getExternalStorageDirectory().getPath() +
//                        File.separatorChar+"abc.jpg";
//
//                BufferedInputStream bis = null;
//                BufferedOutputStream bos = null;
//
//                try {
//                    bis = new BufferedInputStream(new FileInputStream(sourceFilename));
//                    bos = new BufferedOutputStream(new FileOutputStream(destinationFilename, false));
//                    byte[] buf = new byte[1024];
//                    bis.read(buf);
//                    do {
//                        bos.write(buf);
//                    } while(bis.read(buf) != -1);
//                } catch (IOException e) {
//
//                } finally {
//                    try {
//                        if (bis != null) bis.close();
//                        if (bos != null) bos.close();
//                    } catch (IOException e) {
//
//                    }
//                }
                return false;
            }
        });
        simpleDraweeView.setImageURI(medium);
        return rootView;
    }
}
