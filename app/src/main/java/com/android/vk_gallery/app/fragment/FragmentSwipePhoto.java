package com.android.vk_gallery.app.fragment;


import android.graphics.PointF;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.vk_gallery.app.R;
import com.android.vk_gallery.app.activity.SwipePhotoActivity;
import com.android.vk_gallery.app.service.SizesForSwipe;
import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import io.realm.Realm;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

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
        simpleDraweeView
                .getHierarchy()
                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);

        if (!SwipePhotoActivity.isOffline()) {
            simpleDraweeView.setImageURI(uriBig);
        }
        else{
            ImageRequest request = ImageRequestBuilder
                    .newBuilderWithSource(uriBig)
                    .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.DISK_CACHE)
                    .build();

            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .build();

            simpleDraweeView.setController(controller);
        }


        simpleDraweeView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!SwipePhotoActivity.isOffline()) {

                    if (isExternalStorageWritable()) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                File dir = getAlbumStorageDir("VKPhoto");

                                String sourceFilename = uriBig.toString();
                                String fileName = uriBig.getLastPathSegment();
                                String destinationFilename = dir.getPath() + File.separator + fileName;

                                BufferedInputStream bis = null;
                                BufferedOutputStream bos = null;

                                try {
                                    URL url = new URL(sourceFilename);
                                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                                    InputStream inputStream = urlConnection.getInputStream();
                                    bis = new BufferedInputStream(inputStream);
                                    bos = new BufferedOutputStream(new FileOutputStream(destinationFilename, false));
                                    byte[] buf = new byte[1024];
                                    int length = bis.read(buf);
                                    do {
                                        bos.write(buf, 0, length);
                                    } while ((length = bis.read(buf)) != -1);
                                } catch (IOException e) {

                                } finally {
                                    try {
                                        if (bis != null) bis.close();
                                        if (bos != null) bos.close();
                                    } catch (IOException e) {

                                    }
                                }
                            }
                        }).start();

                    }
                    Toast.makeText(getActivity().getApplicationContext(), "Download on sdCard", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "offline Mode", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        return rootView;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public File getAlbumStorageDir(String albumName) {
        File dir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!dir.mkdirs()) {

        }
        return dir;
    }

}
