package com.android.vk_gallery.app.deserializer;

import com.android.vk_gallery.app.model.AlbumItem;
import com.android.vk_gallery.app.model.CollectionAlbums;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CollectionAlbumsDeserializer implements JsonDeserializer<CollectionAlbums> {
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
