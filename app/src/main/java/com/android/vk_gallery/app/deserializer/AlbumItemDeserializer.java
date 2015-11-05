package com.android.vk_gallery.app.deserializer;


import com.android.vk_gallery.app.modelRealm.Album;
import com.google.gson.*;

import java.lang.reflect.Type;

public class AlbumItemDeserializer implements JsonDeserializer<Album> {
    @Override
    public Album deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Album album = new Album();
        JsonArray albumItems;

        JsonObject jsonObject = json.getAsJsonObject();
        if(jsonObject.has("response")){
            albumItems = jsonObject.get("response").getAsJsonArray();
            jsonObject = albumItems.get(0).getAsJsonObject();
        }

        album.setAid(jsonObject.get("aid").getAsInt());
        album.setTitle(jsonObject.get("title").getAsString());
        album.setThumb_src(jsonObject.get("thumb_src").getAsString());
        album.setOwner_id(jsonObject.get("owner_id").getAsInt());

        return album;
    }
}
