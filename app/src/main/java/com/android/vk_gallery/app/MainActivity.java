package com.android.vk_gallery.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.util.VKUtil;


public class MainActivity extends FragmentActivity {

    //protected MyApplication app;
    private static String[] sMyScope = new String[]{VKScope.PHOTOS};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        //VKSdk.initialize(getApplicationContext());
        //app = (MyApplication)getApplication();
        //app.customAppMethod();

        VKSdk.login(this, sMyScope);
        //VKSdk.login(Fragment runningFragment, String... scope);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // ѕользователь успешно авторизовалс€
                VKRequest request = VKApi.users().get();
            }

            @Override
            public void onError(VKError error) {
                // ѕроизошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
