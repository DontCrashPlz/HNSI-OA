package com.hnsi.oa.hnsi_oa.application.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.hnsi.oa.hnsi_oa.application.app.MyApplication;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Deprecated
public class ReadCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences("Cookies",
                Context.MODE_PRIVATE);
        HashSet<String> preferences =
                (HashSet<String>) sp.getStringSet("cookies",new HashSet<String>());
//                (HashSet<String>) SharedPrefUtils.get(MyApplication.getInstance(),"cookies",new HashSet<>());
//                (HashSet) Preferences.getDefaultPreferences().getStringSet(Preferences.PREF_COOKIES, new HashSet<>());
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
        }

        return chain.proceed(builder.build());
    }
}