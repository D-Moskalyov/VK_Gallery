package com.android.vk_gallery.app;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;

public class FragmentCover extends Fragment  {

    Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        bundle = getArguments();
        return inflater.inflate(R.layout.cover_fragment_layout, null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Uri uri = Uri.parse(bundle.getString("Thumb_src"));
        SimpleDraweeView simpleDraweeView =
                (SimpleDraweeView) this.getView().findViewById(R.id.my_image_view);
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) v;
                FrameLayout frameLayout = (FrameLayout) simpleDraweeView.getParent();
                TextView textView = (TextView) frameLayout.findViewById(R.id.titleCover);
                int id = simpleDraweeView.getId();
                Intent intent = new Intent(getActivity(), AlbumActivity.class);
                intent.putExtra("title", textView.getText().toString());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        int id = bundle.getInt("id");
        simpleDraweeView.setId(id);
        //simpleDraweeView.setLayoutParams(new LinearLayout.LayoutParams(700, 700));
        simpleDraweeView.setImageURI(uri);
        String titleCover = bundle.getString("Title");
        ((TextView) this.getView().findViewById(R.id.titleCover)).setText(titleCover);
        this.getView().findViewById(R.id.check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int t = 0;
            }
        });
    }

//    public void onCheckBoxClick(View v) {
//        int t = 4;
//    }
//
//    public void onSimpleDraweeViewClick(View view){
//        int t = 0;
//    }
}
