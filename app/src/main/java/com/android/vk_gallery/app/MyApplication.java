package com.android.vk_gallery.app;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.android.vk_gallery.app.activity.LoginActivity;
import com.android.vk_gallery.app.activity.MainActivity;
import com.android.vk_gallery.app.service.ServiceGenerator;
import com.android.vk_gallery.app.service.VKClient;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends android.app.Application {
    VKClient client;
    Realm realm;
    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(@Nullable VKAccessToken oldToken, @Nullable VKAccessToken newToken) {
            if (newToken == null) {
                Toast.makeText(MyApplication.this, "AccessToken invalidated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyApplication.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(this);
        client = ServiceGenerator.createService(VKClient.class);
        Fresco.initialize(this);
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }

    public VKClient getVKClient(){
        return client;
    }

    public Realm getRealm() {
        return realm;
    }
}