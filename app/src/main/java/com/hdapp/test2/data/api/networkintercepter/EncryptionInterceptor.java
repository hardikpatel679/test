package com.hdapp.test2.data.api.networkintercepter;

import com.hdapp.frameworkkotlin.utils.AppGlobal;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;




public class EncryptionInterceptor implements Interceptor {
    private static final String TAG = EncryptionInterceptor.class.getSimpleName();
    private final CryptoStrategy mEncryptionStrategy;


    public EncryptionInterceptor(CryptoStrategy mEncryptionStrategy) {
        this.mEncryptionStrategy = mEncryptionStrategy;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        RequestBody rawBody = request.body();
        String encryptedBody = "";


        MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
        if (mEncryptionStrategy != null) {
            try {
                String rawBodyStr = CryptoUtil.requestBodyToString(rawBody);
                encryptedBody = mEncryptionStrategy.encrypt(rawBodyStr);
                AppGlobal.print("Raw body=> %s", rawBodyStr);
                AppGlobal.print("Encrypted BODY=> %s", encryptedBody);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("No encryption strategy!");
        }
        RequestBody body = RequestBody.create(mediaType, encryptedBody);
//        request = request.newBuilder().header("User-Agent", "Your-App-Name");
        request = request.newBuilder().header("Content-Type", body.contentType().toString())
                .header("Content-Length", String.valueOf(body.contentLength()))
                .method(request.method(), body).build();

        return chain.proceed(request);
    }


}