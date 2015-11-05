package com.android.vk_gallery.app.deserializer;


import com.android.vk_gallery.app.model.CollectionPhotos;
import com.android.vk_gallery.app.modelRealm.Photo;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CollectionPhotosDeserializer implements JsonDeserializer<CollectionPhotos> {
    @Override
    public CollectionPhotos deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        CollectionPhotos collectionPhotos = new CollectionPhotos();

        JsonObject jsonObject = json.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("response").getAsJsonArray();

        for(JsonElement photo : jsonArray) {
            collectionPhotos.getPhotos().add((Photo) context.deserialize(photo, Photo.class));
        }

        return collectionPhotos;
    }
}
