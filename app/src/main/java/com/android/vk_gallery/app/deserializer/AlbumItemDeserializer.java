package com.android.vk_gallery.app.deserializer;


import com.android.vk_gallery.app.model.AlbumItem;
import com.google.gson.*;

import java.lang.reflect.Type;

public class AlbumItemDeserializer implements JsonDeserializer<AlbumItem> {
    @Override
    public AlbumItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        AlbumItem albumItem = new AlbumItem();
        JsonArray albumItems;

        JsonObject jsonObject = json.getAsJsonObject();
        if(jsonObject.has("response")){
            albumItems = jsonObject.get("response").getAsJsonArray();
            jsonObject = albumItems.get(0).getAsJsonObject();
        }

        albumItem.setmId(jsonObject.get("aid").getAsInt());
        albumItem.setmTitle(jsonObject.get("title").getAsString());
        albumItem.setmThumb_src(jsonObject.get("thumb_src").getAsString());

        return albumItem;
    }
}
