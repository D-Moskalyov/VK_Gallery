package com.android.vk_gallery.app;


import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;

import java.lang.reflect.Type;

public class UserDeserializer  implements JsonDeserializer<User> {
    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        User user = new User();

        JsonObject jsonObject = json.getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("response").getAsJsonArray();
        JsonObject jsonObject1 = jsonArray.get(0).getAsJsonObject();

        user.setUid(jsonObject1.get("uid").getAsInt());
        user.setFirst_name(jsonObject1.get("first_name").getAsString());
        user.setLast_name(jsonObject1.get("last_name").getAsString());

        return user;
    }
}
