package com.android.vk_gallery.app.activity;

import android.app.Activity;
import android.os.Bundle;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;


public class LoginActivity extends Activity{

    private static String[] sMyScope = new String[]{VKScope.PHOTOS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity mainActivity = new MainActivity();
        VKSdk.login(mainActivity, sMyScope);
    }
}
