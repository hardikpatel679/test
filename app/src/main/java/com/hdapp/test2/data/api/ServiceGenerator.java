package com.hdapp.test2.data.api;

import android.text.TextUtils;


import com.hdapp.test2.MyApplication;
import com.hdapp.test2.R;
import com.hdapp.test2.data.api.networkintercepter.AuthenticationInterceptor;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String API_BASE_URL ="https://dl.dropboxusercontent.com/"; //MyApplication.Companion.applicationContext().getString(R.string.apiurl);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);
            httpClient.connectTimeout(30, TimeUnit.SECONDS);
            httpClient.readTimeout(30, TimeUnit.SECONDS);
            httpClient.writeTimeout(30, TimeUnit.SECONDS);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (!httpClient.interceptors().contains(logging)) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        ChuckInterceptor mChuckInterceptor = new ChuckInterceptor(MyApplication.Companion.applicationContext());
        if (!httpClient.interceptors().contains(mChuckInterceptor)) {
            mChuckInterceptor.showNotification(true);
            httpClient.addInterceptor(mChuckInterceptor);
        }
        return retrofit.create(serviceClass);
    }
}