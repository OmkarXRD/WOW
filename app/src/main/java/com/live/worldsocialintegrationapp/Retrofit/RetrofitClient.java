package com.live.worldsocialintegrationapp.Retrofit;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//public class RetrofitClient {
//
//    private static Retrofit retrofit;
//        private static final String BASE_URL = "https://xrdsimulators.tech/wow_project/index.php/";
//
//    public static Retrofit getRetrofitCallerObject() {
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(100, TimeUnit.MINUTES)
//                .readTimeout(50, TimeUnit.MINUTES)
//                .writeTimeout(50, TimeUnit.MINUTES).retryOnConnectionFailure(true).addInterceptor(loggingInterceptor).build();
//        if (retrofit == null) {
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build();
//        }
//        return retrofit;
//    }
//}
//public class RetrofitClient {
//
//    private static Retrofit retrofit;
//    private static final String BASE_URL = "https://xrdsimulators.tech/wow_project/index.php/";
//
//    public static Retrofit getRetrofitCallerObject() {
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(30, TimeUnit.SECONDS) // Adjust timeout values as needed
//                .readTimeout(30, TimeUnit.SECONDS)
//                .writeTimeout(30, TimeUnit.SECONDS)
//                .connectionPool(new ConnectionPool(5, 30, TimeUnit.SECONDS)) // Example connection pooling configuration
//                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .build();
//
//        if (retrofit == null) {
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .client(okHttpClient)
//                    .build();
//        }
//        return retrofit;
//    }
//}

public class RetrofitClient {

    private static final String BASE_URL = "https://xrdsimulators.tech/wow_project/index.php/";

    private static Retrofit retrofit;

    private static final class Holder {
        private static final Retrofit INSTANCE = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    public static Retrofit getInstance() {
        return Holder.INSTANCE;
    }

    private static OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                // Adjust connection pool configuration based on your needs
                .connectionPool(new ConnectionPool(5, 30, TimeUnit.SECONDS))
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }
}