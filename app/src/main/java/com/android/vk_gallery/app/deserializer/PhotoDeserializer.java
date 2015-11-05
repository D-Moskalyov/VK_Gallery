package com.android.vk_gallery.app.deserializer;


import com.android.vk_gallery.app.modelRealm.Photo;
import com.android.vk_gallery.app.modelRealm.PhotoURL;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PhotoDeserializer implements JsonDeserializer<Photo> {
    @Override
    public Photo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Photo photo = new Photo();
        JsonArray photoItem;

        JsonObject jsonObject = json.getAsJsonObject();
        if(jsonObject.has("response")){
            photoItem = jsonObject.get("response").getAsJsonArray();
            jsonObject = photoItem.get(0).getAsJsonObject();
        }

        photo.setAid(jsonObject.get("aid").getAsInt());
        photo.setOwner_id(jsonObject.get("owner_id").getAsInt());
        photo.setPid(jsonObject.get("pid").getAsInt());

        JsonArray sizesArray = jsonObject.get("sizes").getAsJsonArray();
        for(JsonElement sizeItem : sizesArray) {
            photo.getSizes().add((PhotoURL) context.deserialize(sizeItem, PhotoURL.class));
        }

        return photo;
    }
}
