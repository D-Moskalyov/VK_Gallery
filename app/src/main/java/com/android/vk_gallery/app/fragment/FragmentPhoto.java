package com.android.vk_gallery.app.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vk_gallery.app.activity.AlbumActivity;
import com.android.vk_gallery.app.activity.SwipePhotoActivity;
import com.android.vk_gallery.app.modelRealm.Photo;
import com.android.vk_gallery.app.modelRealm.PhotoURL;
import com.android.vk_gallery.app.service.PhotoParcelable;
import com.android.vk_gallery.app.service.PhotoURLParcelable;
import com.android.vk_gallery.app.R;
import com.android.vk_gallery.app.service.SizesForSwipe;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FragmentPhoto extends Fragment {

    Bundle bundle;
    boolean isOffline;
    ArrayList<PhotoURLParcelable> photoURLParcelables;
    int pid;

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
        pid = bundle.getInt("pid");
        isOffline = bundle.getBoolean("isOffline");

        Collections.sort(photoURLParcelables);
        Collections.reverse(photoURLParcelables);

        SimpleDraweeView simpleDraweeView =
                (SimpleDraweeView) this.getView().findViewById(R.id.image_view);
        Uri uri = Uri.parse(photoURLParcelables.get(photoURLParcelables.size() / 2).getSrc());

        if (!isOffline) {
            simpleDraweeView.setImageURI(uri);
        } else {
            ImageRequest request = ImageRequestBuilder
                    .newBuilderWithSource(uri)
                    .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.DISK_CACHE)
                    .build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setImageRequest(request)
                    .build();
            simpleDraweeView.setController(controller);
        }
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SwipePhotoActivity.class);
                boolean isOffline = ((AlbumActivity) getActivity()).getIsOffline();

                List<Photo> photos = ((AlbumActivity) getActivity()).getPhoto();

                int startPositionPhoto = -1;
                ArrayList<SizesForSwipe> sizesForSwipes = new ArrayList<SizesForSwipe>();
                for (int i = 0; i < photos.size(); i++) {
                    if (photos.get(i).getPid() == pid)
                        startPositionPhoto = i;//selected Photo
                    List<PhotoURL> photoURLs = photos.get(i).getSizes();
                    List<PhotoURLParcelable> photoURLParcelables = new ArrayList<PhotoURLParcelable>();
                    for (PhotoURL photoURL : photoURLs) {
                        photoURLParcelables.add(new PhotoURLParcelable(photoURL));
                    }
                    Collections.sort(photoURLParcelables);
                    Collections.reverse(photoURLParcelables);

                    sizesForSwipes.add(new SizesForSwipe(photoURLParcelables.get(photoURLParcelables.size() / 2).getSrc(),
                            photoURLParcelables.get(0).getSrc()));//two sizes for swipe
                }

                intent.putExtra("isOffline", isOffline);
                intent.putExtra("positionPhoto", startPositionPhoto);
                intent.putExtra(SizesForSwipe.class.getCanonicalName(), sizesForSwipes);
                startActivity(intent);
            }
        });

    }
}
