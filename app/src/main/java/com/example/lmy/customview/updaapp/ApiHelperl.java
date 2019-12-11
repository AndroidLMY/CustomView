package com.example.lmy.customview.updaapp;

import com.example.lmy.customview.updaapp.okhttp.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @功能:
 * @Creat 2019/10/23 8:54
 * @User Lmy
 * @Compony zaituvideo
 */
public class ApiHelperl {
    private static volatile ApiHelperl mInstance;
    private OkHttpClient client;
    private Retrofit mRetrofit;
    private HttpLoggingInterceptor loggingInterceptor;

    private String baseUrl = "http://www.androidlmy.top:8080/stockcontrol/home/";//阿里

//    private String baseUrl = "http://192.168.0.114:8080/home/";//本机
//    private String baseUrl = "http://ztapi.ytsztsp.com/";//在途视频

    /**
     * 单例封装
     *
     * @return
     */
    public static ApiHelperl getInstance() {
        if (mInstance == null) {
            mInstance = new ApiHelperl();
        }
        return mInstance;
    }

    public ApiHelperl() {
        loggingInterceptor = new HttpLoggingInterceptor();

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    /**
     * 初始化Retrofit
     */
    public ApiHelperl buildRetrofit() {
        client = new OkHttpClient.Builder()//okhttp设置部分，此处还可再设置网络参数
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(client)
                // 设置请求的域名
                .baseUrl(baseUrl)
                // 设置解析转换工厂
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return this;
    }

    /**
     * 初始化Retrofit
     */
    public ApiHelperl buildDownloadRetrofit() {
        client = new OkHttpClient.Builder()//okhttp设置部分，此处还可再设置网络参数
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                // 设置解析转换工厂
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return this;
    }

    public <T> T createService(Class<T> serviceClass) {
        return mRetrofit.create(serviceClass);
    }


}


