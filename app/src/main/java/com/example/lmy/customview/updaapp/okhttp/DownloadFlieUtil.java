package com.example.lmy.customview.updaapp.okhttp;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.example.lmy.customview.Utils.FileUtils;
import com.example.lmy.customview.Utils.LogUtil;
import com.example.lmy.customview.updaapp.ApiHelperl;
import com.example.lmy.customview.updaapp.DownApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description：下载文件工具类
 * Created by kang on 2018/3/9.
 */

public class DownloadFlieUtil {
    private static final String TAG = "DownloadFlieUtil";
    private static final String PATH_CHALLENGE_VIDEO = Environment.getExternalStorageDirectory() + "";
    private Call<ResponseBody> mCall;
    private File mFile;
    private Thread mThread;
    private String mVideoPath; //下载到本地的视频路径

    public DownloadFlieUtil() {
    }

    public void downloadFile(String url, final DownloadListener downloadListener) {
        //通过Url得到保存到本地的文件名
        String name = url;
        if (FileUtils.createOrExistsDir(PATH_CHALLENGE_VIDEO)) {
            int i = name.lastIndexOf('/');//一定是找最后一个'/'出现的位置
            if (i != -1) {
                name = name.substring(i);
                mVideoPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Download" + name;
            }
        }
        if (TextUtils.isEmpty(mVideoPath)) {
            Log.e(TAG, "downloadVideo: 存储路径为空了");
            return;
        }
        //建立一个文件
        mFile = new File(mVideoPath);
        if (FileUtils.isFileExists(mFile)) {
            //判断文件是否存在 存在则删除重新下载
            new File(mVideoPath).delete();
            LogUtil.e("文件已经存在>>>>>>>删除成功");

        } else {
            LogUtil.e("文件不存在>>>>>>即将下载");
        }
        mCall = ApiHelperl.getInstance()
                .buildDownloadRetrofit()
                .createService(DownApi.class)
                .downloadFile(url);
        mCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                //下载文件放在子线程
                mThread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //保存到本地
                        writeFile2Disk(response, mFile, downloadListener);
                    }
                };
                mThread.start();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                downloadListener.onFailure("网络错误！");
            }
        });
    }

    /**
     * 写入本地文件
     */
    private void writeFile2Disk(Response<ResponseBody> response, File file, DownloadListener downloadListener) {
        downloadListener.onStart();
        long currentLength = 0;
        OutputStream os = null;

        if (response.body() == null) {
            downloadListener.onFailure("资源错误！");
            return;
        }
        InputStream is = response.body().byteStream();
        long totalLength = response.body().contentLength();

        try {
            os = new FileOutputStream(file);
            int len;
            byte[] buff = new byte[1024];
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
                currentLength += len;
                downloadListener.onProgress((int) (100 * currentLength / totalLength));
                if ((int) (100 * currentLength / totalLength) == 100) {
                    downloadListener.onFinish(mVideoPath);
                }
            }
        } catch (FileNotFoundException e) {
            downloadListener.onFailure("未找到文件！");
            e.printStackTrace();
        } catch (IOException e) {
            downloadListener.onFailure("IO错误！");
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
