package com.jack.news.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Field;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class NewsApiFactory {
    /**
     * API密钥
     */
    static String apikey = "34c9312a555cb77740625e940ae8b486";

    //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
    private static final String BASEURL = "http://apis.baidu.com/";
    protected static final Object monitor = new Object();
    private static NewsApi newsApi;

    // @formatter:off
    final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    public static NewsApi getNewsApiSingleton(){
        synchronized (monitor){
            if (newsApi == null){
                initRetrofit();
            }
            return newsApi;
        }
    }

    private static void initRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(genericClient())
                .build();
        newsApi = retrofit.create(NewsApi.class);
    }

    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "text/html;charset=utf-8")
//                                .addHeader("Accept-Encoding", "gzip, deflate, sdch")
                                .addHeader("Connection", "keep-alive")
//                                .addHeader("Accept", "*/*")
//                                .addHeader("Cookie", "")
                                .addHeader("apikey", apikey)
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();

        return httpClient;
    }
}
