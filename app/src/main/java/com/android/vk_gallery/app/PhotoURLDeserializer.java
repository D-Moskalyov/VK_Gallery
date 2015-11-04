package com.android.vk_gallery.app;

import com.google.gson.*;

import java.lang.reflect.Type;

public class PhotoURLDeserializer implements JsonDeserializer<PhotoURL> {
    @Override
    public PhotoURL deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        PhotoURL photoURL = new PhotoURL();

        JsonObject jsonObject = json.getAsJsonObject();

        photoURL.setSrc(jsonObject.get("src").getAsString());
        photoURL.setHeight(jsonObject.get("height").getAsInt());
        photoURL.setWidth(jsonObject.get("width").getAsInt());
        photoURL.setType(jsonObject.get("type").getAsString());

        return photoURL;
    }
}
