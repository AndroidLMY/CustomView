package com.example.lmy.customview.updaapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @功能:
 * @Creat 2019/11/15 15:44
 * @User Lmy
 * @Compony zaituvideo
 */
public interface DownApi {
    /**
     * 文件下载
     */
    @Streaming //大文件时要加不然会OOM
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
