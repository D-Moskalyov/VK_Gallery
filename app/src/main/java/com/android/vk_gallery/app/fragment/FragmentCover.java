package com.android.vk_gallery.app.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.vk_gallery.app.R;
import com.android.vk_gallery.app.activity.AlbumActivity;
import com.facebook.drawee.view.SimpleDraweeView;

public class FragmentCover extends Fragment  {

    Bundle bundle;
    boolean isOffline;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isOffline = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bundle = getArguments();
        return inflater.inflate(R.layout.cover_fragment_layout, null);
    }


    @Override
    public void onResume() {
        super.onResume();
        Uri uri = Uri.parse(bundle.getString("Thumb_src"));
        SimpleDraweeView simpleDraweeView =
                (SimpleDraweeView) this.getView().findViewById(R.id.my_image_view);

        id = bundle.getInt("id");
        simpleDraweeView.setImageURI(uri);
        String titleCover = bundle.getString("Title");
        ((TextView) this.getView().findViewById(R.id.titleCover)).setText(titleCover);

        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) v;
                FrameLayout frameLayout = (FrameLayout) simpleDraweeView.getParent();
                TextView textView = (TextView) frameLayout.findViewById(R.id.titleCover);

                Intent intent = new Intent(getActivity(), AlbumActivity.class);
                intent.putExtra("title", textView.getText().toString());
                intent.putExtra("id", id);
                intent.putExtra("isOffline", isOffline);

                startActivity(intent);
            }
        });

        this.getView().findViewById(R.id.check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked())
                    isOffline = true;
                else
                    isOffline = false;
            }
        });
    }

}
