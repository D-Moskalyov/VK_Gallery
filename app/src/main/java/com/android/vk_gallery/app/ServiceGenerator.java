package com.android.vk_gallery.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ServiceGenerator {

    public static final String API_BASE_URL = "https://api.vk.com/method/";

    static Gson gson = new GsonBuilder()
            .registerTypeAdapter(User.class, new UserDeserializer())
            .registerTypeAdapter(AlbumItem.class, new AlbumItemDeserializer())
            .registerTypeAdapter(CollectionAlbums.class, new CollectionAlbumsDeserialiser())
            .registerTypeAdapter(Photo.class, new PhotoDeserializer())
            .registerTypeAdapter(PhotoURL.class, new PhotoURLDeserializer())
            .registerTypeAdapter(CollectionPhotos.class, new CollectionPhotosDeserializer()).create();
//    Type collectionType = new TypeToken<List<AlbumItem>>(){}.getType();
//    List<AlbumItem> enums = gson.fromJson(yourJson, collectionType);
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
