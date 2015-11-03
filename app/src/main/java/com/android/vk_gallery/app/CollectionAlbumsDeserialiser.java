package com.android.vk_gallery.app;

import com.google.gson.*;

import java.lang.reflect.Type;

public class CollectionAlbumsDeserialiser implements JsonDeserializer<CollectionAlbums> {
    @Override
    public CollectionAlbums deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        CollectionAlbums collectionAlbums = new CollectionAlbums();

        JsonObject jsonObject = json.getAsJsonObject();
        JsonArray albumItems = jsonObject.get("response").getAsJsonArray();

        for(JsonElement albumItem : albumItems) {
            collectionAlbums.getAlbumItems().add((AlbumItem) context.deserialize(albumItem, AlbumItem.class));
        }

        return collectionAlbums;
    }
}
