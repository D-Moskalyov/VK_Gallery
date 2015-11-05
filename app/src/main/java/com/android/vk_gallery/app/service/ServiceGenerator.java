package com.android.vk_gallery.app.service;

import com.android.vk_gallery.app.deserializer.*;
import com.android.vk_gallery.app.model.*;
import com.android.vk_gallery.app.modelRealm.Album;
import com.android.vk_gallery.app.modelRealm.Photo;
import com.android.vk_gallery.app.modelRealm.PhotoURL;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import io.realm.RealmObject;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ServiceGenerator {

    public static final String API_BASE_URL = "https://api.vk.com/method/";

    static Gson gson = new GsonBuilder()
            .registerTypeAdapter(User.class, new UserDeserializer())
            .registerTypeAdapter(Album.class, new AlbumItemDeserializer())
            .registerTypeAdapter(CollectionAlbums.class, new CollectionAlbumsDeserializer())
            .registerTypeAdapter(Photo.class, new PhotoDeserializer())
            .registerTypeAdapter(PhotoURL.class, new PhotoURLDeserializer())
            .registerTypeAdapter(CollectionPhotos.class, new CollectionPhotosDeserializer())
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            }).create();
//    Type collectionType = new TypeToken<List<Album>>(){}.getType();
//    List<Album> enums = gson.fromJson(yourJson, collectionType);
    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
