package com.android.vk_gallery.app;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface VKClient {
    @GET("photos.getAlbums")
    Call<CollectionAlbums> getAlbums(@Query("need_covers") int need_covers, @Query("owner_id") int owner_id);

    @GET("users.get")
    Call<User> getUser(@Query("user_ids") int user_ids);
}
