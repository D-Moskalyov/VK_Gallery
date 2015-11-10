package com.android.vk_gallery.app.service;

import com.android.vk_gallery.app.model.CollectionAlbums;
import com.android.vk_gallery.app.model.CollectionPhotos;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface VKClient {
    @GET("photos.getAlbums")
    Call<CollectionAlbums> getAlbums(@Query("need_covers") int need_covers, @Query("owner_id") int owner_id);

    @GET("photos.get")
    Call<CollectionPhotos> getPhotos(@Query("album_id") int album_id, @Query("owner_id") int owner_id, @Query("photo_sizes") int photo_sizes);
}
